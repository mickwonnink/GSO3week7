package server;

import shared.Fonds;
import shared.IEffectenbeurs;
import shared.IFonds;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class MockEffectenbeurs implements IEffectenbeurs {

    private List<IFonds> fondsList = new ArrayList<>();
    private Timer timer = new Timer();
    public MockEffectenbeurs() {
        for (String name : Arrays.asList("Mick", "Igor", "Fontys", "GSO", "AEX", "Banner")) {
            fondsList.add(new Fonds(name));
        }
        Simulate();
    }

    public void Simulate() {
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
    public List<IFonds> getKoersen() {
        return fondsList;
    }

    public void stop() {
        timer.cancel();
    }
   
}
