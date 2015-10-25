/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import java.io.Serializable;

/**
 *
 * @author Igor
 */
public class Fonds implements IFonds, Serializable {
    private String naam;
    private double koers;
    
    public Fonds(String n){
        naam = n;
        randomKoers();
    }
    
    public void setKoers(double k){
        koers = k;
    }
    
    public void randomKoers()
    {      
        koers = (double)Math.round((Math.random() * 100) * 1000d) / 1000d;
    }
    
    @Override
    public String getNaam() {
        return naam;
    }

    @Override
    public double getKoers() {
        return koers;
    }
    
}
