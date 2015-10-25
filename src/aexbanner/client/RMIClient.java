/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aexbanner.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;
import shared.IEffectenbeurs;
import shared.IFonds;

/**
 * 
 *
 * @author Mick Wonnink
 */
public class RMIClient {

    // Set binding name for student administration
    private static final String bindingName = "EffectenBeurs";

    // References to registry and student administration
    private Registry registry = null;
    private IEffectenbeurs effectBeurs = null;

    // Constructor
    public RMIClient(String ipAddress, int portNumber) {

        // Print IP address and port number for registry
        System.out.println("Client: IP Address: " + ipAddress);
        System.out.println("Client: Port number " + portNumber);

        // Locate registry at IP address and port number
        try {
            registry = LocateRegistry.getRegistry(ipAddress, portNumber);
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: RemoteException: " + ex.getMessage());
            registry = null;
        }

        // Print result locating registry
        if (registry != null) {
            System.out.println("Client: Registry located");
        } else {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: Registry is null pointer");
        }


        // Bind student administration using registry
        if (registry != null) {
            try {
                effectBeurs = (IEffectenbeurs) registry.lookup(bindingName);
            } catch (RemoteException ex) {
                System.out.println("Client: Cannot bind effecten beurs");
                System.out.println("Client: RemoteException: " + ex.getMessage());
                effectBeurs = null;
            } catch (NotBoundException ex) {
                System.out.println("Client: Cannot bind effecten beurs");
                System.out.println("Client: NotBoundException: " + ex.getMessage());
                effectBeurs = null;
            }
        }

        // Print result binding student administration
        if (effectBeurs != null) {
            System.out.println("Client: Effecten beurs bound");
        } else {
            System.out.println("Client: Effecten beurs is null pointer");
        }

        // Test RMI connection
        if (effectBeurs != null) {
            testEffectenBeurs();
        }
    }
    
    public List<IFonds> GetKoersen(){
        try {
        return effectBeurs.getKoersen();
        }
        catch (RemoteException e){
        }
        return null;
    }


    // Test RMI connection
    private void testEffectenBeurs() {
        
        //Get amount of fonds
        try {
            List<IFonds> koersen = effectBeurs.getKoersen();
            System.out.println("Client: Number of fonds: " + koersen.size());
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot get number of fonds");
            System.out.println("Client: RemoteException: " + ex.getMessage());
        }
        
        //Get first fonds' name
                try {
            List<IFonds> koersen = effectBeurs.getKoersen();
            System.out.println("Client: Name of first fonds: " + koersen.get(0).getNaam());
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot get first fonds' name");
            System.out.println("Client: RemoteException: " + ex.getMessage());
        }

    }

    // Main method
    public static void main(String[] args) {

        // Welcome message
        System.out.println("CLIENT USING REGISTRY");

        // Get ip address of server
        Scanner input = new Scanner(System.in);
        System.out.print("Client: Enter IP address of server: ");
        String ipAddress = input.nextLine();

        // Get port number
        System.out.print("Client: Enter port number: ");
        int portNumber = input.nextInt();

        // Create client
        RMIClient client = new RMIClient(ipAddress, portNumber);
    }
}
