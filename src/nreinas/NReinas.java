/*
 * Programa que defineix un tauler d'escacs dins un marc i posa vuit reines
 * on indica l'usuari
 *
 */
package nreinas;

/**
 *
 * @author miquelmascaro
 */
import java.awt.Dimension;
import java.awt.Font;
import nreinas.Tauler;
import nreinas.Pesa;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class NReinas extends JFrame implements MouseListener {

    Tauler tauler;
    int numReines = 0;
    JPanel lateral;
    JButton aceptar,borrar;
    JLabel texto;
    int n;

    public NReinas(int n) {
        
        super("Joc de les reines");
        texto=new JLabel("ALGORITMO DE LAS N REINAS");
        texto.setFont(new Font("Arial", Font.PLAIN, 18));
        lateral=new JPanel();
        tauler = new Tauler(n);
        tauler.addMouseListener(this);
        borrar=new JButton("Voler a Empezar");
        borrar.setBounds(900, 550, 200, 75);
        aceptar=new JButton("ACEPTAR");
        aceptar.setBounds(900, 650, 200, 75);
        lateral.setBounds(800,0,400, 827);
        lateral.add(aceptar);
        lateral.add(texto);
        this.setPreferredSize(new Dimension(1200, 827));
        this.getContentPane().add(aceptar);
        this.getContentPane().add(borrar);
        this.getContentPane().add(lateral);
        this.getContentPane().add(tauler);
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(!tauler.haypieza()){
                    JOptionPane.showMessageDialog(null,"Primero coloca una reina") ;
                }
                else{
                   solucion(tauler,n,1);
                   tauler.repaint();
                }
            }
        });
        borrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(!tauler.haypieza()){
                    JOptionPane.showMessageDialog(null,"Ya no hay piezas") ;
                }
                else{
                   tauler.buidatot();
                   tauler.repaint();
                }
            }
        });
    }

    public static void main(String[] args) {
        String n = JOptionPane.showInputDialog ( "Introduzca un número:" ); 
        NReinas esc = new NReinas(Integer.parseInt(n));
        esc.setVisible(true);
    }
    public static boolean solucion(Tauler tauler,int n,int reinas){
        if(reinas==n){
            return true;
        }
        for (int f=0;f<n;++f){
            for(int c=0;c<n;++c){
                if(is_posible(tauler,f,c,n)){
                    tauler.Posa("reinaN.png", f, c);
                    if(solucion(tauler, n,reinas+1)){
                        return true;
                    }
                    tauler.buida(f, c);
                }
            }
        }
        return false;
    }
    public static boolean is_posible(Tauler tauler,int f,int c,int n) {
        //miramos fila
        for(int i=0;i<n;i++){
            if(tauler.isOcupat(f, i)){
                return false;
            }
        }
        //miramos columnas
        for(int j=0;j<n;j++){
            if (tauler.isOcupat(j, c)){
                return false;
            }
        }
        int i;
        int j;
        //diagonal ascendente izquierda
        for (i = f, j = c; j >= 0 && i < n; i++, j--){
            if (tauler.isOcupat(i,j)){
                return false;
            }
        }
        //diagonal descendente izquierda
        for (i = f, j = c; i >= 0 && j >= 0; i--, j--){
            if (tauler.isOcupat(i,j)){
                return false;
            }
        }
        //diagonal ascendente derecha
        for (i = f, j = c; i >= 0 && j < n; i--, j++) {
            if (tauler.isOcupat(i,j)) {
                return false;
            }
        }
       //diagonal descente derecha
        for (i = f, j = c; i < n && j < n; i++, j++) {
            if (tauler.isOcupat(i,j)) {
                return false;
            }
        }
        return true;
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        
        int x = 0, y = 0, i, j = 0;
         if (e.getButton() == MouseEvent.BUTTON1 && numReines < 8) {
            x = e.getX();
            y = e.getY();
            boolean trobat = false;
            for (i = 0; i < Tauler.DIMENSIO && !trobat; i++) {
                for (j = 0; j < Tauler.DIMENSIO && !trobat; j++) {
                    trobat = tauler.dinsCasella(i, j, x, y);
                }
            }
            i--;
            j--;
            System.out.println("Click a: " + i + ", " + j);
            if (!tauler.isOcupat(i, j)) {
                tauler.buidatot();
                tauler.Posa(Pesa.REINAN, i, j);
                numReines++;
            } else {
                Toolkit.getDefaultToolkit().beep();
                tauler.buida(i, j);
                numReines--;
            }
            tauler.repaint();
            if (numReines == 8) {
                Toolkit.getDefaultToolkit().beep();
                ImageIcon icon = new ImageIcon("reinaN.png");
                JOptionPane.showMessageDialog(null, "8 Reines al tauler!",
                        "Victoria!", JOptionPane.INFORMATION_MESSAGE, icon);
            }
        }
    }
    @Override
    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        @Override
    public void mouseClicked(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
