package dom.spelers;

/**
 * Houdt de score van elke speler bij
 */
public class Score {

    private int score;
    public Score(int score){
        this.score = score;
    }

    //Indien een pion een anderen pion aftikt (score +250)
    public void botsing(){

    }

    //Bij een gewone zet (score -5)
    public void zet(){

    }

    //Eigen pion geforceerd moeten aftikken (score -50)
    public void fouteBotsing(){

    }

    public int getScore() {
        return this.score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Score)) return false;

        Score score1 = (Score) o;

        if (score != score1.score) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return score;
    }

    @Override
    public String toString() {
        return String.valueOf(this.score);
    }
}
