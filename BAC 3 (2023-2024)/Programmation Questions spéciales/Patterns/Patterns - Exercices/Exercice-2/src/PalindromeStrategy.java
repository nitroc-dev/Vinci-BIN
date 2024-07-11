public class PalindromeStrategy implements Strategy {

    @Override
    public boolean imprimerSi(String c) {
        return isPalindrome(c);
    }

    public boolean isPalindrome(String c) {
        if (c == null)
            return false;
        StringBuffer stringbuffer = new StringBuffer(c);
        return c.equals(stringbuffer.reverse().toString());
    }
}
