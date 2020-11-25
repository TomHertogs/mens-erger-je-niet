package Controller;

import View.DobbelUI;
import View.SpelerUI;
import dom.Exceptions.MensErgerJeNietExceptions;
import dom.IO.FileHighScoreIO;
import dom.IO.HighScores;
import dom.Spelbord.Bord;
import dom.Spelbord.Dobbel;
import dom.Spelbord.Ronde;
import dom.spelers.Computer;
import dom.spelers.Mens;
import dom.spelers.Speler;

import javax.swing.*;
import java.util.LinkedList;

/**
 * Created by PATRICK on 18/02/14.
 */
public class Spel {
    private Dobbel dobbel = new Dobbel();
    private Speler speler = new Speler();
    private Bord bord = new Bord(dobbel, speler);
    private Computer computer = new Computer();
    private HighScores highScores = new HighScores();
    private FileHighScoreIO fileHighScoreIO = new FileHighScoreIO();
    private Ronde ronde = new Ronde();
    private SpelerUI spelerUI;
    private int teller = 0;

    public Spel() {

    }

    //Start een nieuw spel
    public void startBeurt() {
        switch (bord.getSpelers().getFirst().getSoort()) {
            case "Mens": //do nothing, mens voert zijn eigen zetten uit
                break;
            case "Computer":
                werpDobbelsteen();
                checkWorp();
                if(!isWorpZes()){
                    checkEindePositie();
                }
                else if(!isWorpZes() || !eindePositie()){
                    zetPion();
                }
                duidSpelerAan();
                startBeurt();
        }
    }

    //Voegt een speler toe
    public void maakSpeler(String naam, Speler.Soort soort) {

        //Speler speler = null;
        teller++;
        switch (soort) {
            case MENS:
                speler = new Mens(naam, teller);
                break;

            case COMPUTER:
                speler = new Computer(naam, teller);
                break;
        }
        bord.getSpelers().push(speler);
    }

    //Vraagt de spelerlijst op
    public LinkedList<Speler> getSpelers() {
        return bord.getSpelers();
    }

    //Stuurt de hulptekst voor op het speler keuzescherm door
    public String spelerHelpTekst() {
        return "<HTML> Voeg hier spelers toe aan je spel. <br><br> Door op de computer knop te klikken<br> wordt een computer toegevoegd.<br><br>" +
                "Door op de mens knop te klikken<br> wordt een menselijke speler toegevoegd.</HTML>";
    }

    public String getSoort() {
        return speler.getSoort();
    }

    public String getColor() {
        return speler.getColor();
    }

    //Zegt wie er aan de beurt is
    public void duidSpelerAan() {

        speler = bord.getSpelers().remove();

        bord.getSpelers().addLast(speler);
    }

    //Bevat de hulptekst die getoond wordt indien de hulpknop wordt ingedrukt
    public String toonHulp() {
        return "<HTML>Welkom bij Mens Erger Je Niet! <br><br> Bij dit spel heeft iedere speler vier pionnen die een ronde over het bord" +
                " moeten maken om veilig op een van de eindcirkels te geraken. Eenmaal daar kan een pion niet meer worden verzet.<br> " +
                "Als dat met alle pionnen is gelukt, heeft men het spel gewonnen.<br>Elke speler heeft pionnen van een eigen kleur, " +
                "een eigen beginpunt, en eigen eindcirkels waar andere pionnen niet mogen komen.<br> Het bord kent twee varianten:" +
                " een voor maximaal 4 spelers, en een voor maximaal zes spelers. <br> De spelers gooien om beurten met een dobbelsteen" +
                " en moeten een pion rechtsom het aantal cirkels verplaatsen dat de worp aangeeft.<br>  Wanneer een pion op een cirkel" +
                " komt waar al een andere pion staat, dan wordt deze afgegooid (geslagen).<br>  Een afgegooide pion moet weer opnieuw in" +
                " het spel worden gebracht en van voren af aan zijn ronde maken.<br></HTML>";
    }

