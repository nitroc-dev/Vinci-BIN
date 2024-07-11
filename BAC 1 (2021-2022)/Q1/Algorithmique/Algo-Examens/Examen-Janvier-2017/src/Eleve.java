public class Eleve {
	public static final int GARCON = 1;
	public static final int FILLE = 2;
	String nom;
	String prenom;
	int sexe; 
	int anneeNaissance;
	public String getNom() {
		return nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public int getSexe() {
		return sexe;
	}
	public int getAnneeNaissance() {
		return anneeNaissance;
	}
	@Override
	public String toString() {
		String sex = "";
		switch (sexe){
		case FILLE: 
			sex = "F";
			break;
		case GARCON:
			sex = "H";
			break;
		}
		return "Eleve [nom=" + nom + ", prenom=" + prenom + ", sexe=" + sex + ", anneeNaissance=" + anneeNaissance
				+ "]";
	}
	
	/**
	 * Construit un objet �l�ve
	 * Jette une exception si l'ann�e de naissance n'est pas 
	 * au 20 ou 21eme siecle
	 * @param nom
	 * @param prenom
	 * @param sexe
	 * @param anneeNaissance
	 */
	public Eleve(String nom, String prenom, int sexe, int anneeNaissance) {
		if (anneeNaissance < 1900 || anneeNaissance>2099)
			throw new IllegalArgumentException("Ann�e hors limites");
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.anneeNaissance = anneeNaissance;
	}
	
}
