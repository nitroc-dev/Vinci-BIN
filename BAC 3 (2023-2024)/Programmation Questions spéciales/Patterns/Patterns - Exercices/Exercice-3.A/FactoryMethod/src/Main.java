public class Main {

    public static void main(String[] args) {
        Magasin magasinDeDVD = new MagasinDeDVD();
        magasinDeDVD.ajouterProduit("DVD1", 2010);
        magasinDeDVD.ajouterProduit("DVD2", 2011);

        Magasin magasinDeLivre = new MagasinDeLivre();
        magasinDeLivre.ajouterProduit("Livre1", 2010);
        magasinDeLivre.ajouterProduit("Livre2", 2011);
    }
}
