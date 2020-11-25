package dom.IO;

import dom.Exceptions.MensErgerJeNietExceptions;
import dom.spelers.Score;
import dom.spelers.Speler;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by school on 4-2-14.
 */
public class HighScores {
    private HighScoreIO highScoreIO;
    private Map<Score, Set<Speler>>scoreBoard;

    public HighScores(){
        this.highScoreIO = new FileHighScoreIO();
        try
        {
            this.scoreBoard = highScoreIO.read(new TreeMap<Score,Set<Speler>>());

        } catch (MensErgerJeNietExceptions e)
        {
            this.scoreBoard = new TreeMap<Score,Set<Speler>>();
        }
    }

    public void addScore(Speler speler){
        Score score = speler.getScore();
        // deze score werd al behaald. en staat in Spelers
        if (scoreBoard.containsKey(score))
        {
            Set<Speler> spelersMetScore = scoreBoard.get(score);
            spelersMetScore.add(speler);
        } else
        {
            Set<Speler> spelersMetScore = new HashSet<>();
            spelersMetScore.add(speler);
            scoreBoard.put(score, spelersMetScore);
        }
    }

    public void bewaarScore() throws MensErgerJeNietExceptions{
        highScoreIO.write(this.scoreBoard);
    }

    public void laadScores()throws MensErgerJeNietExceptions{
        scoreBoard = highScoreIO.read(scoreBoard);
    }

    public Map<Score, Set<Speler>> getScoreBord() {
        return scoreBoard;
    }

    public Map<Score, Set<Speler>> getTop10(){
        Map<Score, Set<Speler>> top10 = new TreeMap<Score, Set<Speler>>();
        int i = 0;
        for (Score score: scoreBoard.keySet()){
            top10.put(score, scoreBoard.get(score));
            i++;
            if (i>9){
                break;
            }
        }

        return top10;
    }

    @Override
    public String toString() {
        Map<Score, Set<Speler>> top10 = this.getTop10();
        StringBuilder sb = new StringBuilder();
        int i = 1;
        sb.append("   ").append(String.format("%-5s\t%-15s","Score","Spelers")).append("\n");
        for (Map.Entry<Score, Set<Speler>> entry : top10.entrySet())
        {
            Score score = entry.getKey();
            Set<Speler> spelers = entry.getValue();
            sb.append(String.format("%d)  ",i)).append(String.format("%-5d\t", score.getScore()));
            for (Speler speler : spelers)
                sb.append(String.format("%-15s", speler.getNaam()));
            sb.append("\n");
            i++;
        }
        return sb.toString();
    }
}
