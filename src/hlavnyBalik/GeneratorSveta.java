package hlavnyBalik;

import java.util.ArrayList;
import java.util.Random;


/**
 * vygeneruje náhodné pozície pre políčka typu Les a Voda
 * @author adam parimucha <aparimucha@gmail.com>
 */
public class GeneratorSveta {
    private final int POCET_LES;
    private final int POCET_VODA;
    private final int POCET_POLICOK;
    
    private Integer[][] suradniceLes;
    private Integer[][] suradniceVoda;
    
    /**
     * podľa zadaných parametrov vygeneruje pozície pre políčka typu Les a Voda
     * @param pocetPolicok počet políčok na jednej strane štvorca. Svet je štvorcový čiže pre n políčok bude n*n pozícií
     * @param pocetLes počet políčok typu Les, ktoré chceme vygenerovať
     * @param pocetVoda počet políčok typu Voda, ktoré chceme vygenerovať
     */
    public GeneratorSveta(int pocetPolicok, int pocetLes, int pocetVoda) {
        this.POCET_VODA = pocetVoda;
        this.POCET_LES = pocetLes;
        this.POCET_POLICOK = pocetPolicok;
    }
    
    /**
     * vytvorí ArrayList všetkých možných súradníc sveta, vytvorí nový objekt typu Random a 2 polia súradníc
     * (jedno pre les a jedno pre vodu),
     * postupne generátor generuje čísla z rozsahu (0,veľkosť_poľa_suradnic),
     * priradí súradnicu na vygenerovanej pozícii do poľa súradníc lesa a vymaže súradnicu z ArrayListu 
     * všetkých súradníc a to isté pre súradnice vody
     */
    public void generuj() {
        ArrayList<Integer[]> suradnice = new ArrayList<>();
        Random generator = new Random();
        
        for (int i = 0; i < this.POCET_POLICOK; i++) {
            for (int j = 0; j < this.POCET_POLICOK; j++) {
                suradnice.add(new Integer[] {i, j});
            }
        }
        
        this.suradniceLes = new Integer[this.POCET_LES][1];
        this.suradniceVoda = new Integer[this.POCET_VODA][1];
        
        for (int i = 0; i < this.POCET_LES; i++) {
            int index = generator.nextInt(suradnice.size());
            this.suradniceLes[i] = suradnice.get(index);
            suradnice.remove(index);
        }
        
        for (int i = 0; i < this.POCET_VODA; i++) {
            int index = generator.nextInt(suradnice.size());
            this.suradniceVoda[i] = suradnice.get(index);
            suradnice.remove(index);
        }  
    }
    
    /**
     * 
     * @return pole súradníc lesa
     */
    public Integer[][] getSuradniceLes() {
        return this.suradniceLes;
    }
    
    /**
     * 
     * @return pole súradníc vody 
     */
    public Integer[][] getSuradniceVoda() {
        return this.suradniceVoda;
    }
    
    /**
     * 
     * @return počet políčok typu Les
     */
    public int getPocetLes() {
        return this.POCET_LES;
    }
    
    /**
     * 
     * @return počet políčok typu Voda
     */
    public int getPocetVoda() {
        return this.POCET_VODA;
    }
    
    
    
    
}
