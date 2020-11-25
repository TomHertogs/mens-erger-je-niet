package dom.IO;

import dom.Exceptions.MensErgerJeNietExceptions;
import dom.spelers.Score;
import dom.spelers.Speler;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;

/**
 * Created by school on 4-2-14.
 */
public class FileHighScoreIO implements HighScoreIO{
    private final Path scoreFile;

    public FileHighScoreIO() {
        FileSystem fs = FileSystems.getDefault();
        scoreFile = fs.getPath("highscores.txt");
    }

    public void write(Map<Score, Set<Speler>> scores) throws MensErgerJeNietExceptions {
        Formatter formatter = null;
        try {
            formatter= new Formatter(new BufferedWriter(new FileWriter(scoreFile.toFile())));
            for(Map.Entry<Score, Set<Speler>> entry: scores.entrySet()){
                formatter.format("%s", entry.getKey().getScore());
                for (Speler speler: entry.getValue()){
                    formatter.format(", %s", speler.getNaam());
                }
                formatter.format("\n");
            }

        } catch (FileNotFoundException e){
            throw new MensErgerJeNietExceptions("Kon file niet schrijven", e);
        } catch (IOException e) {
            throw new MensErgerJeNietExceptions("Kon file niet schrijven", e);
        }
        finally {
            if (formatter !=null){
                formatter.close();
            }
        }
    }

    public Map<Score, Set<Speler>> read(Map<Score, Set<Speler>> scoresMap) throws MensErgerJeNietExceptions{
        try {
            Scanner scanner = new Scanner(scoreFile.toFile());
            while (scanner.hasNext());
            {
                Set<Speler> spelers;
                try {
                    String regel = scanner.nextLine();
                    String [] delen = regel.split(",");
                    int scoreGetal = Integer.parseInt(delen[0]);
                    Score score = new Score(scoreGetal); // scoreGetal is toch een integer dus wat is het probleem?  moet nog worden ingevoerd
                    if (scoresMap.containsKey(score)){
                        spelers = scoresMap.get(score);
                    }
                    else {
                        spelers = new HashSet<Speler>();
                    }
                    for(int i=1; i<delen.length; i++){
                        spelers.add((new Speler(score,delen[i])));
                        scoresMap.put(score,spelers);
                    }
                }catch (NumberFormatException nfe){
                    System.out.println("Kon score niet lezen");
                }
            }
        } catch (FileNotFoundException e){
            throw new MensErgerJeNietExceptions("Kon file niet schrijven", e);
        }
        return scoresMap;
    }
}
