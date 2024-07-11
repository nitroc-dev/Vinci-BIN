public class TestPersonne {

    public static void main(String[] args) {
        Personne personne1 = new Personne("Redacted", "Redacted", 1, 1, 2000, "Redacted", 10, 1000, "Redacted");

        System.out.println(personne1);
        System.out.println(personne1.getAgeFromBefore(2025));
    }
}
