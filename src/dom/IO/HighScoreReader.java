package dom.IO;

import dom.Exceptions.MensErgerJeNietExceptions;
import dom.spelers.Score;
import dom.spelers.Speler;

import java.util.Map;
import java.util.Set;

/**
 * Created by school on 4-2-14.
 */
public interface HighScoreReader {

    public Map<Score, Set<Speler>> read(Map<Score, Set<Speler>> scoreMap) throws MensErgerJeNietExceptions;
}
