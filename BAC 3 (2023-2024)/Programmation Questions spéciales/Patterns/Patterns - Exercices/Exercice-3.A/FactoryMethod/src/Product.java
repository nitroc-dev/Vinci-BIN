public abstract class Product {

    private String name;
    private int anneeDeParution;
    private double prix;

    public Product(String name, int anneeDeParution, double prix){
        this.name=name;
        this.anneeDeParution=anneeDeParution;
        this.prix=prix;
    }

    public String getName() {
        return name;
    }

    public int getAnneeDeParution() {
        return anneeDeParution;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(int prix){
        this.prix=prix;
    }
}
