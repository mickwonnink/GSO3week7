package aexbanner.client;

import java.rmi.RemoteException;
import server.MockEffectenbeurs;
import shared.IEffectenbeurs;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import shared.IFonds;

public class BannerController {

    private AEXBanner banner;
    private IEffectenbeurs effectenbeurs;
    private Timer pollingTimer;
    private RMIClient RMIC;

    public BannerController(AEXBanner banner) {

        this.RMIC = new RMIClient("192.168.1.126", 1099);
        this.banner = banner;
        try{
        this.effectenbeurs = new MockEffectenbeurs();
        }
        catch (RemoteException e){
        }

        // Start polling timer: update banner every two seconds
        pollingTimer = new Timer();
        pollingTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                
                String s = "";
                for (IFonds eb : RMIC.GetKoersen()) {
                    s = s + " " + eb.getNaam() + " "+  eb.getKoers();
                }
                final String fs = s;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    banner.setKoersen(fs);
                }
            });
                
            }
        }, 0, 2000);
    }

    // Stop banner controller
    public void stop() {
        pollingTimer.cancel();
        // Stop simulation timer of effectenbeurs
        // TODO
        try {
        ((MockEffectenbeurs) effectenbeurs).stop();
        }
        catch (RemoteException e){
            
        }
    }
}
