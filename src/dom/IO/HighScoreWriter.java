package dom.IO;

import dom.Exceptions.MensErgerJeNietExceptions;
import dom.spelers.Score;
import dom.spelers.Speler;

import java.util.Map;
import java.util.Set;

/**
 * Created by school on 4-2-14.
 */
public interface HighScoreWriter {

    public void write(Map<Score, Set<Speler>> scores) throws MensErgerJeNietExceptions;
}
