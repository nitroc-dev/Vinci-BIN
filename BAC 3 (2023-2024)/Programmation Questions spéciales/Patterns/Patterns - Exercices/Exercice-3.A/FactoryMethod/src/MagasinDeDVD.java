public class MagasinDeDVD extends Magasin {

	@Override
	protected Product createProduct(String name, int anneeDeParution) {
		return new DVD(name, anneeDeParution);
	}
}
