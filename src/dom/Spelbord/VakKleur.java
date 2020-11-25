package dom.Spelbord;

/**
 * Geeft een vak op het bord een kleur naardehand van waar een pion staat
 */
public enum VakKleur {
    ROOD('R'), GROEN('O'), GEEL('G'), BLAUW('B'), WIT('W');

    private char symbol;

    VakKleur(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}