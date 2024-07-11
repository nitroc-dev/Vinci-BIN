public class Main {

    public static void main(String[] args) {
        Magasin magasinDeDVD = new Magasin();

        magasinDeDVD.ajouterProduit("DVD1", 2010, new DVDStrategy());
        magasinDeDVD.ajouterProduit("DVD2", 2011, new DVDStrategy());

        Magasin magasinDeLivre = new Magasin();
        magasinDeLivre.ajouterProduit("Livre1", 2010, new LivreStrategy());
        magasinDeLivre.ajouterProduit("Livre2", 2011, new LivreStrategy());
    }
}
