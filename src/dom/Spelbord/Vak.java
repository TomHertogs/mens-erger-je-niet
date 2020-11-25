package dom.Spelbord;

/**
 * Maakt een vak met een bepaalde kleur aan
 */
public class Vak {
    private VakKleur kleur;

    public Vak(VakKleur kleur) {
        this.kleur = kleur;
    }

    public VakKleur getKleur() {
        return kleur;
    }

    @Override
    public String toString() {
        return this.kleur.toString();
    }
}