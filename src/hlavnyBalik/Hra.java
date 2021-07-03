/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hlavnyBalik;

import grafika.Zobrazenie;
import grafika.Manazer;
import grafika.StavoveOkno;
import javax.swing.JOptionPane;

import policko.IPolicko;
import policko.IObyvatelny;
import policko.ITazitelny;
import policko.Les;
import policko.Luka;
import policko.Pole;
import policko.Voda;
import policko.DomDrevorubac;
import policko.DomFarmar;
import policko.DomRybar;

/**
 * trieda, ktorá ovláda celú logiku hry
 * @author adam parimucha <aparimucha@gmail.com>
 */
public class Hra {
    private Svet svet;
    private Manazer manazer;
    private Zobrazenie zobrazenie;
    private static Hra instancia;
    private StavoveOkno okno;
    
    private int pocetDreva;
    private int pocetJedla;
    private int pocetDrevorubacov;
    private int pocetRybarov;
    private int pocetFarmarov;
    
    private final int POCET_JEDLA_ZA_KLIK;
    private final int POCET_DREVA_ZA_KLIK;
    private final int POCET_JEDLA_NA_NOVEHO;
    private final int POCET_DREVA_NA_DOM;
    private final int MAX_POCET_OB_NA_DOM;
    private final int POCET_JEDLA_NA_NOVE_POLE;
    private final int POCET_JEDLA_NA_NOVY_LES;
    private final int POCET_JEDLA_NA_ROZMNOZENIE_RYB;
    

    
    /**
     * vytvorí nový svet, zobrazenie k nemu, manažéra klikania myšou,
     * nastaví meniteľné atribúty a konštanty
     */
    public Hra() {
        //inicializacia sveta, grafiky a ovladania
        this.svet = new Svet(12, 25, 20);
        this.zobrazenie = new Zobrazenie(this.svet);
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this); 
        this.zobrazenie.nacitajPolicka(this.svet);
        this.zobrazenie.zobraz();
        
        //menitelne atributy
        this.pocetDreva = 300;
        this.pocetJedla = 50;
        this.pocetDrevorubacov = 0;
        this.pocetFarmarov = 0;
        this.pocetRybarov = 0;
        
        //konstanty
        this.POCET_JEDLA_ZA_KLIK = 50;
        this.POCET_DREVA_ZA_KLIK = 50;
        this.POCET_JEDLA_NA_NOVEHO = 50;
        this.POCET_DREVA_NA_DOM = 150;
        this.MAX_POCET_OB_NA_DOM = 5;
        this.POCET_JEDLA_NA_NOVE_POLE = 200;
        this.POCET_JEDLA_NA_NOVY_LES = 200;
        this.POCET_JEDLA_NA_ROZMNOZENIE_RYB = 5;
        
