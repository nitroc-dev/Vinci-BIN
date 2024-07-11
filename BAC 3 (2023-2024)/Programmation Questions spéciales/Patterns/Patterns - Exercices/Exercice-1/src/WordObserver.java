public class WordObserver implements Observer {

        private final String word;
        private int nbrWord = 0;

        public WordObserver(String word) {
            this.word = word;
        }

        @Override
        public void processLine(String line) {
            if (line.contains(word)) {
                nbrWord++;
            }
        }

        @Override
        public void printResult() {
            System.out.println("Il y avait " + nbrWord + " de " + word + ".");
        }
}