    //Toont de worp die een speler heeft gegooid
    public int werpDobbelsteen() {
        dobbel.werpDobbelsteen();
        return dobbel.getWorp();
    }

    public int getWorp() {
        return dobbel.getWorp();
    }

    //Toont aan welke ronde we zitten
    public int toonRonde() {
        return ronde.getRonde();
    }

    public int berekenRonde() {
        ronde.berekenRonde();
        return ronde.getRonde();
    }

    //bewaart eventuele nieuwe highscores
    public void bewaarHighScores() throws MensErgerJeNietExceptions {
        highScores.bewaarScore();
    }

    //haalt de highscores op bij het einde van het spel
    public void laadHighScores() throws MensErgerJeNietExceptions {
        highScores.bewaarScore();
    }

    public String toonHighScores() {
        return highScores.toString();
    }

    //Hieronder staan de methodes om een pion over het bord te verplaatsen
    public void checkEindePositie(){
        bord.checkEindPositiePion();
    }

    public void checkWorp(){
        bord.checkWorp();
    }

    public void zetPion(){
        bord.zetPion();
    }

    public boolean isWorpZes(){
        return bord.isWorpZes();
    }

    public boolean eindePositie(){
        return bord.eindePositie();
    }

    //getters die de arrays terugsturen
    public int[] getBlauweBeginPositieX() {
        return bord.getBlauweBeginPositieX();
    }

    public int[] getBlauweBeginPositieY() {
        return bord.getBlauweBeginPositieY();
    }


    public int[] getRodeBeginPositieX() {
        return bord.getRodeBeginPositieX();
    }

    public int[] getRodeBeginPositieY() {
        return bord.getRodeBeginPositieY();
    }


    public int[] getGroeneBeginPositieX() {
        return bord.getGroeneBeginPositieX();
    }

    public int[] getGroeneBeginPositieY() {
        return bord.getGroeneBeginPositieY();
    }


    public int[] getGeleBeginPositieX() {
        return bord.getGeleBeginPositieX();
    }

    public int[] getGeleBeginPositieY() {
        return bord.getGeleBeginPositieY();
    }


    public int[] getBlauweEindPositieX() {
        return bord.getBlauwEindPositieX();
    }

    public int[] getBlauweEindPositieY() {
        return bord.getBlauwEindPositieY();
    }


    public int[] getRodeEindPositieX() {
        return bord.getRodeEindPositieX();
    }

    public int[] getRodeEindPositieY() {
        return bord.getRodeEindPositieY();
    }


    public int[] getGroeneEindPositieX() {
        return bord.getGroeneEindPositieX();
    }

    public int[] getGroeneEindPositieY() {
        return bord.getGroeneEindPositieY();
    }


    public int[] getGeleEindPositieX() {
        return bord.getGeelEindPositieX();
    }

    public int[] getGeleEindPositieY() {
        return bord.getGeelEindPositieY();
    }


    public int[] getWitteCirkelsX() {
        return bord.getWitteCirkelsX();
    }

    public int[] getWitteCirkelsY() {
        return bord.getWitteCirkelsY();
    }

    public int[] getSpelPatroonRoodX() {
        return bord.getSpelPatroonRoodX();
    }

    public int[] getSpelPatroonRoodY() {
        return bord.getSpelPatroonRoodY();
    }

    public int[] getSpelPatroonBlauwX() {
        return bord.getSpelPatroonBlauwX();
    }

    public int[] getSpelPatroonBlauwY() {
        return bord.getSpelPatroonBlauwY();
    }

    public int[] getSpelPatroonGroenX() {
        return bord.getSpelPatroonGroenX();
    }

    public int[] getSpelPatroonGroenY() {
        return bord.getSpelPatroonGroenY();
    }

    public int[] getSpelPatroonGeelX() {
        return bord.getSpelPatroonGeelX();
    }

    public int[] getSpelPatroonGeelY() {
        return bord.getSpelPatroonGeelY();
    }
}
