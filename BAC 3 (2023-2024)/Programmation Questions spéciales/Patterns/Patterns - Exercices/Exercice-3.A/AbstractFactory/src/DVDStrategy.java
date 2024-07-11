public class DVDStrategy implements AbstractFactory {

    @Override
    public Product createProduct(String name, int anneeDeParution) {
        return new DVD(name, anneeDeParution);
    }
}
