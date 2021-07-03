/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package policko;

/**
 * Potomok triedy policko, moze mat obyvatelov
 * @author adam parimucha <aparimucha@gmail.com>
 */
public class DomDrevorubac extends Policko implements IObyvatelny {
    private int pocetObyvatelov;

    public DomDrevorubac(int pocetObyvatelov, int riadok, int stlpec) {
        super(riadok, stlpec);
        this.pocetObyvatelov = pocetObyvatelov;
    }
    
    @Override
    public int getPocetObyvatelov() {
        return this.pocetObyvatelov;
    }
    
    @Override
    public String toString() {
        return super.toString() + "Drevorubacsky dom\npocet obyvatelov: " + this.pocetObyvatelov;
    }
    
    /**
     * pridá jedného obyvateľa do počtu obyvateľov
     */
    @Override
    public void pridajObyvatela() {
        this.pocetObyvatelov += 1;
    }
}
