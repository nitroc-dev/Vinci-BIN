public class Or implements Strategy {

    private Strategy strategy1;
    private Strategy strategy2;

    public Or(Strategy strategy1, Strategy strategy2) {
        this.strategy1 = strategy1;
        this.strategy2 = strategy2;
    }

    @Override
    public boolean imprimerSi(String c) {
        return strategy1.imprimerSi(c) || strategy2.imprimerSi(c);
    }
}
