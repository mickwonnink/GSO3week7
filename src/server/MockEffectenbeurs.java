package server;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import shared.Fonds;
import shared.IEffectenbeurs;
import shared.IFonds;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class MockEffectenbeurs extends UnicastRemoteObject implements IEffectenbeurs, Serializable {

    private List<IFonds> fondsList = new ArrayList<>();
    private Timer timer = new Timer();
    public MockEffectenbeurs() throws RemoteException  {
        for (String name : Arrays.asList("Mick", "Igor", "Fontys", "GSO", "AEX", "Banner")) {
            fondsList.add(new Fonds(name));
        }
        Simulate();
    }

    public void Simulate() throws RemoteException {
        (timer).schedule(new TimerTask() {

            @Override
            public void run() {
                for (IFonds fonds : fondsList) {
                    ((Fonds) fonds).randomKoers();
                }
            }

        }, 0, 3000);
        
    }

    @Override
    public List<IFonds> getKoersen() throws RemoteException {
        return fondsList;
    }

    public void stop() throws RemoteException {
        timer.cancel();
    }
   
}
