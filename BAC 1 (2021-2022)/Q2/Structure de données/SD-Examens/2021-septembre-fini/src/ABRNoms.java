import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ABRNoms implements Iterable<String>{

	private NoeudNom racine;

	public ABRNoms () {
		racine = null;
	}

	// A NE PAS MODIFIER!!!
	// VA SERVIR POUR LES TESTS!!!
	/**
	 * insere le nom (Apres insertion l'arbre est toujours un ABR)
	 * @param nom le nom a inserer
	 */
	public void insere (String nom) {
		racine = insere(racine, nom);
	}

	private NoeudNom insere (NoeudNom noeud, String nom) {	
		if (noeud == null) {
			return new NoeudNom(nom);
		} else {
			if (nom.compareTo(noeud.nom) < 0)
				noeud.gauche = insere(noeud.gauche, nom);
			else 
				noeud.droit = insere(noeud.droit, nom);
		}
		return noeud;
	}

	/**
	 * calcule le nombre de noms commencant par la lettre passee en parametre
	 * un meme nom sera comptabilise autant de fois qu'il y est
	 * dans l'exemple de l'enonce, le nombre de noms commencant par 'l' est 5
	 * @param lettre la lettre recherchee
	 * @return le nombre de noms commencant par la lettre passee en parametre
	 * @throws IllegalArgumentException si la lettre n'est pas une lettre minuscule comprise entre 'a' et 'z'
	 */
	public int nombreNomsCommencantPar(char lettre){
		//TODO
		//Attention a l'efficacite de cette methode
		//Il n'est pas necessaire de parcourir tout l'arbre pour trouver ce nombre
		//l'arbre est un ABR
		
		//pour obtenir la premiere lettre, utilisez la methode charAt()
		if (lettre < 'a' || lettre > 'z') throw new IllegalArgumentException();
		return nombreNomsCommencantPar(racine, lettre);
	}

	private int nombreNomsCommencantPar(NoeudNom noeud, char lettre){
		if (noeud == null) return 0;
		else {
			if (noeud.nom.charAt(0) == lettre) return 1 + nombreNomsCommencantPar(noeud.gauche, lettre) + nombreNomsCommencantPar(noeud.droit, lettre);
			else return nombreNomsCommencantPar(noeud.gauche, lettre) + nombreNomsCommencantPar(noeud.droit, lettre);
		}
	}
	
	/**
	 * construit l'ensemble des noms rencontres plus d'une fois
	 * dans l'exemple de l'enonce, l'ensemble des homonymes est : lea , leo et tim
	 * @return un ensemble avec les homonymes
	 */
	public HashSet<String> ensembleHomonymes(){
		//TODO
		//piste de solution :
		//Au fur et a mesure du parcours de l'arbre vous allez remplir 2 ensembles : l'ensemble avec les noms deja rencontres
		//et l'ensemble des homonymes 
		//pour chaque nom rencontre, vous le placerez dans l'ensemble des homonymes si au moment ou il est rencontre,
		//celui-ci fait deja partie de l'ensemble des noms rencontres

		return ensembleHomonymes(racine, new HashSet<>(), new HashSet<>());
	}

	private HashSet<String> ensembleHomonymes(NoeudNom noeud, HashSet<String> rencontré, HashSet<String> homonymes){
		if (noeud == null) return homonymes;
		else {
			if (rencontré.contains(noeud.nom)) homonymes.add(noeud.nom);
			else rencontré.add(noeud.nom);
			ensembleHomonymes(noeud.gauche, rencontré, homonymes);
			ensembleHomonymes(noeud.droit, rencontré, homonymes);
			return homonymes;
		}
	}

	
	/**
	 * l'iterateur permet de parcourir les noms selon l'ordre alpabetique ascendant (a --> z)
	 * @return un iterateur "ascendant"
	 */
	public Iterator<String> iterator(){
		return new Iterateur();
	}

	/**
	 * l'iterateur permet de parcourir les noms selon l'ordre alpabetique descendant (z --> a)
	 * dans l'exemple de l'enonce, l'iterateur renverra les noms dans ce ordre :
	 * tim tim leo leo leo lea laure anouk
	 * @return un iterateur "descendant"
	 */
	public Iterator<String> descendingIterator(){
		return new IterateurDescendant();
	}


	// classe interne
	public class NoeudNom {

		private String nom; 
		private NoeudNom gauche;
		private NoeudNom droit;

		private NoeudNom (String string){
			this.nom = string;
			this.gauche = null;
			this.droit = null;
		}

	}
	
	private class Iterateur implements Iterator<String> {

		private ArrayDeque<String> fileNoms;
		
		public Iterateur() {
			fileNoms = new ArrayDeque<String>();
			remplirFile(racine);
		}
		
		private void remplirFile (NoeudNom n) {
			if (n == null) return;
			remplirFile(n.gauche);
			fileNoms.addLast(n.nom);
			remplirFile(n.droit);
		}

		public boolean hasNext() {
			return !fileNoms.isEmpty();
		}

		public String next() {
			if(fileNoms.isEmpty())
				throw new NoSuchElementException();
			return fileNoms.removeFirst();
		}	
	}

	private class IterateurDescendant implements Iterator<String> {

		private ArrayDeque<String> fileNoms;

		public IterateurDescendant() {
			fileNoms = new ArrayDeque<String>();
			remplirFile(racine);
		}

		private void remplirFile (NoeudNom n) {
			if (n == null) return;
			remplirFile(n.gauche);
			fileNoms.addLast(n.nom);
			remplirFile(n.droit);
		}

		@Override
		public boolean hasNext() {
			return !fileNoms.isEmpty();
		}

		@Override
		public String next() {
			return fileNoms.removeLast();
		}
	}
	

}
