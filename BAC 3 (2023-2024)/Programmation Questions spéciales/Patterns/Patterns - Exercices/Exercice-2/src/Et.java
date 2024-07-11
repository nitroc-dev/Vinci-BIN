import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Et implements Strategy {

    private final List<Strategy> strategies;

    public Et(Strategy... strategies) {
        this.strategies = new ArrayList<>();
        Collections.addAll(this.strategies, strategies);
    }

    @Override
    public boolean imprimerSi(String c) {
        for (Strategy strategy : strategies) {
            if (!strategy.imprimerSi(c)) {
                return false;
            }
        }
        return true;
    }
}
