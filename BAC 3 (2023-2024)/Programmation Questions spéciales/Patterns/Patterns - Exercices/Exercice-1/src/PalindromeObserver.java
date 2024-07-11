public class PalindromeObserver implements Observer {

    private int nbrPalindromes = 0;

    @Override
    public void processLine(String line) {
        String[] words = line.split(" ");
        for (String word : words) {
            if (isPalindrome(word)) {
                nbrPalindromes++;
            }
        }
    }

    @Override
    public void printResult() {
        System.out.println("Il y avait " + nbrPalindromes + " palindromes.");
    }

    private boolean isPalindrome(String word) {
        int i = 0;
        int j = word.length() - 1;
        while (i < j) {
            if (word.charAt(i) != word.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
