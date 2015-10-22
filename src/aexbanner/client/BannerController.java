package aexbanner.client;

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

    public BannerController(AEXBanner banner) {

        this.banner = banner;
        this.effectenbeurs = new MockEffectenbeurs();

        // Start polling timer: update banner every two seconds
        pollingTimer = new Timer();
        pollingTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                String s = "";
                for (IFonds eb : effectenbeurs.getKoersen()) {
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
        ((MockEffectenbeurs) effectenbeurs).stop();
    }
}
