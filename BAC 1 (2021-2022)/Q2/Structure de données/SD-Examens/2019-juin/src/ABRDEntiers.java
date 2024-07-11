import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ABRDEntiers implements Iterable<Integer>{

	private NoeudEntier racine;


	public ABRDEntiers () {
		racine = null ;
	}
	
	// A NE PAS MODIFIER! VA SERVIR POUR LES TESTS
	// si le parametre est 1, construit l'arbre de l'enonce 
	public ABRDEntiers(int i){
		if(i==1){
			NoeudEntier g2 = new NoeudEntier(new NoeudEntier(1,2),3,1,null);
			NoeudEntier g1 = new NoeudEntier(g2, 7, 3, new NoeudEntier(8, 1));
			NoeudEntier d1 = new NoeudEntier(new NoeudEntier(13,1), 15, 2, null);
			racine = new NoeudEntier(g1,12,2,d1);		
		}
	}

	public boolean estVide () {
		return racine == null;
	}

	// A NE PAS MODIFIER!!!
	// VA SERVIR POUR LES TESTS!!!
	public String toString () {
		return "[ "+toString(racine)+" ]";
	}

	private String toString (NoeudEntier n) {
		if (n==null) 
			return "";
		if (n.gauche == null && n.droit == null) 
			return ""+n.entier+"("+n.nombreOccurrences+"x)";
		if (n.gauche == null) 
			return " [ ] "+n.entier+"("+n.nombreOccurrences+"x)"+" [ "+toString(n.droit)+" ] ";
		if (n.droit == null) 
			return " [ "+toString(n.gauche)+" ] "+n.entier+"("+n.nombreOccurrences+"x)"+ " [ ] ";
		return " [ "+toString(n.gauche)+" ] "+n.entier+"("+n.nombreOccurrences+"x)"+" [ "+toString(n.droit)+" ] ";		
	}

	/**
	 * calcule le nombre d'occurrences de l'entier passe en parametre
	 * @param entier l'entier recherche
	 * @return le nombre d'occurrences de l'entier passe en parametre 
	 */
	public int nombreOccurrences(int entier){
		return nombreOccurrences(racine, entier);
	}

	private int nombreOccurrences(NoeudEntier noeudEntier, int entier) {
		if (noeudEntier == null) return 0;
		if (noeudEntier.entier == entier) return noeudEntier.nombreOccurrences;
		if (noeudEntier.entier > entier) return nombreOccurrences(noeudEntier.gauche, entier);
		return nombreOccurrences(noeudEntier.droit, entier);
	}

	/**
	 * insere un entier dans l'ABR
	 * Les doublons sont acceptes (cfr enonce)
	 * @param entier l'entier a inserer
	 */
	public void insere(int entier) {
		racine = insere(entier, racine);
	}

	private NoeudEntier insere(int entier, NoeudEntier noeudCourant) {
		if (noeudCourant == null) {
			noeudCourant = new NoeudEntier(entier);
		} else if (noeudCourant.entier == entier) {
			noeudCourant.nombreOccurrences++;
		} else if (entier < noeudCourant.entier) {
			noeudCourant.gauche = insere(entier, noeudCourant.gauche);
		} else {
			noeudCourant.droit = insere(entier, noeudCourant.droit);
		}
		return noeudCourant;
	}
	
	public Iterator<Integer> iterator () {
		return new InIterateur(this);
	}

	// classe interne
	private class NoeudEntier {

		private int entier; 
		private int nombreOccurrences;
		private NoeudEntier gauche;
		private NoeudEntier droit;

		
		private NoeudEntier (int entier) {
			this.entier = entier;
			this.gauche = null;
			this.droit = null;
			this.nombreOccurrences=1;
		}
		
		

		private NoeudEntier(int entier, int nombreOccurrences) {
			this.entier = entier;
			this.gauche = null;
			this.droit = null;
			this.nombreOccurrences = nombreOccurrences;
		}

		private NoeudEntier( NoeudEntier gauche,int entier, NoeudEntier droit) {
			super();
			this.entier = entier;
			this.gauche = gauche;
			this.droit = droit;
			this.nombreOccurrences=1;
		}

		private NoeudEntier(NoeudEntier gauche,int entier, int nombreOccurrences,
				NoeudEntier droit) {
			super();
			this.entier = entier;
			this.nombreOccurrences = nombreOccurrences;
			this.gauche = gauche;
			this.droit = droit;
		}
	}
	
	
	private class InIterateur implements Iterator<Integer> {

		private ArrayDeque<Integer> fileDEntiers;

		public InIterateur (ABRDEntiers a) {
			fileDEntiers = new ArrayDeque<>();
			remplirFile(a.racine);
		}

		
		private void remplirFile (NoeudEntier n) {
			if (n == null) return;
			remplirFile(n.gauche);
			for (int i = 0; i < n.nombreOccurrences; i++) {
				fileDEntiers.add(n.entier);
			}
			remplirFile(n.droit);
		}

		
		public boolean hasNext () {
			return !fileDEntiers.isEmpty();
		}

		public Integer next () {
			if (!hasNext()) throw new NoSuchElementException();
			return fileDEntiers.remove();
		}
	}


}
