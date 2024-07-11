import java.util.HashMap;
import java.util.HashSet;

public class ListeDoubleMixte {

	private Noeud tete; //sentinelle tete
	private Noeud queue; //sentinelle queue 
	private HashMap<JoueurTennis, Noeud> mapJoueurNoeud; 

	public ListeDoubleMixte() {
		mapJoueurNoeud = new HashMap<JoueurTennis, Noeud>();
		tete = new Noeud();
		queue = new Noeud();
		tete.suivant = queue;
		queue.precedent = tete;
	}
	
	/**
	 * determine le nombre de joueurs 
	 * @return le nombre de joueurs
	 */
	public int taille () {
		return mapJoueurNoeud.size();
	}

	/**
	 * verifie si la liste ne contient aucun joueur
	 * @return true si la liste est vide, false sinon
	 */
	public boolean estVide () {
		return mapJoueurNoeud.isEmpty();
	}
	
	
	/**
	 * verifie s'il y a au moins un joueur de nationalite belge ("B")
	 * @return true s'il y a au moins un joueur de nationalite belge, false sinon
	 */
	public boolean auMoins1Belge(){
		if (estVide()) return false;
		Noeud courant = tete.suivant;
		while (courant != queue) {
			if (courant.joueur.getNationalite().equals("B")) return true;
			courant = courant.suivant;
		}
		return false;		
	}
	
	
	/**
	 * calcule le nombre de nationalites differentes
	 * @return le nombre de nationalites differentes
	 */
	public int nombreNationalitesDifferentes(){
		if (estVide()) return 0;
		Noeud baladeur = tete.suivant;
		HashSet<String> set = new HashSet<String>();
		while (baladeur != queue) {
			set.add(baladeur.joueur.getNationalite());
			baladeur = baladeur.suivant;
		}
		return set.size();
	}
	
	
	/**
	 * verifie la presence d'un joueur dans la liste
	 * @param joueur le joueur recherche
	 * @return true si le joueur est present, false sinon
	 * @throws IllegalArgumentException si le parametre est null
	 */
	public boolean estPresent(JoueurTennis joueur){
		if(joueur == null)
			throw new IllegalArgumentException();
		return mapJoueurNoeud.containsKey(joueur);
	}
		
	/**
	 * supprime un joueur et son partenaire
	 * @param joueur un des joueurs du couple a supprimer
	 * @return true si la suppression a pu se faire (joueur present), false sinon
	 * @throws IllegalArgumentException si le parametre est null
	 * 
	 */
	public boolean supprimerCouple(JoueurTennis joueur) {
		if(joueur == null)
			throw new IllegalArgumentException();
		if (!estPresent(joueur)) return false;
		Noeud par1 = mapJoueurNoeud.get(joueur);
		Noeud par2;
		if (par1.joueur.getSexe() == 'H') {
			par2 = par1.suivant;
			par1.precedent.suivant = par2.suivant;
			par2.suivant.precedent = par1.precedent;
		} else {
			par2 = par1.precedent;
			par2.precedent.suivant = par1.suivant;
			par1.suivant.precedent = par2.precedent;
		}
		mapJoueurNoeud.remove(par1.joueur);
		mapJoueurNoeud.remove(par2.joueur);
		return true;
	}

	// pour les tests
	public ListeDoubleMixte(JoueurTennis[] tableACopier) {	
		mapJoueurNoeud = new HashMap<JoueurTennis, Noeud>();
		if(tableACopier.length==0){
			tete = new Noeud();
			queue = new Noeud();
			tete.suivant = queue;
			queue.precedent = tete;
			return;
		}
		tete = new Noeud();
		Noeud nouveauNoeud = new Noeud(tableACopier[0]);
		tete.suivant = nouveauNoeud;
		nouveauNoeud.precedent = tete;
		mapJoueurNoeud.put(tableACopier[0], nouveauNoeud);
		Noeud prec = nouveauNoeud;
		for (int i = 1; i < tableACopier.length; i++) {
			nouveauNoeud = new Noeud(tableACopier[i]);
			mapJoueurNoeud.put(tableACopier[i], nouveauNoeud);
			nouveauNoeud.precedent = prec;
			prec.suivant = nouveauNoeud;
			prec = nouveauNoeud;
		}
		queue = new Noeud();
		prec.suivant = queue;
		queue.precedent = prec;
	}

	// pour les tests
	public String teteQueue(){
		try{
			String aRenvoyer = "(";
			Noeud baladeur = tete.suivant;
			int cpt=0;
			while (baladeur != queue) {
				if(cpt==0)
					aRenvoyer += baladeur.joueur.getNom();
				else
					aRenvoyer += ","+baladeur.joueur.getNom();
				baladeur = baladeur.suivant;
				cpt++;
				if(cpt==100){
					return "boucle infinie";
				}
			}
			return aRenvoyer+")";
		}catch (NullPointerException e){
			return "nullPointerException";
		}
	}

	// pour les tests
	public String queueTete(){
		try{
			String aRenvoyer = "(";
			Noeud baladeur = queue.precedent;
			int cpt=0;
			while (baladeur != tete) {
				if(cpt==0)
					aRenvoyer += baladeur.joueur.getNom();
				else
					aRenvoyer += ","+baladeur.joueur.getNom();
				baladeur = baladeur.precedent;
				cpt++;
				if(cpt==100){
					return "boucle infinie";
				}
			}
			return aRenvoyer+")";
		}catch (NullPointerException e){
			return "nullPointerException";
		}
	}

	// Classe interne Noeud
	private class Noeud{
		
		private JoueurTennis joueur;
		private Noeud suivant;
		private Noeud precedent;
		
		private Noeud(){
			this(null,null,null);
		}

		private Noeud(JoueurTennis joueur) {
			this(null, joueur, null);
		}

		private Noeud(Noeud precedent, JoueurTennis joueur, Noeud suivant) {
			this.joueur = joueur;
			this.suivant = suivant;
			this.precedent = precedent;
		}
	}

}
