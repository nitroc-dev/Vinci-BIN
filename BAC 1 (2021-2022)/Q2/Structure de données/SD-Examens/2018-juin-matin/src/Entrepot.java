import java.util.ArrayList;
import java.util.HashMap;

public class Entrepot {

	private Hangar[] listeHangars;
	private HashMap<Societe, ArrayList<Integer>> listeSocietesHangars;
	private int nombreHangarsLibre;
	
	/**
	 * construit un entrepot contenant nombreHangars
	 * @param nombreHangars le nombre d'hangars
	 * @throws IllegalArgumentException si le nombre d'hangars est negatif ou nul
	 */
	public Entrepot(int nombreHangars) {
		if (nombreHangars <= 0) throw new IllegalArgumentException();
		listeHangars = new Hangar[nombreHangars];
		listeSocietesHangars = new HashMap<>();
		nombreHangarsLibre = nombreHangars;
		for (int i = 0; i < nombreHangars; i++) {
			listeHangars[i] = new Hangar(i);
		}
	}
	
	/**
	 * attribue un hangar a la societe passee en parametre
	 * Si l'attribution a pu se faire : 
	 * la societe est enregistree comme presente (si pas encore presente)
	 * le hangar lui est ajoute
	 * @param societe la societe qui demande un hangar
	 * @return le numero du hangar attribue ou -1 s'il n'y en a plus de libre
	 * @throws IllegalArgumentException si la societe est null
	 */
	public int attribuerHangar(Societe societe) {
		if (!listeSocietesHangars.containsKey(societe)) {
			listeSocietesHangars.put(societe, new ArrayList<>());
		}
		ArrayList<Integer> listeHangarsSociete = listeSocietesHangars.get(societe);
		int hangarActuel = societe.hashCode();
		if (hangarActuel > listeHangars.length) {
			return -1;
		}
		if (listeHangars[hangarActuel].getSociete() == null) {
			listeHangars[hangarActuel].setSociete(societe);
			listeHangarsSociete.add(hangarActuel);
			nombreHangarsLibre--;
			return hangarActuel;
		} else {
			if (nombreHangarsLibre == 0) {
				return -1;
			}
			for (int i = hangarActuel; i < listeHangars.length; i++) {
				if (listeHangars[i].getSociete() == null) {
					listeHangars[i].setSociete(societe);
					listeHangarsSociete.add(i);
					nombreHangarsLibre--;
					return i;
				}
			}
			for (int i = 0; i < hangarActuel; i++) {
				if (listeHangars[i].getSociete() == null) {
					listeHangars[i].setSociete(societe);
					listeHangarsSociete.add(i);
					nombreHangarsLibre--;
					return i;
				}
			}
		}
		return -1;
	}
	
	/**
	 * renvoie les numeros des hangars occupes par la societe passee en parametre
	 * @param societe la societe 
	 * @return une arrayList avec les numeros des hangars ou null si la societe n'occupe aucun hangar
	 * @throws IllegalArgumentException si la societe est null
	 */
	public ArrayList<Integer> listeHangars(Societe societe){
		if (societe == null) throw new IllegalArgumentException();
		if (listeSocietesHangars.containsKey(societe)) {
			return listeSocietesHangars.get(societe);
		}
		return null;
	}
	
	
}
