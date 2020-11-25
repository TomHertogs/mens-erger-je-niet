package View;

import Controller.Spel;
import dom.IO.HighScores;
import dom.spelers.Speler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Hertogs Tom
 * Date: 13/02/14
 * Time: 23:10
 * To change this template use File | Settings | File Templates.
 */
public class IntroScherm extends JFrame {
    //attributen
    private Spel spel = new Spel();
    HighScores highScores = new HighScores();

    private final int VIER = 4;

    private SpelerUI spelerUI;
    private HelpUI helpUI;
    private GUI gui;

    private JLabel achtergrond;
    private JLabel welkom;
    private JLabel mensLabel[] = new JLabel[VIER];
    private JLabel computerLabel[] = new JLabel[VIER];
    private JLabel hoeveelSpelers;

    private JPanel northContainer;
    private JPanel aantalSPanel;
    private JPanel SpelerKeuzePanel;

    private JButton[] isMens = new JButton[VIER];
    private JButton[] isComputer = new JButton[VIER];

    private JTextField[] geefNaamSp = new JTextField[VIER];
    private JTextField[] naamSp = new JTextField[VIER];

    private JPanel southContainer;
    private JPanel[] containtMadeSpeler = new JPanel[VIER];
    private JPanel[] spelerPanel = new JPanel[VIER];

    public IntroScherm() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1000, 620);
        setResizable(false);
        welkom = new JLabel("Welkom bij Mens Erger Je Niet, gemaakt door Tom Hertogs en Patrick Frison", SwingConstants.CENTER);
        welkom.setOpaque(true);
        welkom.setBackground(Color.ORANGE);
        setResizable(true);
        setMinimumSize(new Dimension(470,640));
        add(new imagePanel(), BorderLayout.CENTER);
        add(welkom, BorderLayout.NORTH);
        AanmaakKeuzeSpelerPanel();
        setNaamSp(naamSp);
        geefKleur();
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Mens Erger Je Niet");
        setVisible(true);
    }

    public void setNaamSp(JTextField[] naamSp){
        this.naamSp = naamSp;
        for(int i = 0; i < naamSp.length; i++){
            naamSp[i].setText("Speler " + (i+1));
        }
    }


    public void AanmaakKeuzeSpelerPanel() {
        SpelerKeuzePanel = new JPanel();
        SpelerKeuzePanel.setLayout(new BoxLayout(SpelerKeuzePanel, BoxLayout.Y_AXIS));
        SpelerKeuzePanel.setMinimumSize(new Dimension(260, 5));

        aantalSPanel = new JPanel();
        aantalSPanel.setLayout(new BoxLayout(aantalSPanel, BoxLayout.X_AXIS));

        hoeveelSpelers = new JLabel(spel.spelerHelpTekst());

        northContainer = new JPanel();
        northContainer.setLayout(new GridLayout(2, 1, 1, 1));
        southContainer = new JPanel();
        southContainer.setLayout(new GridLayout(4, 1));

        for (int i = 0; i < VIER; i++) {
            mensLabel[i] = new JLabel();
            computerLabel[i] = new JLabel();
            isMens[i] = new JButton("Mens");
            isComputer[i] = new JButton("Computer");
        }

        for (int i = 0; i < VIER; i++) {
            spelerPanel[i] = new JPanel();
            spelerPanel[i].setLayout(new GridLayout(1, 2));
        }
        for (int i = 0; i < VIER; i++) {
            spelerPanel[i].add(isMens[i]);
            spelerPanel[i].add(isComputer[i]);
        }

        for (int i = 0; i < VIER; i++) {
            geefNaamSp[i] = new JTextField("Geef de naam van speler " + (i + 1) + ":");
            geefNaamSp[i].setEditable(false);
        }

        for (int i = 0; i < VIER; i++) {
            naamSp[i] = new JTextField();
        }

        try {
            for (int i = 0; i < 4; i++) {
                containtMadeSpeler[i] = new JPanel(new GridLayout(3, 1));
                containtMadeSpeler[i].add(geefNaamSp[i]);
                containtMadeSpeler[i].add(naamSp[i]);
                containtMadeSpeler[i].add(isMens[i]);
                containtMadeSpeler[i].add(isComputer[i]);
            }
        } catch (NullPointerException e) {
            e.getStackTrace();
        }


        for (int i = 0; i < containtMadeSpeler.length; i++) {
            southContainer.add(containtMadeSpeler[i]);
        }

        northContainer.add(hoeveelSpelers);
        northContainer.add(aantalSPanel);
        SpelerKeuzePanel.add(northContainer, BorderLayout.NORTH);
        SpelerKeuzePanel.add(southContainer, BorderLayout.SOUTH);
        add(SpelerKeuzePanel, BorderLayout.EAST);
    }

    public void geefKleur() {
        northContainer.setBackground(new Color(224, 192, 128));
        southContainer.setBackground(new Color(224, 192, 128));
        hoeveelSpelers.setBackground(new Color(224, 192, 128));
        aantalSPanel.setBackground(new Color(224, 192, 128));
        for (int i = 0; i < VIER; i++) {
            containtMadeSpeler[i].setBackground(new Color(224, 192, 128));
        }
    }

    public void initComponents() {
        JPanel southContainer = new JPanel(new GridLayout(1, 3));
        JButton startKnop = new JButton("Start Spel");
        startKnop.setBackground(new Color(59, 89, 182));
        startKnop.setForeground(Color.white);
        JButton stopKnop = new JButton("Stoppen");
        stopKnop.setBackground(new Color(59, 89, 182));
        stopKnop.setForeground(Color.white);
        JButton hulpKnop = new JButton("Spelregels");
        hulpKnop.setBackground(new Color(59, 89, 182));
        hulpKnop.setForeground(Color.white);
        JButton highScores = new JButton("Highscores");
        highScores.setBackground(new Color(59, 89, 182));
        highScores.setForeground(Color.white);

        startKnop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui = new GUI(spel);
                dispose();
            }
        });

        for (int i = 0; i < VIER; i++){
            final int temp = i;
            isMens[temp].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   spel.maakSpeler(naamSp[temp].getText(), Speler.Soort.MENS);
            }
            });
        }

        for (int i = 0; i < VIER; i++){
            final int temp = i;
            isComputer[temp].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    spel.maakSpeler(naamSp[temp].getText(), Speler.Soort.COMPUTER);
                }
            });
        }


        stopKnop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        hulpKnop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                helpUI = new HelpUI();
            }
        });

        highScores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HighScoreUI highScoreUI = new HighScoreUI(spel);
            }
        });

        southContainer.add(startKnop);
        southContainer.add(stopKnop);
        southContainer.add(hulpKnop);
        southContainer.add(highScores);

        add(southContainer, BorderLayout.SOUTH);
    }

    /**
     * getClass().getResourceAsStream("pad van uw tekst bestand")
     */
    class imagePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Afbeeldingen/introAchtergrond.png"));
            Image image = imageIcon.getImage();
            super.paintComponent(g);
            if (image != null) {
//            image resizen aan afmetingen JPanel:
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

//                image niet resizen:
//                g.drawImage(image, 0, 0, image.getWidth(this), image.getHeight(this), this);
            }
        }
    }
}
