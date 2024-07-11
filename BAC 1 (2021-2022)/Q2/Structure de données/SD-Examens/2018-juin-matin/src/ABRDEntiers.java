import java.util.ArrayList;

public class ABRDEntiers {
	
	private NoeudEntier racine;

	public ABRDEntiers () {
		racine = null ;
	}
	
	// A NE PAS MODIFIER! VA SERVIR POUR LES TESTS!!!
	/**
	 * insere un entier dans l'ABR
	 * Les doublons sont acceptes 
	 * @param entier l'entier a inserer
	 */
	public void insere (int entier) {		
		racine = insere(racine, entier);
	}
	
	// A NE PAS MODIFIER! VA SERVIR POUR LES TESTS!!!
	private NoeudEntier insere (NoeudEntier n, int entier) {	
		if (n == null) {
			return new NoeudEntier(entier);
		} else {
			if (entier < n.entier)
				n.gauche = insere(n.gauche, entier);
			else 
				n.droit = insere(n.droit, entier);
		}
		return n;
	}
	
	/**
	 * remplit et revoie une arrayList avec les entiers contenus dans l'arbre
	 * les entiers y seront places par ordre croissant
	 * @return une arrayList avec les entiers contenus dans l'arbre 
	 */
	public ArrayList<Integer> toArrayList(){
		ArrayList<Integer> liste = new ArrayList<>();
		return toArrayList(racine, liste);
	}

	private ArrayList<Integer> toArrayList(NoeudEntier noeudEntier, ArrayList<Integer> liste) {
		if (noeudEntier == null) return liste;
		toArrayList(noeudEntier.gauche, liste);
		liste.add(noeudEntier.entier);
		toArrayList(noeudEntier.droit, liste);
		return liste;
	}


	
	/**
	 * calcule et renvoie le nombre d'entiers compris entre entier1 et entier2
	 * @param entier1
	 * @param entier2
	 * @return le nombre d'entiers compris entre entier1 et entier2
	 * @throws IllegalArgumentException si entier1 > entier2
	 */
	public int nombreEntiersComprisEntre(int entier1, int entier2){
		if (entier1 > entier2) throw new IllegalArgumentException();
		return nombreEntiersComprisEntre(racine, entier1, entier2);
	}

	private int nombreEntiersComprisEntre(NoeudEntier noeudEntier, int entier1, int entier2) {
		if (noeudEntier == null) return 0;
		if (noeudEntier.entier < entier1) return nombreEntiersComprisEntre(noeudEntier.droit, entier1, entier2);
		if (noeudEntier.entier > entier2) return nombreEntiersComprisEntre(noeudEntier.gauche, entier1, entier2);
		return 1 + nombreEntiersComprisEntre(noeudEntier.gauche, entier1, entier2) + nombreEntiersComprisEntre(noeudEntier.droit, entier1, entier2);
	}
	
	// classe interne
	public class NoeudEntier {

      private int entier; 
		private NoeudEntier gauche;
		private NoeudEntier droit;

		private NoeudEntier (int entier) {
			this.entier = entier;
			this.gauche = null;
			this.droit = null;
		}
		
		private NoeudEntier (NoeudEntier g, int entier, NoeudEntier d) {
			this.entier = entier;
			this.gauche = g;
			this.droit = d;
		}
	}

}
