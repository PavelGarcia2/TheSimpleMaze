/*
*
*
 */
package practicafinal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JPanel;

/**
 *
 * @author Pavel Garcia
 */
public class Laberinto extends JPanel {

    private final int LADO = 40; //es el alto y ancho de cada casilla 
    private int numCo;              //numero de columnas del laberinto
    private int numFi;              //numero de filas del laberinto
    private int xSalida;
    private int ySalida;
    private int iJug;
    private int jJug;
    private boolean terminada = false;
    private Casilla lab[][];  //conjunto de todos las casillas del laberinto
    private Ficha fit;

    public Laberinto(File f) throws FileNotFoundException, IOException {

        BufferedReader fLab; //buffer asociado al fichero que contiene la informacion del laberinto 
        int x = this.getX();
        int y = this.getY();
        fLab = new BufferedReader(new FileReader(f));
        numFi = Integer.parseInt(fLab.readLine()); //leemos el numero de filas que tendra el laberinto
        numCo = Integer.parseInt(fLab.readLine()); //leemos el numero de columnas que tendra el laberinto
        //inicilaizamos la matriz de casillas con su tamaño
        lab = new Casilla[numFi][numCo];

        //ahora leeremos los datos del laberinto
        //hay tantas lineas como filas hay y en cada fila hay
        //tantas casillas como columnas
        for (int i = 0; i < numFi; i++) {
            //estamos en la i-esima fila
            String aux = fLab.readLine();
            int m = 0;
            for (int j = 0; j < numCo; j++) {
                //estamos en la i-esima fila,en la j-esima casilla
                int pN = Character.getNumericValue(aux.charAt(m));
                m++;

                int pE = Character.getNumericValue(aux.charAt(m));
                m++;

                int pS = Character.getNumericValue(aux.charAt(m));
                m++;

                int pO = Character.getNumericValue(aux.charAt(m));
                m++;

                Rectangle2D.Float r = new Rectangle2D.Float(x, y, LADO, LADO);
                lab[i][j] = new Casilla(r, pN, pE, pS, pO);
                x += LADO;
            }
            y += LADO;
            x = this.getX();
        }

        //leemos la posicion de la salida y la inicializamos
        lab[iJug][jJug].setVerPisadas(false);
        xSalida = Integer.parseInt(fLab.readLine());
        ySalida = Integer.parseInt(fLab.readLine()) - 1;
        lab[xSalida][ySalida].setMeta(true);

        //Ponemos al jugador en un punto aleatorio del laberinto
        inicializarFicha();
        //cerramos el stream
        fLab.close();

    }

    public Laberinto() {
        numCo = 0;
        numFi = 0;
    }

    @Override
    public void paintComponent(Graphics g) {

        if (terminada) {

        }
        for (int i = 0; i < numFi; i++) {
            for (int j = 0; j < numCo; j++) {

                lab[i][j].paintComponent(g);
            }
        }
        float x = lab[iJug][jJug].getRec().x;
        float y = lab[iJug][jJug].getRec().y;
        fit.paintComponent(g, x, y);

    }

    private void inicializarFicha() {
        fit = new Ficha();
        iJug = (int) Math.floor(Math.random() * ((numFi - 1) - 0 + 1) + 0);
        jJug = (int) Math.floor(Math.random() * ((numCo - 1) - 0 + 1) + 0);
        lab[iJug][jJug].setfJug(true);
        lab[iJug][jJug].setPisada(true);
    }

    public void moverFicha(int i, int j) {

        Casilla act = lab[iJug][jJug];
        if (((i == -1 && act.getpNorte() != 1) || (i == 1 && act.getpSur() != 1) || (j == -1 && act.getpOeste() != 1) || (j == 1 && act.getpEste() != 1)) && !terminada) {
            lab[iJug][jJug].setfJug(false);
            iJug += i;
            jJug += j;
            lab[iJug][jJug].setfJug(true);
            lab[iJug][jJug].setPisada(true);
            this.repaint();
            if (iJug == xSalida && jJug == ySalida) {
                terminada = true;
                lab[iJug][jJug].setVerPisadas(true);
                //mostramos la ventana de victoria
                Resultado ventana = new Resultado();
            }

        }

    }

    public void setCFondo(Color c) {
        lab[0][0].setcFondo(c);

    }

    public void setCPared(Color c) {
        lab[0][0].setcPared(c);

    }

    public void setiJug(int iJug) {
        this.iJug = iJug;
    }

    public void setjJug(int jJug) {
        this.jJug = jJug;
    }

    public int getiJug() {
        return iJug;
    }

    public int getjJug() {
        return jJug;
    }

}
