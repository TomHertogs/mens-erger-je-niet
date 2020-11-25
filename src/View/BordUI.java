package View;

import Controller.Spel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by PATRICK on 18/02/14.
 * Voegt het bord toe aan het spel
 */
public class BordUI extends JPanel {
    private Spel spel;
    private final int VIER = 4;
    private BufferedImage imR[] = new BufferedImage[VIER];
    private BufferedImage imB[]= new BufferedImage[VIER];
    private BufferedImage imG[]= new BufferedImage[VIER];
    private BufferedImage imGr[]= new BufferedImage[VIER];

    public BordUI(Spel spel) {
        this.setPreferredSize(new Dimension(500, 500));
        this.spel = spel;
        laadPion();
    }

    public void laadPion() throws RuntimeException {
        try {
            for (int i = 0 ; i<VIER; i++){
            imR[i] = ImageIO.read(new File("src\\Afbeeldingen\\pion_rooddummy.png"));
            imB[i] = ImageIO.read(new File("src\\Afbeeldingen\\pion_blauwdummy.png"));
            imGr[i] = ImageIO.read(new File("src\\Afbeeldingen\\pion_groendummy.png"));
            imG[i] = ImageIO.read(new File("src\\Afbeeldingen\\pion_geeldummy.png"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        for (int i = 0; i < spel.getWitteCirkelsX().length; i++) {
            g2d.setColor(new Color(255, 250, 250));
            g2d.fillOval(spel.getWitteCirkelsX()[i], spel.getWitteCirkelsY()[i], 35, 35);
        }

        for (int i = 0; i < VIER; i++) {
            g2d.setColor(new Color(0,0,255));
            g2d.fillOval(spel.getBlauweBeginPositieX()[i], spel.getBlauweBeginPositieY()[i], 35, 35);
            g2d.fillOval(spel.getBlauweEindPositieX()[i], spel.getBlauweEindPositieY()[i], 35, 35);

            g2d.setColor(new Color(180, 0, 0));
            g2d.fillOval(spel.getRodeBeginPositieX()[i], spel.getRodeBeginPositieY()[i], 35, 35);
            g2d.fillOval(spel.getRodeEindPositieX()[i], spel.getRodeEindPositieY()[i], 35, 35);

            g2d.setColor(new Color(0, 180, 0));
            g2d.fillOval(spel.getGroeneBeginPositieX()[i], spel.getGroeneBeginPositieY()[i], 35, 35);
            g2d.fillOval(spel.getGroeneEindPositieX()[i], spel.getGroeneEindPositieY()[i], 35, 35);

            g2d.setColor(new Color(255, 200, 0));
            g2d.fillOval(spel.getGeleBeginPositieX()[i], spel.getGeleBeginPositieY()[i], 35, 35);
            g2d.fillOval(spel.getGeleEindPositieX()[i], spel.getGeleEindPositieY()[i], 35, 35);
        }

        /**
         * dit was een test om te zien of de pionnen werden getekent op het spelbord
         * voor de eigenlijke implementatie van de pionnen zullen we gebruik maken van methodes aangemaakt
         * in Bord
         */

        //tekent de pionnen op het spelbord:
        for (int i = 0; i < VIER; i++) {
                g2d.drawImage(imR[i], spel.getRodeBeginPositieX()[i] + 5, spel.getRodeBeginPositieY()[i], null, this);
                g2d.drawImage(imB[i], spel.getBlauweBeginPositieX()[i] + 5, spel.getBlauweBeginPositieY()[i], null, this);
                g2d.drawImage(imGr[i], spel.getGroeneBeginPositieX()[i] + 5, spel.getGroeneBeginPositieY()[i], null, this);
                g2d.drawImage(imG[i], spel.getGeleBeginPositieX()[i] + 5, spel.getGeleBeginPositieY()[i], null, this);

        }
    }
}
