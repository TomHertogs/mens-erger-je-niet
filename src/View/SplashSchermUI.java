package View;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by PATRICK on 18/02/14.
 * Introscherm waar je een spel kan starten, spelers/computers kan toevoegen of de spelregels lezen
 */
public class SplashSchermUI extends JWindow {
    private Clip clip;
    JProgressBar progressBar = new JProgressBar(0, 100);
    private static int count = 1, TIMER_PAUSE = 10, PROGBAR_MAX = 100;
    private static Timer progressBarTimer;
    Font font = new Font("Arial Black", Font.BOLD, 12);

    ActionListener al = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            progressBar.setValue(count);
            if (count == 1) {
                progressBar.setString("Mens Erger Je Niet uitpakken.");
            }
            if (count == 10) {
                progressBar.setString("Mens Erger Je Niet uitpakken..");
            }
            if (count == 19) {
                progressBar.setString("Mens Erger Je Niet uitpakken...");
            } else if (count == 25) {
                progressBar.setString("Inlezen van Klassen.");
            } else if (count == 34) {
                progressBar.setString("Inlezen van Klassen..");
            } else if (count == 43) {
                progressBar.setString("Inlezen van Klassen...");
            } else if (count == 50) {
                progressBar.setString("Inlezen van Methoden.");
            } else if (count == 59) {
                progressBar.setString("Inlezen van Methoden..");
            } else if (count == 68) {
                progressBar.setString("Inlezen van Methoden...");
            } else if (count == 75) {
                progressBar.setString("Tom kijkt het project even na.");
            } else if (count == 79) {
                progressBar.setString("Tom kijkt het project even na..");
            } else if (count == 83) {
                progressBar.setString("Tom kijkt het project even na...");
            } else if (count == 87) {
                progressBar.setString("Patrick bekijkt het project ook en besluit het af te ronden.");
            } else if (count == 91) {
                progressBar.setString("Patrick bekijkt het project ook en besluit het af te ronden..");
            } else if (count == 95) {
                progressBar.setString("Patrick bekijkt het project ook en besluit het af te ronden...");
            }

            System.out.println(count);
            if (PROGBAR_MAX == count) {
                dispose();
                progressBarTimer.stop();//stop the timer
                clip.stop();
                new IntroScherm();
            }
            count++;
        }
    };


    public SplashSchermUI(String afbeelding) {
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(getClass().getResource("/geluid/mario.wav")));
            clip.start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        JLabel label = new JLabel(new ImageIcon(getClass().getResource(afbeelding)));
        progressBar.setMaximum(PROGBAR_MAX);
        progressBar.setFont(font);
        progressBar.setStringPainted(true);
        progressBar.setForeground(Color.BLUE);
        progressBar.setBackground(Color.YELLOW);
//        progressBar.setString("Opzetten van het Spel");
        getContentPane().add(label, BorderLayout.CENTER);
        getContentPane().add(progressBar, BorderLayout.SOUTH);
        startProgressBar();
        pack();
        setSize(new Dimension(848, 652));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void startProgressBar() {
        progressBarTimer = new Timer(TIMER_PAUSE, al);
        progressBarTimer.start();
    }
}
