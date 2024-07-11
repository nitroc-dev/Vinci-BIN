import java.util.ArrayDeque;
import java.util.HashMap;


public class Brocante {
	
	private int phase;
	private String[] tableEmplacements;
	private HashMap<String, Integer> mapNombreEmplacements;
	private ArrayDeque<Integer> emplacementsLibres;
	
	
	
	/**
	 * initialise une brocante avec nombre emplacements
	 * @param nombreEmplacements le nombre d'emplacements
	 * @param tableRiverains la table des riverains 
	 */
	public Brocante(int nombreEmplacements, String[] tableRiverains){
		if(nombreEmplacements<1)
			throw new IllegalArgumentException();
		if(tableRiverains==null)
			throw new IllegalArgumentException();
		this.phase=1;
		this.tableEmplacements = new String[nombreEmplacements];
		mapNombreEmplacements = new HashMap<>();
	}
	
	/**
	 * reserve l'emplacement qui porte le numero passe en parametre au demandeur passe en parametre
	 * La reservation reussit si
	 *     l'emplacement est libre
	 *     le demandeur est bien un riverain
	 *     le riverain n'a pas encore 3 emplacements
	 * @param demandeur le riverain qui demande un emplacement
	 * @param numeroEmplacement le numero de l'emplacement souhaite
	 * @return true si la reservation a reussi, false sinon
	 * @throws IllegalArgumentException si le numero de l'emplacement n'existe pas
	 * @throws IllegalStateException si on n'est pas en phase 1
	 */
	public boolean reserver(String demandeur,int numeroEmplacement){
		if(numeroEmplacement<0 || numeroEmplacement>=tableEmplacements.length) throw new IllegalArgumentException();
		if (phase!=1) throw new IllegalStateException();
		if(tableEmplacements[numeroEmplacement]==null){
			tableEmplacements[numeroEmplacement]=demandeur;
			if(mapNombreEmplacements.containsKey(demandeur)) {
				mapNombreEmplacements.put(demandeur, mapNombreEmplacements.get(demandeur) + 1);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * a comme effet de passer de la phase 1 a la phase 2
	 * si deja en phase 2, rien ne doit etre fait
	 */
	public void changerPhase(){
		if (phase == 1) {
			phase = 2;
			emplacementsLibres = new ArrayDeque<>();
			for (int i = 0; i < tableEmplacements.length; i++) {
				if (tableEmplacements[i] == null) {
					assert tableEmplacements[i] != null;
					emplacementsLibres.add(i);
				}
			}
		}
	}
	
	/**
	 * attribue automatiquement un emmplacement libre au demandeur passe en parametre
	 * @param demandeur le demandeur d'un emplacement
	 * @return le numero de l'emplacement attribue ou -1 si plus d'emplacement libre
	 * @throws IllegalStateException si on n'est pas en phase 2
	 */
	public int attribuerAutomatiquementEmplacement(String demandeur){
		if (phase != 2) throw new IllegalStateException();
		if(emplacementsLibres.isEmpty()) return -1;
		int emplacement = emplacementsLibres.poll();
		tableEmplacements[emplacement]=demandeur;
		if(mapNombreEmplacements.containsKey(demandeur)) {
			mapNombreEmplacements.put(demandeur, mapNombreEmplacements.get(demandeur) + 1);
		}
		return emplacement;
	}
	
	/**
	 * renvoie, sous forme d'une chaine de caracteres, tous les numeros des emplacements et leurs eventuels occupants
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tableEmplacements.length; i++) {
			sb.append(i);
			sb.append(" : ");
			if(tableEmplacements[i]==null) {
				sb.append("libre");
			}else {
				sb.append(tableEmplacements[i]);
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	

}
