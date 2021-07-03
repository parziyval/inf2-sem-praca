package grafika;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * enum ciest k obrazkom 
 * @author adam parimucha <aparimucha@gmail.com>
 * 
 * 
 */
public enum CestyKObrazkom { 
    LUKA("obrazky\\luka.png"),
    LES("obrazky\\les.png"), 
    VODA("obrazky\\voda.png"),
    POLE("obrazky\\pole.png"),
    RYBAR_DOM("obrazky\\rybar_dom.png"),
    DREVORUBAC_DOM("obrazky\\drevorubac_dom.png"),
    FARMAR_DOM("obrazky\\farmar_dom.png");
  
    private String cesta; 
  
    public String getCesta() { 
        return this.cesta; 
    } 
   
    CestyKObrazkom(String cesta) { 
        this.cesta = cesta; 
    } 
} 
