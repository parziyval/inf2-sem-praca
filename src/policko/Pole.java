/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package policko;

/**
 * Potomok triedy Policko, da sa tazits
 * @author adam parimucha <aparimucha@gmail.com>
 */
public class Pole extends Policko implements ITazitelny {
    private int pocetObilia;
    private int riadok;
    private int stlpec;

    public Pole(int pocetObilia, int riadok, int stlpec) {
        super(riadok, stlpec);
        this.pocetObilia = pocetObilia;
        this.riadok = riadok;
        this.stlpec = stlpec;
    }

    public int getPocetObilia() {
        return this.pocetObilia;
    }

    @Override
    public int getRiadok() {
        return this.riadok;
    }

    @Override
    public int getStlpec() {
        return this.stlpec;
    }

    @Override
    public String toString() {
        return super.toString() + "Pole\nPocet obilia: " + this.pocetObilia;
    }
    
    @Override
    public void vytaz(int kolko) {
        this.pocetObilia -= kolko;
    }
    
    
    
}
