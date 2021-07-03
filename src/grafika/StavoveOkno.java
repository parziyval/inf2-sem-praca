package grafika;


import hlavnyBalik.Hra;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * vytvorí okno pre plátno na ktorom sa vykresľuje stav hry
 * @author adam parimucha <aparimucha@gmail.com>
 * 
 * 
 */
public class StavoveOkno extends JFrame {
    
    private Hra hra;
    
    /**
     * 
     * @param hra hra, ktorej stav sa má vykresľovať
     */
    public StavoveOkno(Hra hra) {
        this.hra = hra;
        
        this.setLocation(10, 700);
        this.setSize(1000, 75);

        this.setResizable(false);
        this.setTitle("Stav hry");
       
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        this.setLayout(new BorderLayout(2, 2));
        this.add(BorderLayout.CENTER, new StavovePlatno(hra));
        
        this.setVisible(true);
        this.toFront();   
    }
    
    /**
     * prekreslí obsah okna
     */
    public void prekresli() {
        this.repaint();
    }
}
