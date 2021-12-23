/*
 * Classe casella
 *
 * El constructor defineix una casella com un rectangle d'un color i si està
 * ocupada o no, el constructor no posa cap peça a la casella
 *
 * El mètode paintComponent pinta el rectangle de la casella si la casella està
 * ocupada pinta la pesa cridant al mètode de pintar peces
 *
 * El mètode setPesa col·loca una peça en la casella en questió
 */

package nreinas;

/**
 *
 * @author miquelmascaro
 */
import nreinas.Pesa;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

class Casella {
    
    private Rectangle2D.Float rec;
    private Color col;
    private Boolean ocupada;
    private Pesa pesa;

    public Casella(Rectangle2D.Float r, Color c, Boolean ocu ) {
        this.rec = r;
        this.col = c;
        this.ocupada = ocu;
        this.pesa = null;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.col);
        g2d.fill(this.rec);
        if (this.ocupada) {
            this.pesa.paintComponent(g, this.rec.x, this.rec.y);
        }
    }

    void setPesa(Pesa s) {
        this.ocupada = true;
        this.pesa = s;
    }

    public Rectangle2D.Float getRec() {
        return rec;
    }

    boolean isOcupada() {
        return ocupada;
    }

    void setOcupada(boolean b) {
        ocupada = b;
    }

}
