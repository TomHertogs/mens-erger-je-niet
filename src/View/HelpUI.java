package View;

import Controller.Spel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by school on 11-2-14.
 */
public class HelpUI extends JFrame {
    Spel spel = new Spel();

    public HelpUI(){
        hulpScherm();
    }

    private void hulpScherm(){
        String hulp;
        JLabel hulpTekst;

        hulp = spel.toonHulp();
        hulpTekst = new JLabel("Spelregels", SwingConstants.CENTER);
        hulpTekst.setText(hulp);
        hulpTekst.setFont(new Font("Arial", Font.PLAIN, 16));
        hulpTekst.setVerticalAlignment(SwingConstants.CENTER);
        hulpTekst.setBorder(new EmptyBorder(0,10,10,0));

        add(hulpTekst);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setTitle("Spelregels");
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
