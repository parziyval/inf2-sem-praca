/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package policko;


/**
 * Potom triedy Policko
 * @author adam parimucha <aparimucha@gmail.com>
 * 
 */
public class Luka extends Policko {
    
    private int riadok;
    private int stlpec;
    
    public Luka(int riadok, int stlpec) {
        super(riadok, stlpec);
        this.riadok = riadok;
        this.stlpec = stlpec;
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
        return super.toString() + "Luka";
    }
    
    
    
}
