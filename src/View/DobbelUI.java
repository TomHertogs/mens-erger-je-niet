package View;

import javax.swing.*;

/**
 * Deze klasse bevat de verschillende mogelijke images die een worp van de dobbelsteen laten zien
 */
public class DobbelUI extends JPanel{
    private int worp;
    private String geroldeWorp;

    public DobbelUI(int worp){
        this.worp = worp;
    }

    public String laatWorpZien(){
        switch (this.worp){
            case 1 : geroldeWorp = "../Afbeeldingen/dice_1.png";
                break;
            case 2 : geroldeWorp = "../Afbeeldingen/dice_2.png";
                break;
            case 3 : geroldeWorp = "../Afbeeldingen/dice_3.png";
                break;
            case 4 : geroldeWorp = "../Afbeeldingen/dice_4.png";
                break;
            case 5 : geroldeWorp = "../Afbeeldingen/dice_5.png";
                break;
            case 6 : geroldeWorp = "../Afbeeldingen/dice_6.png";
                break;
        }
        return geroldeWorp;
    }

}
