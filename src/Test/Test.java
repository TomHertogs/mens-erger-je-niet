package Test;

import Controller.Spel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by PATRICK on 19/02/14.
 */
public class Test extends JFrame {
    private Spel spel;
    private final int DIMENSION = 11;
    int[][] xPos = new int[DIMENSION][DIMENSION];
    int[][] yPos = new int[DIMENSION][DIMENSION];

    public Test() {
        ToonScherm();
    }

    public void paintComponents(Graphics g) {
        //de grote panel:
        JPanel GrootPanel = new JPanel(new GridLayout(DIMENSION, DIMENSION));

        //de kleinere panels
        JPanel[][] PatroonPanel = new JPanel[DIMENSION][DIMENSION];

        //maakt de panels aan:
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                PatroonPanel[i][j] = new JPanel();
            }
        }

        //zet de panels in de grote panel
        for (int i = 0; i < PatroonPanel.length; i++) {
            for (int j = 0; j < PatroonPanel.length; j++) {
                GrootPanel.add(PatroonPanel[i][j]);
            }
        }
        GrootPanel.setBackground(new Color(224, 192, 128));
        add(GrootPanel);


        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        for (int i = 0; i < PatroonPanel.length; i++) {
            for (int j = 0; j < PatroonPanel[i].length; j++) {

                GrootPanel.add(PatroonPanel[i][j]);
                //De  vier kleuren van rood  in vier aparte panels
                if ((i == 0 && j == 0) || (i == 0 && j == 1) || (i == 1 && j == 0) || (i == 1 && j == 1)) {
                    g.setColor(new Color(180, 0, 0));
                    g.fillOval(this.xPos[i][j], this.yPos[i][j], 35, 35);
                }
                //de vier kleuren van blauw in vier aparte panels
                else if ((i == 0 && j == 9) || (i == 0 && j == 10) || (i == 1 && j == 9) || (i == 0 && j == 10)) {
                    g.setColor(new Color(0, 0, 180));
                    g.fillOval(this.xPos[i][j], this.yPos[i][j], 35, 35);
                }
                //de vier kleuren van geel in vier aparte panels (linksonderaan)
                else if ((i == 9 && j == 0) || (i == 9 && j == 1) || (i == 10 && j == 0) || (i == 10 && j == 1)) {
                    g.setColor(new Color(255, 200, 0));
                    g.fillOval(this.xPos[i][j], this.yPos[i][j], 35, 35);
                }
                //de vier kleuren van groen in vier aparte panels (rechtsonderaan)
                else if ((i == 9 && j == 9) || (i == 9 && j == 10) || (i == 10 && j == 9) || (i == 10 && j == 10)) {
                    g.setColor(new Color(0, 180, 0));
                    g.fillOval(this.xPos[i][j], this.yPos[i][j], 35, 35);
                }
                //het pad van de witte cirkels in het spelpatroon
                else if ((i == 0 && j == 4) || (i == 0 && j == 5) || (i == 0 && j == 6) || // rij 1
                        (i == 1 && j == 4) || (i == 1 && j == 6) || // rij 2
                        (i == 2 && j == 4) || (i == 2 && j == 6) || // rij 3
                        (i == 3 && j == 4) || (i == 3 && j == 6) || // rij 4
                        (i == 4 && j != 5) || // rij 5
                        (i == 5 && j == 0) || (i == 5 && j == 10) || // rij 6
                        (i == 6 && j != 5) || //rij 7
                        (i == 7 && j == 4) || (i == 7 && j == 6) || //rij 8
                        (i == 8 && j == 4) || (i == 8 && j == 6) || //rij 9
                        (i == 9 && j == 4) || (i == 9 && j == 6) || //rij 10
                        (i == 10 && j == 4) || (i == 10 && j == 5) || (i == 10 && j == 6)) // rij 11
                {
                    g.setColor(new Color(255, 250, 250));
                    g.fillOval(this.xPos[i][j], this.yPos[i][j], 35, 35);
                }
                // de vier kleuren van blauw in het spelpatroon:
                else if ((i == 1 && j == 5) || //rij 2 (begint pas bij rij 2
                        (i == 2 && j == 5) || //rij 3
                        (i == 3 && j == 5) || //rij 4
                        (i == 4)  //rij 5
                        ) {
                    g.setColor(new Color(0, 0, 180));
                    g.fillOval(this.xPos[i][j], this.yPos[i][j], 35, 35);
                }
                // de vier kleuren voor rood in het spelpatroon:
                else if (i == 5 && j < 5) {
                    g.setColor(new Color(180, 0, 0));
                    g.fillOval(this.xPos[i][j], this.yPos[i][j], 35, 35);
                }
                // de vier kleuren voor groen in het spelpatroon:
                else if (i == 5 && j < 10 && j > 5) {
                    g.setColor(new Color(0, 180, 0));
                    g.fillOval(this.xPos[i][j], this.yPos[i][j], 35, 35);
                }
                // de vier kleuren voor geel in het spelpatroon:
                else if ((i == 6) || // rij 7
                        (i == 7 && j == 5) || // rij 8
                        (i == 8 && j == 5) || // rij 9
                        (i == 9 && j == 5)) {
                    g.setColor(new Color(255, 200, 0));
                    g.fillOval(this.xPos[i][j], this.yPos[i][j], 35, 35);
                }
            }
        }
        add(GrootPanel);
    }

    public void ToonScherm() {
        setTitle("spelpatroon");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1100, 700);
        setVisible(true);
    }
}

