/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package policko;

/**
 * Potomok triedy Policko, da sa tazit
 * @author adam parimucha <aparimucha@gmail.com>
 */
public class Les extends Policko implements ITazitelny {
    private int pocetDreva;
    private int riadok;
    private int stlpec;

    public Les(int pocetDreva, int riadok, int stlpec) {
        super(riadok, stlpec);
        this.pocetDreva = pocetDreva;
        this.riadok = riadok;
        this.stlpec = stlpec;
    }

    public int getPocetDreva() {
        return this.pocetDreva;
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
        return super.toString() + "Les\nPocet dreva: " + this.pocetDreva;
    }
    
    @Override
    public void vytaz(int kolko) {
        this.pocetDreva -= kolko;
    }     
}
