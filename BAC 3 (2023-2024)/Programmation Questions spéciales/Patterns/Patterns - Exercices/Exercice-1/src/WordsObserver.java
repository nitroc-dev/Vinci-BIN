public class WordsObserver implements Observer {

    private int nbrMots = 0;

    @Override
    public void processLine(String line) {
        String[] words = line.split(" ");
        nbrMots += words.length;
    }

    @Override
    public void printResult() {
        System.out.println("Il y avait " + nbrMots + " mots.");
    }
}
