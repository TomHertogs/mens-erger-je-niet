package dom.Spelbord;

import Controller.Spel;
import View.BordUI;
import dom.IO.HighScores;
import dom.spelers.Speler;

import java.util.LinkedList;

/**
 * Bevat de code die een zet beschrijft --> wat mag/niet en maakt het spelbord
 */
public class Bord {
    private Vak vak;
    private HighScores highScores = new HighScores();
    //    private boolean isErWinnaar = false;
    private Dobbel dobbel;
    private Speler speler;
    private LinkedList<Speler> spelers = new LinkedList<>();
    private boolean isErWinnaar = false;
    private boolean worpIsZes = false;
    private boolean eindePositie = false;
    private final int VIER = 4;
    private int beginTellerRood = 3;
    private int beginTellerBlauw = 3;
    private int beginTellerGroen = 3;
    private int beginTellerGeel = 3;

    private int[] positieRood = new int[VIER];
    private int[] positieBlauw = new int[VIER];
    private int[] positieGroen = new int[VIER];
    private int[] positieGeel = new int[VIER];

    private boolean[] eindPositieBezetRood = new boolean[VIER];
    private boolean[] eindPositieBezetBlauw = new boolean[VIER];
    private boolean[] eindPositieBezetGroen = new boolean[VIER];
    private boolean[] eindPositieBezetGeel = new boolean[VIER];

    //constructor initialiseert arrays
    public Bord(Dobbel dobbel, Speler speler) {
        this.dobbel = dobbel;
        this.speler = speler;

        for (int i = 0; i < VIER; i++) {
            positieRood[i] = 0;
            positieBlauw[i] = 0;
            positieGroen[i] = 0;
            positieGeel[i] = 0;

            eindPositieBezetRood[i] = false;
            eindPositieBezetBlauw[i] = false;
            eindPositieBezetGroen[i] = false;
            eindPositieBezetGeel[i] = false;
        }
    }

    //Er wordt gecontroleerd of er een 6 werd gegooid
    private boolean worpIsZes() {
        if (dobbel.getWorp() == 6) {
            return worpIsZes = true;
        } else {
            return worpIsZes = false;
        }
    }


    //Deze methode checkt of er een 6 werd geworpen, indien dit het geval is wordt een nieuwe pion op het bord geplaatst
    public void checkWorp() {

        if (spelers.getFirst().getColor().equals("Rood")) {
            if (worpIsZes() && beginTellerRood != 0) {
                rodeBeginPositieX[beginTellerRood] = spelPatroonRoodX[0];
                rodeBeginPositieY[beginTellerRood] = spelPatroonRoodY[0];
                beginTellerRood--;
                worpIsZes = true;
            }
        } else if (spelers.getFirst().getColor().equals("Blauw")) {
            if (worpIsZes() && beginTellerBlauw != 0) {
                blauweBeginPositieX[beginTellerBlauw] = spelPatroonBlauwX[0];
                blauweBeginPositieY[beginTellerBlauw] = spelPatroonBlauwY[0];
                beginTellerBlauw--;
                worpIsZes = true;
            }
        } else if (spelers.getFirst().getColor().equals("Groen")) {
            if (worpIsZes() && beginTellerGroen != 0) {
                groeneBeginPositieX[beginTellerGroen] = spelPatroonGroenX[0];
                groeneBeginPositieY[beginTellerGroen] = spelPatroonGroenY[0];
                beginTellerGroen--;
                worpIsZes = true;
            }
        } else if (spelers.getFirst().getColor().equals("Geel")) {
            if (worpIsZes() && beginTellerGeel != 0) {
                geleBeginPositieX[beginTellerGeel] = spelPatroonGeelX[0];
                geleBeginPositieY[beginTellerGeel] = spelPatroonGeelY[0];
                beginTellerGroen--;
                worpIsZes = true;
            }

        }
    }


