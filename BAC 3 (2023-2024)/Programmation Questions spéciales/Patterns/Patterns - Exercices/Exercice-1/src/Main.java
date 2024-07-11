import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Observer wordsObserver = new WordsObserver();
        Observer linesObserver = new LinesObserver();
        Observer palindromeObserver = new PalindromeObserver();
        Observer belgiumObserver = new WordObserver("Belgique");

        AnalyseurDeTexte analyser = new AnalyseurDeTexte();
        analyser.registerObserver(wordsObserver);
        analyser.registerObserver(linesObserver);
        analyser.registerObserver(palindromeObserver);
        analyser.registerObserver(belgiumObserver);

        analyser.lireFichier(args[0]);
    }
}
