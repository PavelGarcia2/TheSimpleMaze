/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicafinal;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 *
 * @author Pavel Garcia
 */
public class Casilla {

    //informacion sobre las paredes de la casilla
    private int pNorte;
    private int pEste;
    private int pSur;
    private int pOeste;
    //indica si la meta esta situada en la casilla
    private boolean meta;
    //indica si la ficha jugador esta situada
    private boolean fJug;
    private boolean pisada;
    private static boolean verPisadas=false;
    //Parte visual del Juego
    private static Color cFondo ;
    private static Color cPared ;
    BasicStroke atributoStroke = new BasicStroke(8, BasicStroke.CAP_SQUARE, BasicStroke.CAP_SQUARE, 2.0f);
    private Rectangle2D.Float rec;
    private BufferedImage img;
    
    //MÉTODOS CONSTRUCTORES
    /**
     * *******************************************************
     * Function: metodo constructor que intancia un objeto Casilla. Return:
     *
     * ******************************************************* @param pN
     * @param pE
     * @param pS
     * @param pO
     * @param m
     */
    public Casilla(Rectangle2D.Float r, int pN, int pE, int pS, int pO) {
        try {
            this.img = ImageIO.read(new File("exit3.png"));
        } catch (IOException ex) {
            Logger.getLogger(Casilla.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.rec = r;
        pNorte = pN;
        pEste = pE;
        pSur = pS;
        pOeste = pO;
        meta = false;
        fJug=false;
        pisada=false;
        cFondo=Color.orange;
        cPared=Color.BLACK;
    }

    public Casilla() {
    }

    //MÉTODOS FUNCIONALES
    /**
     * *******************************************************
     * Function: metodo que pinta una casilla y sus paredes Return: void
     *
     * ******************************************************* @param g
     */
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        //dibujamos el fondo de las casillas
        g.setColor(cFondo);

        g2d.fillRect((int) this.rec.x, (int) this.rec.y + 1, (int) this.rec.width, (int) this.rec.height + 1);
        if(meta){
            g.setColor(Color.white);
            
            g2d.fillRect((int) this.rec.x, (int) this.rec.y + 1, (int) this.rec.width, (int) this.rec.height + 1);
            g.drawImage(img, (int)this.rec.x+9, (int)this.rec.y+15, null);
        }else if (pisada && verPisadas) {
            g.setColor(Color.green);
            g2d.fillRect((int) this.rec.x, (int) this.rec.y + 1, (int) this.rec.width, (int) this.rec.height + 1);
            
        }
        
        //dibujamos las paredes
        g.setColor(cPared);
        g2d.setStroke(atributoStroke);
        if (pNorte == 1) {
            g.drawLine((int) this.rec.x + 4, (int) this.rec.y + 5, (int) this.rec.x + (int) this.rec.width - 4, (int) this.rec.y + 5);

        }
        if (pEste == 1) {
            g.drawLine((int) this.rec.x + (int) this.rec.width - 4, (int) this.rec.y + 5, (int) this.rec.x + (int) this.rec.width - 4, (int) this.rec.y + (int) this.rec.height - 2);
        }
        if (pSur == 1) {
            g.drawLine((int) this.rec.x + 4, (int) this.rec.y + (int) this.rec.height - 2, (int) this.rec.x + (int) this.rec.width - 4, (int) this.rec.y + (int) this.rec.height - 2);
        }
        if (pOeste == 1) {
            g.drawLine((int) this.rec.x + 4, (int) this.rec.y + 5, (int) this.rec.x + 4, (int) this.rec.y + (int) this.rec.height - 2);

        }
    }

    //SETTERS
    public void setMeta(boolean meta) {
        this.meta = meta;
    }

    public void setfJug(boolean fJug) {
        this.fJug = fJug;
    }

    public void setcFondo(Color cFondo) {
        this.cFondo = cFondo;
    }

    public void setcPared(Color cPared) {
        this.cPared = cPared;
    }

    public boolean isPisada() {
        return pisada;
    }

    public void setPisada(boolean pisada) {
        this.pisada = pisada;
    }

    public  void setVerPisadas(boolean verPisadas) {
        this.verPisadas = verPisadas;
    }
    
    

    public Rectangle2D.Float getRec() {
        return rec;
    }

    public int getpNorte() {
        return pNorte;
    }

    public int getpEste() {
        return pEste;
    }

    public int getpSur() {
        return pSur;
    }

    public int getpOeste() {
        return pOeste;
    }
    
    
    

}
