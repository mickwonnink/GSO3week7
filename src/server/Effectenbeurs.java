package server;

import shared.Fonds;
import shared.IEffectenbeurs;
import shared.IFonds;
import java.util.ArrayList;
import java.util.List;

public class Effectenbeurs implements IEffectenbeurs {
    private List<IFonds> fondsList = new ArrayList<>();
    
    @Override
    public List<IFonds> getKoersen() {
        return fondsList;
    }
}