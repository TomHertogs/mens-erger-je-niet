package dom.Exceptions;

/**
 * Created by school on 4-2-14.
 */
public class MensErgerJeNietExceptions extends Exception {

    public MensErgerJeNietExceptions(){

    }

    public MensErgerJeNietExceptions(String bericht){
        super(bericht);
    }

    public MensErgerJeNietExceptions(Throwable oorzaak){
        super(oorzaak);
    }

    public MensErgerJeNietExceptions(String bericht, Throwable oorzaak){
        super(bericht, oorzaak);
    }

    public MensErgerJeNietExceptions(String bericht, Throwable oorzaak, boolean onderdruk, boolean schrijfStackTrace){
        super(bericht, oorzaak, onderdruk, schrijfStackTrace);
    }


}
