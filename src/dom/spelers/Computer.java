package dom.spelers;


/**
 * De code voor de AI, indien gevraagd bij begin spel
 */
public class Computer extends Speler {

    public Computer(String naam, int teller) {
        super(naam, "Computer", new Score(0), teller);
    }

    public Computer(){
    }
}
