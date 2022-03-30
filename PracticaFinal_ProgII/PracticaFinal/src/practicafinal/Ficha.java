/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicafinal;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Pavel Garcia
 */
public class Ficha {
    private BufferedImage img;
    
    public Ficha() {
        try {
            img = ImageIO.read(new File("fichanegra2.png"));
        } catch (IOException e) {
        }
    }
    
     void paintComponent(Graphics g, float x, float y) {
        g.drawImage(img, (int) x + 5, (int) y + 5, null);
    }
}
