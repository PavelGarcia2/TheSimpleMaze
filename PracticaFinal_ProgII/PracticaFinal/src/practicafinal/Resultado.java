/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicafinal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Pavel Garcia
 */
public class Resultado extends JFrame {

    private JTextField jtexto;
    private JPanel jpBotones;
    
    private JButton btnAceptar;

    public Resultado() {
        new JFrame();
        this.setLayout(new BorderLayout());
        this.setSize(300, 200);
        this.setLocationRelativeTo(null);
        jtexto = new JTextField();

        jtexto.setText("     HAS GANADO");
        jtexto.setForeground(Color.green);
        jtexto.setFont(new Font("Dialog", Font.BOLD, 30));
        jtexto.setEditable(false);

        jpBotones = new JPanel();

        btnAceptar = new JButton();
        btnAceptar.setText("Aceptar");
        btnAceptar.setBackground(Color.white);
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }

        });

        //jpBotones.add(jtexto);
        jpBotones.add(btnAceptar);
 

        this.getContentPane().add(jtexto, BorderLayout.CENTER);
        this.getContentPane().add(jpBotones, BorderLayout.SOUTH);
        this.setResizable(false);
        this.setVisible(true);

    }
}