    public void zetPion() {
        for (int j = 0; j < VIER; j++) {
            if (spelers.getFirst().getColor().equals("Rood")) {
                spelPatroonRoodX[positieRood[j]] = spelPatroonRoodX[positieRood[j] + dobbel.getWorp()];
                spelPatroonRoodY[positieRood[j]] = spelPatroonRoodY[positieRood[j] + dobbel.getWorp()];
            } else if (spelers.getFirst().getColor().equals("Blauw")) {
                spelPatroonBlauwX[positieBlauw[j]] = spelPatroonBlauwX[positieBlauw[j] + dobbel.getWorp()];
                spelPatroonBlauwY[positieBlauw[j]] = spelPatroonBlauwY[positieBlauw[j] + dobbel.getWorp()];
            } else if (spelers.getFirst().getColor().equals("Groen")) {
                spelPatroonGroenX[positieGroen[j]] = spelPatroonGroenX[positieGroen[j] + dobbel.getWorp()];
                spelPatroonGroenY[positieGroen[j]] = spelPatroonGroenY[positieGroen[j] + dobbel.getWorp()];
            } else if (spelers.getFirst().getColor().equals("Geel")) {
                spelPatroonGeelX[positieGeel[j]] = spelPatroonGeelX[positieGeel[j] + dobbel.getWorp()];
                spelPatroonGeelY[positieGeel[j]] = spelPatroonGeelY[positieGeel[j] + dobbel.getWorp()];
            }
        }
    }


