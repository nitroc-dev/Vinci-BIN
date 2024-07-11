import java.util.HashMap;


public class DocumentsLRU {

	private Noeud tete, queue;
	private HashMap<String, Noeud> map;
	
	/**
	 * initialise une liste de taille noeuds avec les documents doc1, doc2, ...
	 * @param taille
	 * @throws IllegalArgumentException la liste est de 3 documents minimum
	 */
	public DocumentsLRU(int taille){
		if(taille<3){
			throw new IllegalArgumentException();
		}
		String doc1 = "doc1";
		tete = new Noeud(null,doc1,null);
		map = new HashMap<>();
		Noeud temp = tete;
		map.put(doc1, tete);
		for(int i=2;i<=taille;i++){
			String docI = new String("doc"+i);
			temp.suivant = new Noeud(temp,docI,null);
			temp = temp.suivant;
			map.put(docI, temp);
		}
		queue = temp;
	}
	
	/**
	 * renvoie le noeud contenant le document passe en parametre ou null si le document n'est pas present dans la liste
	 * @param document le document recherche
	 * @return le noeud contenant le document ou null
	 */
	private Noeud donnerNoeud(String document){
		if (map.containsKey(document)) {
			return map.get(document);
		} else {
			return null;
		}
	}
	
	/**
	 * supprime le noeud passe en parametre
	 * @param noeud le noeud a supprimer
	 */
	private void supprimer(Noeud noeud){
		if (map.containsValue(noeud) && noeud != tete && noeud != queue) {
			noeud.precedent.suivant = noeud.suivant;
			noeud.suivant.precedent = noeud.precedent;
			noeud.suivant = null;
			noeud.precedent = null;
			map.remove(noeud.document, noeud);
		}
	}
	
	/**
	 * ajoute un noeud en tete de liste avec le document passe en parametre
	 * @param document le document a ajouter
	 */
	private void ajouterMRU(String document){
		Noeud nouveauNoeud = new Noeud(null, document, tete);
		tete.precedent = nouveauNoeud;
		tete = nouveauNoeud;
		map.put(document, nouveauNoeud);
	}
	
	/**
	 * supprime le noeud (et donc le document!) qui se trouve en fin de liste
	 */
	private void supprimerLRU(){
		map.remove(queue.document, queue);
		queue.precedent.suivant = null;
		queue = queue.precedent;
		queue.precedent = null;
	}

	/**
	 * place le document passe en parametre dans la liste selon le mecanisme LRU
	 * @param document ouvert
	 */
	public void ouvrirDocument(String document){
		Noeud documentOuvert = donnerNoeud(document);
		if (documentOuvert != null) {
			if (queue == documentOuvert) {
				supprimerLRU();
			} else if (tete == documentOuvert) {
				return;
			} else {
				supprimer(documentOuvert);
			}
		}
		ajouterMRU(document);
	}
	
	
	public String toString(){
		String msg = "";
		int size = 0;
		Noeud baladeur = tete;
		while (baladeur != null && size != 5) {
			msg += baladeur.document + " ";
			baladeur = baladeur.suivant;
			size++;
		}
		return msg;
	}
	
	
	private class Noeud {
		private String document;
		private Noeud precedent;
		private Noeud suivant;
		
		public Noeud(Noeud precedent,String document,Noeud suivant) {
			super();
			this.precedent = precedent;
			this.document = document;	
			this.suivant = suivant;
		}
	}
}
