import java.util.StringTokenizer;

public class CommenceParStrategy implements Strategy {

    private String parameter;

    public CommenceParStrategy(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public boolean imprimerSi(String c) {
        return c.startsWith(parameter.toLowerCase()) || c.startsWith(parameter.toUpperCase());
    }
}