    //checkt of een pion op de eindreeks kan komen adhv de worp en checkt of de eindreeks bezet is
    public void checkEindPositiePion() {
        if (spelers.getFirst().getColor().equals("Rood")) {
            for (int i = 34; i < 40; i++) {
                for (int j = 0; j < VIER; j++) {

                    if ((spelPatroonRoodX[positieRood[0]] == spelPatroonRoodX[i] && spelPatroonRoodY[positieRood[0]] == spelPatroonRoodY[i]) && dobbel.getWorp() >= (40 - i)) {
                        if (spelPatroonRoodX[(positieRood[0] + dobbel.getWorp() - 39)] == spelPatroonRoodX[1] && !eindPositieBezetRood[0]) {
                            spelPatroonRoodX[positieRood[0]] = rodeEindPositieX[0];
                            spelPatroonRoodY[positieRood[0]] = rodeEindPositieY[0];
                            eindPositieBezetRood[0] = true;
                            eindePositie = true;
                        } else if (spelPatroonRoodX[(positieRood[0] + dobbel.getWorp() - 39)] == spelPatroonRoodX[2] && !eindPositieBezetRood[1]) {
                            spelPatroonRoodX[positieRood[0]] = rodeEindPositieX[1];
                            spelPatroonRoodY[positieRood[0]] = rodeEindPositieY[1];
                            eindPositieBezetRood[1] = true;
                            eindePositie = true;
                        } else if (spelPatroonRoodX[(positieRood[0] + dobbel.getWorp() - 39)] == spelPatroonRoodX[3] && !eindPositieBezetRood[2]) {
                            spelPatroonRoodX[positieRood[0]] = rodeEindPositieX[2];
                            spelPatroonRoodY[positieRood[0]] = rodeEindPositieY[2];
                            eindPositieBezetRood[2] = true;
                            eindePositie = true;
                        } else if (spelPatroonRoodX[(positieRood[0] + dobbel.getWorp() - 39)] == spelPatroonRoodX[4] && !eindPositieBezetRood[3]) {
                            spelPatroonRoodX[positieRood[0]] = rodeEindPositieX[3];
                            spelPatroonRoodY[positieRood[0]] = rodeEindPositieY[3];
                            eindPositieBezetRood[3] = true;
                            eindePositie = true;
                        }
                    }
                }
            }
        }
        if (spelers.getFirst().getColor().equals("Blauw")) {
            for (int i = 34; i < 40; i++) {
                for (int j = 0; j < VIER; j++) {
                    if ((spelPatroonBlauwX[positieBlauw[0]] == spelPatroonBlauwX[i] && spelPatroonBlauwY[positieBlauw[0]] == spelPatroonBlauwY[i]) && dobbel.getWorp() >= (40 - i)) {
                        if (spelPatroonBlauwX[(positieBlauw[0] + dobbel.getWorp() - 39)] == spelPatroonBlauwX[1] && !eindPositieBezetBlauw[0]) {
                            spelPatroonBlauwX[positieBlauw[0]] = blauwEindPositieX[0];
                            spelPatroonBlauwY[positieBlauw[0]] = blauwEindPositieY[0];
                            eindPositieBezetBlauw[0] = true;
                            eindePositie = true;
                        } else if (spelPatroonBlauwX[(positieBlauw[0] + dobbel.getWorp() - 39)] == spelPatroonBlauwX[2] && !eindPositieBezetBlauw[1]) {
                            spelPatroonBlauwX[positieBlauw[0]] = blauwEindPositieX[1];
                            spelPatroonBlauwY[positieBlauw[0]] = blauwEindPositieY[1];
                            eindPositieBezetBlauw[1] = true;
                            eindePositie = true;
                        } else if (spelPatroonBlauwX[(positieBlauw[0] + dobbel.getWorp() - 39)] == spelPatroonBlauwX[3] && !eindPositieBezetBlauw[2]) {
                            spelPatroonBlauwX[positieBlauw[0]] = blauwEindPositieX[2];
                            spelPatroonBlauwY[positieBlauw[0]] = blauwEindPositieY[2];
                            eindPositieBezetBlauw[2] = true;
                            eindePositie = true;
                        } else if (spelPatroonBlauwX[(positieBlauw[0] + dobbel.getWorp() - 39)] == spelPatroonBlauwX[4] && !eindPositieBezetBlauw[3]) {
                            spelPatroonBlauwX[positieBlauw[0]] = blauwEindPositieX[3];
                            spelPatroonBlauwY[positieBlauw[0]] = blauwEindPositieY[3];
                            eindPositieBezetBlauw[3] = true;
                            eindePositie = true;
                        }
                    }
                }
            }
        }

        if (spelers.getFirst().getColor().equals("Groen")) {
            for (int i = 34; i < 40; i++) {
                for (int j = 0; j < VIER; j++) {
                    if ((spelPatroonGroenX[positieGroen[0]] == spelPatroonGroenX[i] && spelPatroonGroenY[positieGroen[0]] == spelPatroonGroenY[i]) && dobbel.getWorp() >= (40 - i)) {
                        if (spelPatroonGroenX[(positieGroen[0] + dobbel.getWorp() - 39)] == spelPatroonGroenX[1] && !eindPositieBezetGroen[0]) {
                            spelPatroonGroenX[positieGroen[0]] = groeneEindPositieX[0];
                            spelPatroonGroenY[positieGroen[0]] = groeneEindPositieY[0];
                            eindPositieBezetGroen[0] = true;
                            eindePositie = true;
                        } else if (spelPatroonGroenX[(positieGroen[0] + dobbel.getWorp() - 39)] == spelPatroonGroenX[2] && !eindPositieBezetGroen[1]) {
                            spelPatroonGroenX[positieGroen[0]] = groeneEindPositieX[1];
                            spelPatroonGroenY[positieGroen[0]] = groeneEindPositieY[1];
                            eindPositieBezetGroen[1] = true;
                            eindePositie = true;
                        } else if (spelPatroonGroenX[(positieGroen[0] + dobbel.getWorp() - 39)] == spelPatroonGroenX[3] && !eindPositieBezetGroen[2]) {
                            spelPatroonGroenX[positieGroen[0]] = groeneEindPositieX[2];
                            spelPatroonGroenY[positieGroen[0]] = groeneEindPositieY[2];
                            eindPositieBezetGroen[2] = true;
                            eindePositie = true;
                        } else if (spelPatroonGroenX[(positieGroen[0] + dobbel.getWorp() - 39)] == spelPatroonGroenX[4] && !eindPositieBezetGroen[3]) {
                            spelPatroonGroenX[positieGroen[0]] = groeneEindPositieX[3];
                            spelPatroonGroenY[positieGroen[0]] = groeneEindPositieY[3];
                            eindPositieBezetGroen[3] = true;
                            eindePositie = true;
                        }
                    }
                }
            }
        }

        if (spelers.getFirst().getColor().equals("Geel")) {
            for (int i = 34; i < 40; i++) {
                for (int j = 0; j < VIER; j++) {
                    if ((spelPatroonGeelX[positieGeel[0]] == spelPatroonGeelX[i] && spelPatroonGeelY[positieGeel[0]] == spelPatroonGeelY[i]) && dobbel.getWorp() >= (40 - i)) {
                        if (spelPatroonGeelX[(positieGeel[0] + dobbel.getWorp() - 39)] == spelPatroonGeelX[1] && !eindPositieBezetGeel[0]) {
                            spelPatroonGeelX[positieGeel[0]] = geelEindPositieX[0];
                            spelPatroonGeelY[positieGeel[0]] = geelEindPositieY[0];
                            eindPositieBezetGeel[0] = true;
                            eindePositie = true;
                        } else if (spelPatroonGeelX[(positieGeel[0] + dobbel.getWorp() - 39)] == spelPatroonGeelX[2] && !eindPositieBezetGeel[1]) {
                            spelPatroonGeelX[positieGeel[0]] = geelEindPositieX[1];
                            spelPatroonGeelY[positieGeel[0]] = geelEindPositieY[1];
                            eindPositieBezetGeel[1] = true;
                            eindePositie = true;
                        } else if (spelPatroonGeelX[(positieGeel[0] + dobbel.getWorp() - 39)] == spelPatroonGeelX[3] && !eindPositieBezetGeel[2]) {
                            spelPatroonGeelX[positieGeel[0]] = geelEindPositieX[2];
                            spelPatroonGeelY[positieGeel[0]] = geelEindPositieY[2];
                            eindPositieBezetGeel[2] = true;
                            eindePositie = true;
                        } else if (spelPatroonGeelX[(positieGeel[0] + dobbel.getWorp() - 39)] == spelPatroonGeelX[4] && !eindPositieBezetGeel[3]) {
                            spelPatroonGeelX[positieGeel[0]] = geelEindPositieX[3];
                            spelPatroonGeelY[positieGeel[0]] = geelEindPositieY[3];
                            eindPositieBezetGeel[3] = true;
                            eindePositie = true;
                        }
                    }
                }
            }
        }
    }


