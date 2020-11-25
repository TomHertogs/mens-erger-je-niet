package dom.Spelbord;

/**
 * Created by school on 11-2-14.
 */
public class Ronde {
    private int ronde;

    public Ronde() {
    }

    public void berekenRonde(){
        this.ronde += 1;
    }

    public void setRondeNul() {
        this.ronde = 0;
    }


    public int getRonde() {
        return ronde;
    }
}
