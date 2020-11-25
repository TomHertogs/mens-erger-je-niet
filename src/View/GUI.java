package View;


import Controller.Spel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Plaatst alle grafische componenten van het spel op het speelbord
 */
public class GUI extends JFrame {
    private Spel spel;
    private JPanel spelbord;
    private DobbelUI dobbelUI;
    private BordUI bordUI = new BordUI(spel);
    private HelpUI helpUI;
    private JPanel[] spelerPanel = new JPanel[4];
    private JLabel[] kleurenLabel = new JLabel[4];
    private JLabel[] spelerLabel = new JLabel[4];
    private JLabel[] soortLabel = new JLabel[4];
    private JLabel[] scoreLabel = new JLabel[4];
    private JLabel dobbelLabel;

    public GUI(Spel spel) {
        this.spel = spel;
        plaatsBord();
        plaatsKnoppen();
        plaatsViews();
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setTitle("Mens Erger Je Niet");
        addWindowListener(new BenJeZeker());
        setVisible(true);
    }

    public GUI() {
        Sluiten();
    }

    public void plaatsBord() {
        spelbord = new BordUI(spel);
        spelbord.setBackground(new Color(224, 192, 128));
        add(spelbord, BorderLayout.CENTER);
    }

    public void Sluiten() {
        dispose();
    }

    public void plaatsKnoppen() {
        JPanel southContainer = new JPanel(new GridLayout(1, 3, 20, 20));
        southContainer.setBackground(new Color(224, 192, 128));

        JButton startKnop = new JButton("Start Spel");
        startKnop.setBackground(new Color(59, 89, 182));
        startKnop.setForeground(Color.white);
        JButton stopKnop = new JButton("Stoppen");
        stopKnop.setBackground(new Color(59, 89, 182));
        stopKnop.setForeground(Color.white);
        JButton hulpKnop = new JButton("Spelregels");
        hulpKnop.setBackground(new Color(59, 89, 182));
        hulpKnop.setForeground(Color.white);

        startKnop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spel.startBeurt();
            }
        });

        stopKnop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] keuzes = {"Ja, ik wil stoppen met spelen", "Nee, ik wil verder spelen"};
                Object defaultkeuze = keuzes[1];
                int optie = JOptionPane.showOptionDialog(
                        getContentPane(),
                        "Bent u zeker dat u wilt stoppen?",
                        "Sluit venster", JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE, null, keuzes,
                        defaultkeuze);
                if (optie == JOptionPane.YES_OPTION) {
                    new IntroScherm();
                    dispose();
                }
            }
        });

        hulpKnop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                helpUI = new HelpUI();
            }
        });

        southContainer.add(startKnop);
        southContainer.add(stopKnop);
        southContainer.add(hulpKnop);

        add(southContainer, BorderLayout.SOUTH);
    }


    public void plaatsViews() {
        JPanel eastContainer = new JPanel();
        eastContainer.setBackground(new Color(224, 192, 128));
        eastContainer.setLayout(new BoxLayout(eastContainer, BoxLayout.Y_AXIS));

        JPanel spelerContainer = new JPanel(new GridLayout(4, 1));
        spelerContainer.setBackground(new Color(224, 192, 128));

        JPanel dobbelContainer = new JPanel(new GridLayout(2, 1));
        dobbelContainer.setMaximumSize(new Dimension(100, 10));
        dobbelContainer.setBackground(new Color(224, 192, 128));

        JPanel rondeContainer = new JPanel(new GridLayout(1, 1));
        rondeContainer.setBackground(new Color(224, 192, 128));

        for (int i = 0; i < 4; i++) {
            spelerPanel[i] = new JPanel(new GridLayout(1, 4));
            spelerPanel[i].setBackground(new Color(224, 192, 128));
        }


        dobbelLabel = new JLabel(new ImageIcon(getClass().getResource("../Afbeeldingen/dummyDice.png")));

        dobbelLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                spel.werpDobbelsteen();
                dobbelUI = new DobbelUI(spel.getWorp());
                dobbelLabel.setIcon(new ImageIcon(getClass().getResource(dobbelUI.laatWorpZien())));
                spel.checkWorp();
                if(!spel.isWorpZes()){
                    spel.checkEindePositie();
                }
                else if(!spel.isWorpZes() || !spel.eindePositie()){
                    spel.zetPion();
                }
                spel.duidSpelerAan();
                spel.startBeurt();
                bordUI.repaint();

            }
        });

        JLabel rondeLabel = new JLabel();
        rondeLabel.setText("We zijn aan ronde " + String.valueOf(spel.toonRonde()));
        rondeLabel.setFont(new Font("Arial Black", Font.BOLD, 12));


        try {
            for (int i = 0; i < spel.getSpelers().size(); i++) {
                kleurenLabel[i] = new JLabel(String.valueOf(spel.getSpelers().get(i).getColor()));
                spelerLabel[i] = new JLabel(spel.getSpelers().get(i).getNaam());
                soortLabel[i] = new JLabel(spel.getSpelers().get(i).getSoort());
                scoreLabel[i] = new JLabel(String.valueOf(spel.getSpelers().get(i).getScore()));
            }
        } catch (IndexOutOfBoundsException e) {
            for (int i = 0; i < 4; i++) {
                kleurenLabel[i] = new JLabel("GeenSpeler");
                spelerLabel[i] = new JLabel("GeenSpeler");
                soortLabel[i] = new JLabel("GeenSpeler");
                scoreLabel[i] = new JLabel("GeenSpeler");
            }
        }

        rondeContainer.add(rondeLabel);

        for (int i = 0; i < spel.getSpelers().size(); i++) {
            spelerPanel[i].add(kleurenLabel[i]);
            spelerPanel[i].add(spelerLabel[i]);
            spelerPanel[i].add(soortLabel[i]);
            spelerPanel[i].add(scoreLabel[i]);
            spelerPanel[i].setPreferredSize(new Dimension(350, 40));
        }


        for (int i = 0; i < 4; i++) {
            spelerContainer.add(spelerPanel[i]);
        }

        dobbelContainer.add(dobbelLabel);

        eastContainer.add(rondeContainer, BorderLayout.NORTH);
        eastContainer.add(Box.createVerticalStrut(70));
        eastContainer.add(spelerContainer, BorderLayout.CENTER);
        eastContainer.add(Box.createVerticalStrut(50));
        eastContainer.add(dobbelContainer, BorderLayout.SOUTH);

        add(eastContainer, BorderLayout.EAST);
    }

    private class BenJeZeker extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            Object[] keuzes = {"Ja, ik wil stoppen met spelen", "Nee, ik wil verder spelen"};
            Object defaultkeuze = keuzes[1];
            int optie = JOptionPane.showOptionDialog(
                    getContentPane(),
                    "Bent u zeker dat u wilt stoppen?",
                    "Sluit venster", JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE, null, keuzes,
                    defaultkeuze);
            if (optie == JOptionPane.YES_OPTION) {
                new IntroScherm();
                dispose();
            }
        }
    }
}