    //De witte cirkels-------------------------------
    private int[] witteCirkelsX = {
            50, 100, 150, 200, 250, 250, 250, 250, 250, 300, 350, 350, 350
            , 350, 350, 400, 450, 500, 550, 550, 550, 500, 450, 400, 350, 350
            , 350, 350, 350, 300, 250, 250, 250, 250, 250, 200, 150, 100
            , 50, 50
    };

    private int[] witteCirkelsY = {
            250, 250, 250, 250, 250, 200, 150, 100, 50, 50, 50, 100, 150, 200
            , 250, 250, 250, 250, 250, 300, 350, 350, 350, 350, 350, 400
            , 450, 500, 550, 550, 550, 500, 450, 400, 350, 350, 350, 350
            , 350, 300
    };

    /**
     * nog getters aanmaken voor de spelPatroon Coöordinaten
     */
// het spelpatroon dat de rode pionnen zullen volgen:
    private int[] spelPatroonRoodX = {
            50, 100, 150, 200, 250, 250, 250, 250, 250, 300, 350, 350, 350
            , 350, 350, 400, 450, 500, 550, 550, 550, 500, 450, 400, 350, 350
            , 350, 350, 350, 300, 250, 250, 250, 250, 250, 200, 150, 100
            , 50, 50

    };
    //    de Y coördinaten van het spelpatroon dat de rode pionnen zullen volgen:
    private int[] spelPatroonRoodY = {
            250, 250, 250, 250, 250, 200, 150, 100, 50, 50, 50, 100, 150, 200
            , 250, 250, 250, 250, 250, 300, 350, 350, 350, 350, 350, 400
            , 450, 500, 550, 550, 550, 500, 450, 400, 350, 350, 350, 350
            , 350, 300

    };
    // spelPatroonBlauwX
    private int[] spelPatroonBlauwX = {
            350, 350, 350
            , 350, 350, 400, 450, 500, 550, 550, 550, 500, 450, 400, 350, 350
            , 350, 350, 350, 300, 250, 250, 250, 250, 250, 200, 150, 100
            , 50, 50, 50, 100, 150, 200, 250, 250, 250, 250, 250, 300
    };

    private int[] spelPatroonBlauwY = {
            50, 100, 150, 200
            , 250, 250, 250, 250, 250, 300, 350, 350, 350, 350, 350, 400
            , 450, 500, 550, 550, 550, 500, 450, 400, 350, 350, 350, 350
            , 350, 300, 250, 250, 250, 250, 250, 200, 150, 100, 50, 50

    };

    // spelPatroonGroenX:
    private int[] spelPatroonGroenX = {
            550, 500, 450, 400,
            350, 350, 350, 350, 350, 300, 250, 250, 250, 250, 250, 200, 150,
            100, 50, 50, 50, 100, 150, 200, 250, 250, 250, 250, 250, 300
            , 350, 350, 350, 350, 350, 400, 450, 500, 550, 550
    };

    // spelPatroonGroenY:
    private int[] spelPatroonGroenY = {
            350, 350, 350, 350, 350, 400
            , 450, 500, 550, 550, 550, 500, 450, 400, 350, 350, 350, 350
            , 350, 300, 250, 250, 250, 250, 250, 200, 150, 100, 50, 50
            , 50, 100, 150, 200, 250, 250, 250, 250, 250, 300,
    };

    //spelPatroonGeelX:
    private int[] spelPatroonGeelX = {
            250, 250, 250, 250, 250, 200, 150,
            100, 50, 50, 50, 100, 150, 200, 250, 250, 250, 250, 250, 300
            , 350, 350, 350, 350, 350, 400, 450, 500, 550, 550, 550,
            500, 450, 400, 350, 350, 350, 350, 350, 300
    };

