/*
 * Classe Pesa que defineix les peces per jugar a escacs. Les peces són fitxers
 * d'imatges que representen les peces habituals.
 *
 * El constructor llegeix el fitxer amb la imatge
 *
 * El mètode paintComponent pinta la imatge a la posició indicada
 */
package nreinas;

/**
 *
 * @author miquelmascarooliver
 */

import java.awt.Graphics;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;

public class Pesa {

    public static final String REINAN = "reinaN.png";

    private BufferedImage img;

    public Pesa(String s) {
        try {
            img = ImageIO.read(new File(s));
        } catch (IOException e) {
        }
    }

    void paintComponent(Graphics g, float x, float y) {
        g.drawImage(img,(int) x + 10, (int) y + 10, null);
    }
}