        this.okno = new StavoveOkno(this);
        
    }
    
    /**
     * vypočíta riadok a stlpec kliku podľa súradníc kliku a veľkosti obrázka na plátne
     * zistí akého je typu políčko
     * pre všetky políčka vykreslí okno s jeho toString()
     * ak je typu Luka vykreslí aj možnosti čo na nej možno postaviť a podľa výberu pošle správu
     * ak je typu Voda vykreslí aj gombík s možnosťou rozmnožiť ryby, a ak je dostatok jedla 
     * na rozmnoženie rýb pošle sa políčku správa rozmoz()
     * 
     * po každom kliku sa prekreslí okno so stavom hry
     * @param x pozícia x kliku
     * @param y pozícia y kliku
     * 
     *
     */
    public void pravyKlik(int x, int y) {
        int riadok = y / this.zobrazenie.getVelkostObrazka();
        int stlpec = x / this.zobrazenie.getVelkostObrazka();
        IPolicko policko = this.svet.getPolicko(riadok, stlpec);
        if (policko instanceof Luka) {
            String[] buttony = { "Zasadiť pole" , "Zasadiť les" , "Postaviť drevorubačský dom" ,
                "Postaviť farmársky dom" , "Postaviť rybársku chatku"};    
            int moznost = JOptionPane.showOptionDialog(null, "Vyber si čo chceš s týmto políčkom robiť", "Výber možnosti"
                    , JOptionPane.WARNING_MESSAGE, 0, null, buttony, buttony[0]);
            if (moznost == 0 || moznost == 1) {
                this.zasad(moznost, riadok, stlpec);
            } else if (moznost == 2 || moznost == 3 || moznost == 4) {
                this.postavDom(moznost, riadok, stlpec);
            }
            this.vypisStav();
        } else if (policko instanceof Voda) {
            Voda voda = (Voda)policko;
            String[] buttony = {"Rozmnozit ryby "};  
            boolean koniec = false;
            while (!koniec) {
                int moznost = JOptionPane.showOptionDialog(null, voda.toString(), "More"
                    , JOptionPane.WARNING_MESSAGE, 0, null, buttony, buttony[0]);
                
                if (moznost == 0) {
                    if (this.pocetJedla >= this.POCET_JEDLA_NA_ROZMNOZENIE_RYB) {
                        voda.rozmnoz();
                        this.pocetJedla -= this.POCET_JEDLA_NA_ROZMNOZENIE_RYB;
                    } else {
                        JOptionPane.showMessageDialog(null, "Nemáš dostatok jedla na rozmnoženie rýb! (" + this.POCET_JEDLA_NA_ROZMNOZENIE_RYB + ")", "Chyba", JOptionPane.WARNING_MESSAGE, null);
                    }
                } else if (moznost == JOptionPane.CLOSED_OPTION) {
                    koniec = true;
                }
            }    
        } else {
            JOptionPane.showMessageDialog(null, policko.toString());
        }
        this.okno.prekresli();
        
    }
    
    /**
     * vypočíta riadok a stlpec kliku podľa súradníc kliku a veľkosti obrázka na plátne
     * zistí akého je typu políčko
     * ak je typu ITazitelny zavolá sa metóda vytaz
     * ak je typu IObyvatelny a je dostatok jedla na nového obyvateľa a v danom dome je menej ako maximálny počet obyvateľov, pridá mu obyvateľa
     * po každom kliku prekreslí okno so stavom hry
     * ak je ineho typu, nespraví nič
     * 
     * po každom kliku sa prekreslí okno so stavom hry
     * @param x pozícia x kliku
     * @param y pozícia y kliku
     * 
     *
     */
    public void lavyKlik(int x, int y) {
        int riadok = y / this.zobrazenie.getVelkostObrazka();
        int stlpec = x / this.zobrazenie.getVelkostObrazka();
        IPolicko policko = this.svet.getPolicko(riadok, stlpec);
        if (policko instanceof ITazitelny) {
            this.vytaz(policko);
        } else if (policko instanceof IObyvatelny) {
            if (((IObyvatelny)policko).getPocetObyvatelov() < this.MAX_POCET_OB_NA_DOM) {
                if (this.pocetJedla >= this.POCET_JEDLA_NA_NOVEHO) {
                    ((IObyvatelny)policko).pridajObyvatela();
                    if (policko instanceof DomDrevorubac) {
                        this.pocetDrevorubacov += 1;
                    } else if (policko instanceof DomFarmar) {
                        this.pocetFarmarov += 1;
                    } else if (policko instanceof DomRybar) {
                        this.pocetRybarov += 1;
                    }
                    this.pocetJedla -= this.POCET_JEDLA_NA_NOVEHO;
                } else {
                    JOptionPane.showMessageDialog(null, "Nemas dostatok jedla na vytvorenie noveho obyvatela!", "Chyba", JOptionPane.WARNING_MESSAGE, null);
                }
            } else {
                JOptionPane.showMessageDialog(null, "V dome býva maximálny počet obyvateľov!", "Chyba", JOptionPane.WARNING_MESSAGE, null);
            }  
        }
        //this.vypisStav();
        this.okno.prekresli();
    }
    
    /**
     * ak je dostatok jedla, zmení políčko na vybranej pozícii na políčko typu Les alebo Pole podľa zvolenej možnosti
     * @param moznost 0-zasadiť pole, 1-zasadiť les
     * @param riadok riadok políčka na ktoré chceme niečo zasadiť
     * @param stlpec stĺpec políčka na ktoré chceme niečo zasadiť
     */
    public void zasad(int moznost, int riadok, int stlpec) {
        switch (moznost) {
            case 0:
                if (this.pocetJedla >= this.POCET_JEDLA_NA_NOVE_POLE) {
                    this.svet.setPolicko(riadok, stlpec, new Pole(400, riadok, stlpec));
                    this.zobrazenie.prekresli(this.svet);
                    this.pocetJedla -= this.POCET_JEDLA_NA_NOVE_POLE;
                } else {
                    JOptionPane.showMessageDialog(null, "Nemáš dostatok jedla na zasadenie poľa (" + this.POCET_JEDLA_NA_NOVE_POLE + ")", "Chyba", JOptionPane.WARNING_MESSAGE, null);
                }
                break;
            case 1:
                if (this.pocetJedla >= this.POCET_JEDLA_NA_NOVY_LES) {
                    this.svet.setPolicko(riadok, stlpec, new Les(400, riadok, stlpec));
                    this.zobrazenie.prekresli(this.svet);
                    this.pocetJedla -= this.POCET_JEDLA_NA_NOVY_LES;
                } else {
                    JOptionPane.showMessageDialog(null, "Nemáš dostatok jedla na zasadenie lesa (" + this.POCET_JEDLA_NA_NOVY_LES + ")", "Chyba", JOptionPane.WARNING_MESSAGE, null);
                }
                break;
            default:
                break;
        }   
    }
    
    /**
     * ak je dostatok dreva, zmení políčko na vybranej pozícii na políčko typu DomDrevorubac,DomFarmar alebo DomRybar podľa zvolenej možnosti
     * @param moznost 2-drevorubačský dom, 3-farmársky dom, 4-rybárska chatka
     * @param riadok riadok políčka, na ktorom chceme postaviť dom
     * @param stlpec stĺpec políčka, na ktorom chceme postaviť dom
     */
    public void postavDom(int moznost, int riadok, int stlpec) {
        if (this.pocetDreva >= this.POCET_DREVA_NA_DOM) {
            switch (moznost) {
                case 2:
                    this.svet.setPolicko(riadok, stlpec, new DomDrevorubac(1, riadok, stlpec));
                    this.pocetDrevorubacov++;
                    break;   
                case 3:
                    this.svet.setPolicko(riadok, stlpec, new DomFarmar(1, riadok, stlpec));
                    this.pocetFarmarov++;
                    break;
                case 4:
                    this.svet.setPolicko(riadok, stlpec, new DomRybar(1, riadok, stlpec));
                    this.pocetRybarov++;
                    break;
                default:
                    break;
            }
            this.zobrazenie.prekresli(this.svet);
            this.pocetDreva -= this.POCET_DREVA_NA_DOM;
        } else {
            JOptionPane.showMessageDialog(null, "Nemáš dostatok dreva na postavanie domu (" + this.POCET_DREVA_NA_DOM + ")", "Chyba", JOptionPane.WARNING_MESSAGE, null);
        }  
    }
    
    @Deprecated
    public void vypisStav() {
        System.out.println("pocet dreva: " + this.pocetDreva + " pocet Jedla: " + this.pocetJedla);
        System.out.println("pocet drevorubacov: " + this.pocetDrevorubacov + " pocet rybarov: " + this.pocetRybarov + " pocet farmarov: " + this.pocetFarmarov);
    }
    
    /**
     * 
     * @return String s počtom surovín a obyvateľov
     */
    public String dajStav() {
        return "pocet dreva: " + this.pocetDreva + " pocet Jedla: " + this.pocetJedla + " pocet drevorubacov: " + this.pocetDrevorubacov + " pocet rybarov: " + this.pocetRybarov + " pocet farmarov: " + this.pocetFarmarov;
    }
    
    /**
     * vyťaží počet surovín, ktorý sa vypočíta pre políčka rôznych typov ako:
     * pre Les: POCET_DREVA_ZA_KLIK * pocetDrevorubacov
     * pre Pole: POCET_JEDLA_ZA_KLIK * pocetFarmarov
     * pre Voda: POCET_JEDLA_ZA_KLIK * pocetRybarov
     * 
     * Ak je počet ťaženého väčší ako počet zostávajúceho zmenia sa políčka nasledovne:
     * Les a Pole sa zmenia na Luku
     * Voda zostane tak, len v nej už nebudú ryby
     * 
     * @param policko políčko, ktoré sa pokúšame vyťažiť
     * 
     */
    public void vytaz(IPolicko policko) {
        if (policko instanceof Les) {
            int pocetVytazeneho = this.POCET_DREVA_ZA_KLIK * this.pocetDrevorubacov;
            Les les = (Les)policko;
            if (les.getPocetDreva() <= pocetVytazeneho) {
                pocetVytazeneho = les.getPocetDreva();
                les.vytaz(pocetVytazeneho);
                this.pocetDreva += pocetVytazeneho;
                this.svet.setPolicko(les.getRiadok(), les.getStlpec(), new Luka(les.getRiadok(), les.getStlpec()));
                this.zobrazenie.prekresli(this.svet);
            } else {
                les.vytaz(pocetVytazeneho);
                this.pocetDreva += pocetVytazeneho;
            }
        } else if (policko instanceof Pole) {
            int pocetVytazeneho = this.POCET_JEDLA_ZA_KLIK * this.pocetFarmarov;
            Pole pole = (Pole)policko;
            if (pole.getPocetObilia() <= pocetVytazeneho) {
                pocetVytazeneho = pole.getPocetObilia();
                pole.vytaz(pocetVytazeneho);
                this.pocetJedla += pocetVytazeneho;
                this.svet.setPolicko(pole.getRiadok(), pole.getStlpec(), new Luka(pole.getRiadok(), pole.getStlpec()));
                this.zobrazenie.prekresli(this.svet);
            } else {
                pole.vytaz(pocetVytazeneho);
                this.pocetJedla += pocetVytazeneho;
            }
        } else if (policko instanceof Voda) {
            int pocetVytazeneho = this.POCET_JEDLA_ZA_KLIK * this.pocetRybarov;
            Voda voda = (Voda)policko;
            if (voda.getPocetRyb() <= pocetVytazeneho) {
                pocetVytazeneho = voda.getPocetRyb();
                voda.vytaz(pocetVytazeneho);
                this.pocetJedla += pocetVytazeneho;
                JOptionPane.showMessageDialog(null, "Vsetky ryby z tohto mora sú vylovené", "Chyba", JOptionPane.WARNING_MESSAGE, null);
            } else {
                voda.vytaz(pocetVytazeneho);
                this.pocetJedla += pocetVytazeneho;
            }
        } 
    }
    /**
     * prejde všetky políčka a keď natrafí na dom tak počet jeho obyvateľov pripočíta k celkovému počtu obyvateľov
     */
    public void inicializujPocetObyvatelov() {
        for (int i = 0; i < this.svet.getPocetPolicok(); i++) {
            for (int j = 0; j < this.svet.getPocetPolicok(); j++) {
                IPolicko policko = this.svet.getPolicko(i, j);
                if (policko instanceof DomDrevorubac) {
                    this.pocetDrevorubacov += ((DomDrevorubac)policko).getPocetObyvatelov();
                } else if (policko instanceof DomFarmar) {
                    this.pocetFarmarov += ((DomFarmar)policko).getPocetObyvatelov();
                } else if (policko instanceof DomRybar) {
                    this.pocetRybarov += ((DomRybar)policko).getPocetObyvatelov();
                }
            }
        }
    }
    
    /**
     * vytvorí jedináčika triedy Hra
     * @return inštancia triedy Hra
     */
    public static Hra spusti() {
        if (Hra.instancia == null) {
            Hra.instancia = new Hra();
            Hra.instancia.inicializujPocetObyvatelov();
        }
        return Hra.instancia;
    }
    

}