    //spelPatroonGeelY:
    private int[] spelPatroonGeelY = {
            550, 500, 450, 400, 350, 350, 350, 350
            , 350, 300, 250, 250, 250, 250, 250, 200, 150, 100, 50, 50
            , 50, 100, 150, 200, 250, 250, 250, 250, 250, 300, 350
            , 350, 350, 350, 350, 400, 450, 500, 550, 550
    };

    //De rode startposities--------------------------
    private int[] rodeBeginPositieX = {
            50, 100, 50, 100
    };

    private int[] rodeBeginPositieY = {
            50, 50, 100, 100
    };


    //De gele startposities--------------------------
    private int[] geleBeginPositieX = {
            50, 100, 50, 100
    };

    private int[] geleBeginPositieY = {
            500, 500, 550, 550
    };


    //De groene startposities------------------------
    private int[] groeneBeginPositieX = {
            500, 550, 500, 550
    };

    private int[] groeneBeginPositieY = {
            500, 500, 550, 550
    };


    //De blauwe startposities--------------------------
    private int[] blauweBeginPositieX = {
            500, 550, 500, 550
    };

    private int[] blauweBeginPositieY = {
            50, 50, 100, 100
    };

    //De rode eindposities-----------------------------
    private int[] rodeEindPositieX = {
            100, 150, 200, 250
    };

    private int[] rodeEindPositieY = {
            300, 300, 300, 300
    };

    //De blauwe eindposities---------------------------
    private int[] blauwEindPositieX = {
            300, 300, 300, 300
    };
    private int[] blauwEindPositieY = {
            100, 150, 200, 250
    };

    //De gele eindposities-----------------------------
    private int[] geelEindPositieX = {
            300, 300, 300, 300
    };

    private int[] geelEindPositieY = {
            350, 400, 450, 500
    };

    //De groene eindposities---------------------------
    private int[] groeneEindPositieX = {
            350, 400, 450, 500
    };

    private int[] groeneEindPositieY = {
            300, 300, 300, 300
    };


    public LinkedList<Speler> getSpelers() {
        return spelers;
    }

    //Getters voor alle arrays
    public int[] getWitteCirkelsX() {
        return witteCirkelsX;
    }

    public int[] getWitteCirkelsY() {
        return witteCirkelsY;
    }

    public int[] getRodeBeginPositieX() {
        return rodeBeginPositieX;
    }

    public int[] getRodeBeginPositieY() {
        return rodeBeginPositieY;
    }

    public int[] getGeleBeginPositieX() {
        return geleBeginPositieX;
    }

    public int[] getGeleBeginPositieY() {
        return geleBeginPositieY;
    }

    public int[] getGroeneBeginPositieX() {
        return groeneBeginPositieX;
    }

    public int[] getGroeneBeginPositieY() {
        return groeneBeginPositieY;
    }

    public int[] getBlauweBeginPositieX() {
        return blauweBeginPositieX;
    }

    public int[] getBlauweBeginPositieY() {
        return blauweBeginPositieY;
    }

    public int[] getRodeEindPositieX() {
        return rodeEindPositieX;
    }

    public int[] getRodeEindPositieY() {
        return rodeEindPositieY;
    }

    public int[] getBlauwEindPositieX() {
        return blauwEindPositieX;
    }

    public int[] getBlauwEindPositieY() {
        return blauwEindPositieY;
    }

    public int[] getGeelEindPositieX() {
        return geelEindPositieX;
    }

    public int[] getGeelEindPositieY() {
        return geelEindPositieY;
    }

    public int[] getGroeneEindPositieX() {
        return groeneEindPositieX;
    }

    public int[] getGroeneEindPositieY() {
        return groeneEindPositieY;
    }

    public int[] getSpelPatroonRoodX() {
        return spelPatroonRoodX;
    }

    public int[] getSpelPatroonRoodY() {
        return spelPatroonRoodY;
    }

    public int[] getSpelPatroonBlauwX() {
        return spelPatroonBlauwX;
    }

    public int[] getSpelPatroonBlauwY() {
        return spelPatroonBlauwY;
    }

    public int[] getSpelPatroonGroenX() {
        return spelPatroonGroenX;
    }

    public int[] getSpelPatroonGroenY() {
        return spelPatroonGroenY;
    }

    public int[] getSpelPatroonGeelX() {
        return spelPatroonGeelX;
    }

    public int[] getSpelPatroonGeelY() {
        return spelPatroonGeelY;
    }

    public boolean isWorpZes() {
        return worpIsZes;
    }

    public boolean eindePositie() {
        return eindePositie;
    }
}

