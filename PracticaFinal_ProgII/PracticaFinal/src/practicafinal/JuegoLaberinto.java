


package practicafinal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Pavel Garcia
 */
public class JuegoLaberinto extends JFrame {

    private JFrame ventana;
    private final int ANCHO_VENTANA = 418;
    private final int ALTO_VENTANA = 672;
    private Color colorFondo;

    //JPanel /JComponents/JMenu...
    private static Laberinto gridLaberinto;
    private static JMenuBar barraMenu;
    private static JMenuItem ficheroMenu;
    private static JMenuItem cFondoMenu;

    //posición columna y fila de la meta
    private int xMeta;
    private int yMeta;
    //posición columna y fila de la ficha
    private int xFicha;
    private int yFicha;

    private File fichLab; //buffer de lectura de donde leeremos la informacion del laberinto

    //METODOS CONSTRUCTORES
    /**
     * Function: Metodo constructor que instancia un objeto Laberinto Return:
     * void
     */
    public JuegoLaberinto()  {
        super("Laberinto");
        initComponents();
        this.setSize(new Dimension(ANCHO_VENTANA, ALTO_VENTANA));
        //this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args)  {
        // TODO code application logic here
        JuegoLaberinto juego = new JuegoLaberinto();
        juego.setVisible(true);
    }

    //MÉTODOS FUNCIONALES
    /**
     * Function: Metodo que incializa todas las componentes del laberinto
     * Return: void
     */
    private void initComponents()  {

        ActionListener gestorEventos = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                switch (evento.getActionCommand()) {
                    case "Fichero":
                        JFileChooser ventanaSeleccionImagen2 = new JFileChooser();

                        if (ventanaSeleccionImagen2.showOpenDialog(ventana) == JFileChooser.APPROVE_OPTION) {
                            fichLab = ventanaSeleccionImagen2.getSelectedFile();
                            {
                                JuegoLaberinto.this.getContentPane().remove(gridLaberinto);
                                try {
                                    gridLaberinto = new Laberinto(fichLab);
                                    JuegoLaberinto.this.getContentPane().add(gridLaberinto);
                                    gridLaberinto.repaint();
                                    JuegoLaberinto.this.getContentPane().validate();

                                } catch (IOException ex) {
                                    Logger.getLogger(JuegoLaberinto.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                        }

                        
                        break;
                    case "Color Fondo":
                        colorFondo = JColorChooser.showDialog(JuegoLaberinto.this,
                                "SELECCIONAR UN COLOR", colorFondo);
                        gridLaberinto.setCFondo(colorFondo);
                        gridLaberinto.repaint();
                        break;

                }

            }
        };

        KeyListener evtTeclado = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case 'w':
                    case 'W':
                        gridLaberinto.moverFicha(-1, 0);
                        break;
                    case 'd':
                    case 'D':
                        ;
                        gridLaberinto.moverFicha(0, 1);
                        break;
                    case 's':
                    case 'S':
                        gridLaberinto.moverFicha(1, 0);
                        break;
                    case 'a':
                    case 'A':
                        gridLaberinto.moverFicha(0, -1);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        };

        this.addKeyListener(evtTeclado);

        this.setLayout(new BorderLayout()); //establecemos el layout de la ventana
        barraMenu = new JMenuBar();
        ficheroMenu = new JMenuItem("Fichero");
        ficheroMenu.addActionListener(gestorEventos);
        cFondoMenu = new JMenuItem("Color Fondo");
        cFondoMenu.addActionListener(gestorEventos);
        barraMenu.add(ficheroMenu);
        barraMenu.add(cFondoMenu);

        try {
            //añadimos o inicializamos el laberinto
            gridLaberinto = new Laberinto(new File("maze1.txt"));
        } catch (IOException ex) {
            Logger.getLogger(JuegoLaberinto.class.getName()).log(Level.SEVERE, null, ex);
        }
        //gridLaberinto = new Laberinto();
        this.getContentPane().add(gridLaberinto, BorderLayout.CENTER);

        this.getContentPane().add(barraMenu, BorderLayout.NORTH); //añadimos el menu a la ventana

    }

}
