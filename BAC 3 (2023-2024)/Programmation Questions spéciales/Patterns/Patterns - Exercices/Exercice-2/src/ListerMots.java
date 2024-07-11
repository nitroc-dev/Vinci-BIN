import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ListerMots {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Usage : java ListerMots1 fichier");
            System.exit(1);
        }
        Counter counter = new Counter(new And(new PalindromeStrategy(), new CommenceParStrategy("t")));
        imprimerSi(counter, args[0]);
        System.out.println("Nombre de mots : " + counter.getCount());
    }

    public static void imprimerSi(Strategy strategy, String file) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(file));
        String buffer = null;
        while ((buffer = input.readLine()) != null) {
            StringTokenizer mots = new StringTokenizer(buffer, " \t.;(){}\"'*=:!/\\");
            while (mots.hasMoreTokens()) {
                String mot = mots.nextToken();
                if (strategy.imprimerSi(mot))
                    System.out.println(mot);
            }
        }
    }
}
