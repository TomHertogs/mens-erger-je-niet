package dom.spelers;


/**
 * Laat toe een menselijke speler toe te voegen aan het spel
 */
public class Mens extends Speler {

    public Mens(String naam, int teller) {
            super(naam, "Mens", new Score(0), teller);
    }


}
