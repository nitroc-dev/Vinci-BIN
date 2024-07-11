import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ou implements Strategy {

    private List<Strategy> strategies;

    public Ou(Strategy... strategies) {
        this.strategies = new ArrayList<>();
        Collections.addAll(this.strategies, strategies);
    }

    @Override
    public boolean imprimerSi(String c) {
        for (Strategy strategy : strategies) {
            if (strategy.imprimerSi(c)) {
                return true;
            }
        }
        return false;
    }
}
