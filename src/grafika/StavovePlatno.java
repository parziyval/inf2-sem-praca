package grafika;

import hlavnyBalik.Hra;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
 * 
 * plátno, na ktoré sa vykresľuje stav hry
 * @author adam
 * 
 * 
 */
public class StavovePlatno extends JComponent {
    
    private Hra hra;
    
    /**
     * 
     * @param hra hra, ktorej stav sa vykresľuje
     */
    public StavovePlatno(Hra hra) {
        this.hra = hra;
    }
    
    
    /**
     * vykreslí na plátno String so stavom hry
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D gg = (Graphics2D)g;
        
        Font font = gg.getFont().deriveFont( 20.0f );
        gg.setFont(font);
        gg.drawString(this.hra.dajStav(), 15, 25);
    }
}