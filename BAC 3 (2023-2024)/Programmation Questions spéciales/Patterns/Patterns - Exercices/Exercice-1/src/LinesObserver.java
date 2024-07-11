public class LinesObserver implements Observer {

    private int linesCount = 0;

    @Override
    public void processLine(String line) {
        linesCount++;
    }

    @Override
    public void printResult() {
        System.out.println("Il y avait " + linesCount + " lignes.");
    }
}
