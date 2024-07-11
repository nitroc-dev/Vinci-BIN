public class MagasinDeLivre extends Magasin {

	@Override
	protected Product createProduct(String name, int anneeDeParution) {
		return new Livre(name, anneeDeParution);
	}
}
