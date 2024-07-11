public class LivreStrategy implements AbstractFactory {

    @Override
    public Product createProduct(String name, int anneeDeParution) {
        return new Livre(name, anneeDeParution);
    }
}
