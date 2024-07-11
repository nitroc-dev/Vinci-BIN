/**
 * Cette liste gere une liste de succession
 * Elle ne peut etre vide
 * Elle ne contient pas de doublons
 * L'actuel leader est place en tete de liste.
 * L'ordre des elements respecte l'ordre de succession
 * 
 * @author 													-------> METTEZ ICI VOS NOM ET PRENOM
 * 
 * 
 */
public class ListeSuccession<E> {

	private Noeud tete,queue;
	private int taille;
	// N'ajoutez pas d'autres attributs
	
	
	/**
	 * construit une liste avec 1 element
	 * @param leader l'element a placer dans la liste
	 * @throws IllegalArgumentException si e est null
	 */
	public ListeSuccession(E leader){
		if(leader==null)
			throw new IllegalArgumentException();
		tete = new Noeud(leader);
		queue = tete;
		taille=1;
	}
		
	
	public String toString(){
		String aRenvoyer = "Le leader : "+tete.element;
		if(taille>1){
			aRenvoyer +="\nEnsuite : ";
			Noeud baladeur=tete.suivant;
			while(baladeur!=null){
				aRenvoyer += baladeur.element + " ";
				baladeur=baladeur.suivant;
			}
			
		}
		return aRenvoyer;
	}
	
	
	/**
	 * renvoie le leader, celui-ci existe toujours et se trouve en tete de liste
	 * @return le leader
	 */
	public E donnerLeader(){
		return tete.element;
	}
	
	/**
	 * recherche la position de l'element dans la liste (L'element de tete se trouve en position 0)
	 * @param element l'element recherche
	 * @return la position ou -1 si l'element n'y est pas
	 * @throws IllegalArgumentException si e est null
	 */
	public int donnerPosition(E element){
		if (element == null) throw new IllegalArgumentException();
		int position = 0;
		Noeud baladeur = tete;
		while (baladeur != null) {
			if (baladeur.element.equals(element)) return position;
			position++;
			baladeur = baladeur.suivant;
		}
		return -1;
	}
	
	/**
	 * renvoie l'element qui se trouve a une position passee en parametre
	 * @param position une position dans la liste (L'element de tete se trouve en position 0)
	 * @return l'element qui se trouve a la position passee en parametre
	 * @throws IllegalArgumentException si pas d'element a cette position
	 */
	public E donnerElement (int position) throws IllegalArgumentException{
		// TODO
		// Veuillez suivre l'indication suivante :
		// La liste peut etre parcourue dans les 2 sens : tete --> queue ou queue --> tete
		// En fonction de la position, choisissez le sens qui permet le parcours le plus court

		if (position > taille/2) {
			Noeud baladeur = queue;
			for (int i = taille-1; i > position; i--) {
				baladeur = baladeur.precedent;
			}
			return baladeur.element;
		} else {
			Noeud baladeur = tete;
			for (int i = 0; i < position; i++) {
				baladeur = baladeur.suivant;
			}
			return baladeur.element;
		}
	}
	
	/**
	 * ajoute a la fin de la liste l'element passe en parametre s'il n'est pas encore present
	 * @param element l'element a ajouter
	 * @return true si l'element a ete ajoute, false sinon
	 * @throws IllegalArgumentException si e est null
	 */
	public boolean ajouterEnFin(E element) {
		if (element == null) throw new IllegalArgumentException();
		if (taille == 0) {
			tete = new Noeud(element);
			queue = tete;
			taille++;
			return true;
		}
		Noeud baladeur = tete;
		while (baladeur.suivant != null) {
			if (baladeur.suivant.element.equals(element)) return false;
			baladeur = baladeur.suivant;
		}
		baladeur.suivant = new Noeud(element);
		baladeur.suivant.precedent = baladeur;
		queue = baladeur.suivant;
		taille++;
		return true;
	}
	
	/**
	 * supprime l'element de tete a condition qu'il ne soit pas le seul element de la liste
	 * @return true si la suppression a pu etre faite, false sinon
	 */
	public boolean supprimerLeader(){
		if (taille == 1) return false;
		tete = tete.suivant;
		taille--;
		return true;
	}
	
	
	// Classe interne 
	private class Noeud{

		private E element;
		private Noeud precedent;
		private Noeud suivant;
		
	
		private Noeud(E e) {
			this(null, e, null);
		}

		private Noeud(Noeud precedent, E e, Noeud suivant) {
			this.element = e;
			this.precedent = precedent;
			this.suivant = suivant;
		}
	}
}
