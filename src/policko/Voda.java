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
public class Voda extends Policko implements ITazitelny {
    private int riadok;
    private int stlpec;
    private int pocetRyb;

    public Voda(int pocetRyb, int riadok, int stlpec) {
        super(riadok, stlpec);
        this.riadok = riadok;
        this.stlpec = stlpec;
        this.pocetRyb = pocetRyb;
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
        return super.toString() + "Voda\nPocet ryb: " + this.pocetRyb;
    }
    
    @Override
    public void vytaz(int kolko) {
        this.pocetRyb -= kolko;
    }

    public int getPocetRyb() {
        return this.pocetRyb;
    }
    
    /**
     * zväčší počet rýb o jeho stotinu
     */
    public void rozmnoz() {
        this.pocetRyb += (this.pocetRyb / 100);
    }
    
    
    
}
