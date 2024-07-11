import java.util.ArrayList;

public class UnshuffleSort {
	
	private ArrayList<DequeImpl> listeDeque;

	public UnshuffleSort() {
		listeDeque = new ArrayList<>();
	}

	// cfr enonce examen
	public void placerEntier(int entier) {
		for (DequeImpl deque1 : listeDeque) {
			if (deque1.estVide())deque1.ajouterEnPremier(entier);
			if (entier < (int) deque1.premier()){
				deque1.ajouterEnPremier(entier);
				return;
			}
			if (entier > (int) deque1.dernier()){
				deque1.ajouterEnDernier(entier);
				return;
			}
		}
		DequeImpl deque = new DequeImpl();
		deque.ajouterEnPremier(entier);
		listeDeque.add(deque);
	}
	
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (DequeImpl deque1 : listeDeque) {
			sb.append(deque1.toString()).append("\n");
		}
		return sb.toString();
	}

}
