package dom.Spelbord;

import java.util.Random;

/**
 * Created by PATRICK on 19/02/14.
 */
public class Dobbel {

    private int worp;

    public void werpDobbelsteen() {

        Random random = new Random();
        worp = random.nextInt(6) + 1;
    }


    public int getWorp() {
        return worp;
    }
}
