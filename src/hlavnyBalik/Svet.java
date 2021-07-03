/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hlavnyBalik;

import policko.IPolicko;
import policko.Les;
import policko.Luka;
import policko.Voda;

/**
 * trieda, ktorá vytvára logickú reprezentáciu sveta
 * @author adam parimucha <aparimucha@gmail.com>
 */
public class Svet {
    private final int POCET_POLICOK;
    private IPolicko[][] svet;
   
    /**
     * vytvorí svojrozmerné pole objektov typu IPolicko, vytovrí nový generátor sveta, od ktorého si potom vypýta súradnice 
     * a priradí ich na príslušné pozície
     * @param PocetPolicok počet políčok na jednej strane štvorca. Svet má rozmery PocetPolicok x pocetPolicok
     * @param PocetVoda počet políčok typu Voda vo svete
     * @param PocetLes počet políčok typu Les vo svete
     */
    public Svet(int pocetPolicok, int pocetVoda, int pocetLes) {
        this.POCET_POLICOK = pocetPolicok;
        this.svet = new IPolicko[this.POCET_POLICOK][this.POCET_POLICOK];
        GeneratorSveta generator = new GeneratorSveta(this.POCET_POLICOK, pocetLes, pocetVoda);
        generator.generuj();
        
        for (int i = 0; i < this.POCET_POLICOK; i++) {
            for (int j = 0; j < this.POCET_POLICOK; j++) {
                this.svet[i][j] = new Luka(i, j);
            }
        }
        
        for (Integer[] suradnice : generator.getSuradniceLes()) {
            int riadok = suradnice[0];
            int stlpec = suradnice[1];
            this.svet[riadok][stlpec] = new Les(400, riadok, stlpec);
        }
        
        for (Integer[] suradnice : generator.getSuradniceVoda()) {
            int riadok = suradnice[0];
            int stlpec = suradnice[1];
            this.svet[riadok][stlpec] = new Voda(1500, riadok, stlpec);
        }
    }
    
    /**
     * 
     * @return počet políčok na jednej strane sveta. Svet je rozmerov pocetPolicok x pocetPolicok
     */
    public int getPocetPolicok() {
        return this.POCET_POLICOK;
    }
    
    /**
     * 
     * @param riadok
     * @param stlpec
     * @return vráti objekt typu IPolicko na pozícii [riadok,stlpec]
     */
    public IPolicko getPolicko(int riadok, int stlpec) {
        return this.svet[riadok][stlpec];
    }
    
    /**
     * vloží na pozíciu [riadok,stlpec] objekt typu IPolicko zadaný v parametri
     * @param riadok
     * @param stlpec
     * @param policko 
     */
    public void setPolicko(int riadok, int stlpec, IPolicko policko) {
        this.svet[riadok][stlpec] = policko;
    }
}
