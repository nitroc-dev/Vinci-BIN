import java.util.Arrays;

public class Classe {
	private static final int TAILLE = 50;
	private Eleve [] classe;
	private int nombreDeGarcons;
	private int nombreDEleves; // Taille logique
	
	/**
	 * Construit un objet de type classe en allouant 
	 * un tableau contenant TAILLE élèves.
	 */
	public Classe(){
		this.classe = new Eleve[TAILLE];
	}
	
	/**
	 * Ajoute un élève de la façon décrite dans l'énoncé
	 * 
	 * @param eleve
	 * @return false s'il n'y a plus de place dans le tableau, true sinon
	 */
	public boolean ajoutEleve(Eleve eleve){
		if (nombreDEleves == TAILLE) {
			return false;
		}
		int nombreDeFilles = nombreDEleves - nombreDeGarcons;
		if (eleve.getSexe() == 1) {
			classe[TAILLE - nombreDeGarcons - 1] = eleve;
			nombreDeGarcons++;
		} else {
			classe[nombreDeFilles] = eleve;
		}
		nombreDEleves++;
		return true;
	}
	
	/**
	 * Renvoie un tableau d'élèves dont l'année de naissance est l'année
	 * la moins représentée dans la classe.
	 * En cas d'égalité entre deux année, on renvoie les élève appartenant à
	 * l'année la plus ancienne.
	 * L'année choisie doit au moins avoir un élève la représentant dans la classe
	 * 
	 * On supposera que tous les élèves sont nés au 20ème ou 21ème siècle
	 * (il n'y a donc que 200 valeurs différentes possibles).
	 * @return les élèves dont l'année de naissance est l'année
	 * la moins représentée dans la classe.
	 * S'il n'y a pas d'élève dans la classe, on renverra un tableau 
	 * de taille 0.
	 */
	Eleve [] elevesDELAnneeLaMoinsPresente(){
		int[] tabFrequences = new int[200];
		for (int i = 0; i < nombreDEleves - nombreDeGarcons; i++) {
			tabFrequences[classe[i].getAnneeNaissance() - 1900]++;
		}

		//recherche de l'annee la moins presente
		int nbElevesDeLAnnee = Integer.MAX_VALUE;
		int indiceAnneeMoinsPresente = 0;
		for (int i = 0; i < tabFrequences.length; i++) {
			if (tabFrequences[i] < nbElevesDeLAnnee && tabFrequences[i] > 0) {
				nbElevesDeLAnnee = tabFrequences[i];
				indiceAnneeMoinsPresente = i;
			}
		}

		//creation de la table de retour avec les eleves de l'annee la moins presente
		Eleve[] fillesAnnees = new Eleve[nbElevesDeLAnnee];
		int compteurFilles = 0;
		for (int i = 0; i < nombreDEleves - nombreDeGarcons; i++) {
			if (classe[i].getAnneeNaissance() == indiceAnneeMoinsPresente + 1900) {
				fillesAnnees[compteurFilles] = classe[i];
				compteurFilles++;
			}
		}
		return fillesAnnees;
	}

	@Override
	public String toString() {
		return "Classe: " + Arrays.toString(classe);
	}
	
	
}
