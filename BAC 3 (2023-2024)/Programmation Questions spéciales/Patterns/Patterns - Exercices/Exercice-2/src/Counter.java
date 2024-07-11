
/**
 * Visitor qui compte le nombre de fois qu'un caractère est imprimé
 */
public class Counter implements Strategy {

    private int count;
    private Strategy strategy;

    public Counter(Strategy strategy) {
        this.strategy = strategy;
        this.count = 0;
    }

    public boolean imprimerSi(String c) {
        if (strategy.imprimerSi(c)) {
            count++;
            return true;
        }
        return false;
    }

    public int getCount() {
        return count;
    }
}
