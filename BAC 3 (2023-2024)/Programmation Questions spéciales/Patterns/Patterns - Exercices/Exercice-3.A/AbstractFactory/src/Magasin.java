import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Magasin {

    private AbstractFactory factory;

    public Magasin(AbstractFactory factory) {
        this.factory = factory;
    }

    private Map<String, Product> bac = new HashMap<>();

    public void ajouterProduit(String name, int anneeDeParution) {
        Product product = factory.createProduct(name, anneeDeParution);
        bac.put(name, product);
    }

    public Product retourneProduit(String name) {
        return bac.get(name);
    }
}
