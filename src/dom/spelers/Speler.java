package dom.spelers;

import java.util.LinkedList;

/**
 * Klasse waar mens en computer van overerven, bevat code die gebruikt wordt door zowel de computer als de
 * menselijke speler
 */
public class Speler {

    private String naam;
    private String soort;
    private Score score;
    private String color;

    public enum Soort{
        MENS, COMPUTER
    }

    public Speler(){

    }

    public Speler(Score score, String naam){
        this.score = score;
        this.naam = naam;
    }

    public Speler(String naam, String soort, Score score, int teller) {
        this.naam = naam;
        this.soort = soort;
        this.score = score;
        switch (teller){
            case 1 : this.color = "Geel";
                break;

            case 2 : this.color = "Groen";
                break;

            case 3 : this.color = "Blauw";
                break;

            case 4 : this.color = "Rood";
                break;
        }
    }

    public Score getScore() {
        return this.score;
    }

    public String getNaam() {
        return this.naam;
    }

    public String getColor(){
        return this.color;
    }

    public String getSoort() {
        return soort;
    }
}
