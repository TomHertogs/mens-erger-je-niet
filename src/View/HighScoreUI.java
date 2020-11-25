package View;

import Controller.Spel;

import javax.swing.*;

/**
 * Created by Tom Hertogs on 21-2-14.
 */
public class HighScoreUI extends JFrame {
    Spel spel;

    public HighScoreUI(Spel spel){
        this.spel = spel;
        String top10;
        JLabel highScores = new JLabel();

        top10 = spel.toonHighScores();
        highScores.setText(top10);

        add(highScores);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(500,500);
        setLocationRelativeTo(null);
        setTitle("HighScores");
        setVisible(true);
    }
}
