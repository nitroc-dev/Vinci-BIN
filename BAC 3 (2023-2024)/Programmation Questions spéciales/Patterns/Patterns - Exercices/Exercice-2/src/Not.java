public class Not implements Strategy {

    private Strategy strategy;

    public Not(Strategy strategy) {
        this.strategy = strategy;
    }

    public boolean imprimerSi(String c) {
        return !strategy.imprimerSi(c);
    }
}
