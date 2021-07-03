package grafika;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import policko.IPolicko;
import policko.Les;
import policko.Luka;
import policko.Pole;
import policko.Voda;
import policko.DomDrevorubac;
import policko.DomFarmar;
import policko.DomRybar;

import hlavnyBalik.Svet;



/**
 * vytvára grafické zobrazenie z triedy Svet
 * @author adam parimucha <aparimucha@gmail.com>
 */
public class Zobrazenie {
    private Svet svet;
    private final int POCET_POLICOK;
    private Obrazok[][] zobrazenie;
    private final int VELKOST_OBRAZKA;
    
    /**
     * vytvorí si pole obrázkov podľa parametra typu Svet, ktorý dostane
     * @param svet parameter typu Svet, ktorý sa má vykresliť
     */
    public Zobrazenie(Svet svet) {
        this.svet = svet;
        this.POCET_POLICOK = svet.getPocetPolicok();
        this.zobrazenie = new Obrazok[this.POCET_POLICOK][this.POCET_POLICOK];
        this.VELKOST_OBRAZKA = 50;
        
    }
    
    /**
     * pre políčko na pozícii i,j priradí do poľa zobrazenie na príslušnú pozíciu príslušný obrázok
     * @param svet svet ktorý sa má zobraziť
     */
    public void nacitajPolicka(Svet svet) {
        for (int i = 0; i < this.POCET_POLICOK; i++) {
            for (int j = 0; j < this.POCET_POLICOK; j++) {
                IPolicko policko = this.svet.getPolicko(i, j);
                if (policko instanceof Luka) {
                    this.zobrazenie[i][j] = new Obrazok(CestyKObrazkom.LUKA.getCesta(), j * this.VELKOST_OBRAZKA, i * this.VELKOST_OBRAZKA);
                } else if (policko instanceof Les) {
                    this.zobrazenie[i][j] = new Obrazok(CestyKObrazkom.LES.getCesta(), j * this.VELKOST_OBRAZKA, i * this.VELKOST_OBRAZKA);
                } else if (policko instanceof Voda) {
                    this.zobrazenie[i][j] = new Obrazok(CestyKObrazkom.VODA.getCesta(), j * this.VELKOST_OBRAZKA, i * this.VELKOST_OBRAZKA);
                } else if (policko instanceof Pole) {
                    this.zobrazenie[i][j] = new Obrazok(CestyKObrazkom.POLE.getCesta(), j * this.VELKOST_OBRAZKA, i * this.VELKOST_OBRAZKA);
                } else if (policko instanceof DomDrevorubac) {
                    this.zobrazenie[i][j] = new Obrazok(CestyKObrazkom.DREVORUBAC_DOM.getCesta(), j * this.VELKOST_OBRAZKA, i * this.VELKOST_OBRAZKA);
                } else if (policko instanceof DomRybar) {
                    this.zobrazenie[i][j] = new Obrazok(CestyKObrazkom.RYBAR_DOM.getCesta(), j * this.VELKOST_OBRAZKA, i * this.VELKOST_OBRAZKA);
                } else if (policko instanceof DomFarmar) {
                    this.zobrazenie[i][j] = new Obrazok(CestyKObrazkom.FARMAR_DOM.getCesta(), j * this.VELKOST_OBRAZKA, i * this.VELKOST_OBRAZKA);
                }
            }
        }
    }
    
    /**
     * zobrazí postupne každý obrázok v poli zobrazenie
     */
    public void zobraz() {
        for (int i = 0; i < this.POCET_POLICOK; i++) {
            for (int j = 0; j < this.POCET_POLICOK; j++) {
                this.zobrazenie[i][j].zobraz();
            }
        }
    }
    
    /**
     * skryje postupne každý obrázok v poli zobrazenie
     */
    public void skry() {
        for (int i = 0; i < this.POCET_POLICOK; i++) {
            for (int j = 0; j < this.POCET_POLICOK; j++) {
                this.zobrazenie[i][j].skry();
            }
        }
    }
    
    
    /**
     * každý obrázok v poli zobrazenie postupne skryje, načíta nový stav sveta a potom ho zobrazí
     * @param svet 
     */
    public void prekresli(Svet svet) {
        this.skry();
        this.nacitajPolicka(svet);
        this.zobraz();
        
    }
    
    /**
     * 
     * @return hodnota konštatnty VELKOST_OBRAZKA
     */
    public int getVelkostObrazka() {
        return this.VELKOST_OBRAZKA;
    }
    
    
    
    
}
