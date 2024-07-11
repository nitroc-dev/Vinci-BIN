import java.util.Objects;

public class Client  {
	private static int numeroSuivant=1;
	private int numero;
	private String nom;
	private String prenom;
	private String telephone;
	
	public Client(String nom, String prenom, String telephone) {
		this.numero = numeroSuivant++;
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
	}
	
	public int getNumero() {
		return numero;
	}
	public String getNom() {
		return nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @return représentation textuelle du client
	 */
	@Override
	public String toString() {
		return "client numero " + numero + " (" + prenom  + " " + nom + ", telephone : " + telephone +")";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Client client = (Client) o;
		return numero == client.numero;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numero);
	}
}
