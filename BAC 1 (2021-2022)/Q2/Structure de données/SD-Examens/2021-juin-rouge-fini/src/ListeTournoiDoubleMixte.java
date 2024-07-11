import java.util.HashMap;

public class ListeTournoiDoubleMixte {

	private Noeud tete; 
	private Noeud queue; 
	private HashMap<Etudiant, Noeud> mapEtudiantNoeud;

	public ListeTournoiDoubleMixte() {
		mapEtudiantNoeud = new HashMap<Etudiant, Noeud>();
		tete=null;
		queue=null;
	}
	
	/**
	 * determine le nombre d'etudiants 
	 * @return le nombre d'etudiants
	 */
	public int taille () {
		return mapEtudiantNoeud.size();
	}

	/**
	 * verifie si la liste ne contient aucun etudiant
	 * @return true si la liste est vide, false sinon
	 */
	public boolean estVide () {
		return mapEtudiantNoeud.isEmpty();
	}
	
	
	/**
	 * calcule le nombre d'etudiant appartenant au bloc passe en parametre
	 * @param bloc le numero du bloc
	 * @return le nombre d'etudiant appartenant au bloc passe en parametre
	 */
	public int donnerNombreEtudiantsBloc(int bloc){
		if (estVide()) return 0;
		int nbEtudiants = 0;
		Noeud courant = tete;
		while (courant != null) {
			if (courant.etudiant.getBloc() == bloc) {
				nbEtudiants++;
			}
			courant = courant.suivant;
		}
		return nbEtudiants;
	}
	
	
	
	/**
	 * verifie si tous les etudiants sont en section info ("INFO")
	 * @return true si tous les etudiants sont en section INFO
	 */
	public boolean sontTousEnSectionInfo(){
		if (estVide()) return true;
		Noeud courant = tete;
		while (courant != null) {
			if (!courant.etudiant.getSection().equals("INFO")) {
				return false;
			}
			courant = courant.suivant;
		}
		return true;
	}
	
	
	/**
	 * donne le partenaire de l'etudiant passe en parametre
	 * @param etudiant l'etudiant dont on cherche le partenaire
	 * @return le partenaire ou null si l'etudiant n'est pas present dans la liste
	 * @throws IllegalArgumentException si le parametre est null
	 */
	public Etudiant donnerPartenaire(Etudiant etudiant){
		if(etudiant==null)
			throw new IllegalArgumentException();
		if (estVide()) return null;
		if (!mapEtudiantNoeud.containsKey(etudiant)) {
			return null;
		}
		if (etudiant.getSexe() == 'M') {
			return mapEtudiantNoeud.get(etudiant).suivant.etudiant;
		} else {
			return mapEtudiantNoeud.get(etudiant).precedent.etudiant;
		}
	}
		
	/**
	 * ajoute les 2 etudiants en fin de liste
	 * les 2 etudiants doivent etre de sexe oppose et ne peuvent pas encore etre presents dans la liste
	 * @param etudiant1 un des etudiants du couple
	 * @param etudiant2 l'autre etudiant du couple
	 * @return true si l'ajout a pu se faire, false sinon
	 * @throws IllegalArgumentException si un des parametres est null
	 * 
	 */
	public boolean ajouterCouple (Etudiant etudiant1, Etudiant etudiant2) {
		if(etudiant1==null||etudiant2==null)
			throw new IllegalArgumentException();
		Noeud nouveauNoeud1;
		Noeud nouveauNoeud2;
		if (etudiant1.getSexe() == 'M') {
			nouveauNoeud1 = new Noeud(etudiant1);
			nouveauNoeud2 = new Noeud(etudiant2);
		} else {
			nouveauNoeud1 = new Noeud(etudiant2);
			nouveauNoeud2 = new Noeud(etudiant1);
		}
		if (mapEtudiantNoeud.containsKey(etudiant1) || mapEtudiantNoeud.containsKey(etudiant2)) return false;
		if (etudiant1.getSexe() == etudiant2.getSexe()) return false;
		if (estVide()) {
			tete = nouveauNoeud1;
			queue = nouveauNoeud2;
			tete.suivant = queue;
			queue.precedent = tete;
		} else {
			queue.suivant = nouveauNoeud1;
			nouveauNoeud1.precedent = queue;
			nouveauNoeud1.suivant = nouveauNoeud2;
			nouveauNoeud2.precedent = nouveauNoeud1;
			queue = nouveauNoeud2;
		}
		mapEtudiantNoeud.put(etudiant1, nouveauNoeud1);
		mapEtudiantNoeud.put(etudiant2, nouveauNoeud2);
		return true;
	}


	// pour les tests
	public ListeTournoiDoubleMixte(Etudiant[] tableACopier) {	
		mapEtudiantNoeud = new HashMap<Etudiant, Noeud>();
		if(tableACopier.length==0)
			return;
		tete = new Noeud(tableACopier[0]);
		mapEtudiantNoeud.put(tableACopier[0], tete);
		Noeud prec = tete;
		for (int i = 1; i < tableACopier.length; i++) {
			Noeud nouveauNoeud = new Noeud(tableACopier[i]);
			mapEtudiantNoeud.put(tableACopier[i], nouveauNoeud);
			nouveauNoeud.precedent = prec;
			prec.suivant = nouveauNoeud;
			prec = nouveauNoeud;
		}
		queue = prec;
	}

	// pour les tests
	public String teteQueue(){
		try{
			String aRenvoyer = "(";
			Noeud baladeur = tete;
			int cpt=0;
			while (baladeur != null) {
				if(cpt==0)
					aRenvoyer += baladeur.etudiant.getNom();
				else
					aRenvoyer += ","+baladeur.etudiant.getNom();
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
			Noeud baladeur = queue;
			int cpt=0;
			while (baladeur != null) {
				if(cpt==0)
					aRenvoyer += baladeur.etudiant.getNom();
				else
					aRenvoyer += ","+baladeur.etudiant.getNom();
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
		
		private Etudiant etudiant;
		private Noeud suivant;
		private Noeud precedent;

		private Noeud(Etudiant etudiant) {
			this(null, etudiant, null);
		}

		private Noeud(Noeud precedent, Etudiant etudiant, Noeud suivant) {
			this.etudiant = etudiant;
			this.suivant = suivant;
			this.precedent = precedent;
		}
	}

}
