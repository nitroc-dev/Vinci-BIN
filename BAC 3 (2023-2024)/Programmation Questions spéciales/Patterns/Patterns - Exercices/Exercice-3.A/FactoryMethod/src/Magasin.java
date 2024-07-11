import java.util.HashMap;
import java.util.Map;

public abstract class Magasin {

    private Map<String,Product> bac= new HashMap<>();
    public void ajouterProduit(String name, int anneeDeParution){
        Product product = this.createProduct(name, anneeDeParution);
        bac.put(name, product);
    }
    public Product retourneProduit(String name){
        return bac.get(name);
    }

    protected abstract Product createProduct(String name, int anneeDeParution);
}
