import java.util.StringTokenizer;

public class LongueurStrategy implements Strategy {

    private int longueur;

    public LongueurStrategy(int longueur) {
        this.longueur = longueur;
    }

    @Override
    public boolean imprimerSi(String c) {
        return c.length() == longueur;
    }
}
