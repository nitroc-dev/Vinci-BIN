import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class DequeImpl<E> implements Deque<E>{

	private Object[] table;
	private int indicePremier; 
	private int indiceDernier;
	private int taille;
	private int numVersion = 0; // pour l'iterateur

	public DequeImpl(){
		table = new Object[5];
		indicePremier = -1;
		indiceDernier = -1;
		taille = 0;
	}

	// Va servir pour les tests 
	// A ne pas modifier!!!
	public DequeImpl(Object[] tableARecopier){
		if(tableARecopier==null)
			throw new IllegalArgumentException();
		table = new Object[tableARecopier.length+4];
		for (int i = 0; i < tableARecopier.length; i++) {
			table[i+2]=tableARecopier[i];
		}
		indicePremier = 2;
		indiceDernier = indicePremier+tableARecopier.length-1;
		taille = tableARecopier.length;
	}

	// Va servir pour les tests 
	// A ne pas modifier!!!
	public String toString(){
		String aRenvoyer = "";
		for (int i = indicePremier; i <= indiceDernier; i++) {
			aRenvoyer += " "+table[i];
		}
		return aRenvoyer;
	}


	public boolean estVide() {
		return taille==0;
	}

	public int taille() {
		return taille;
	}

	private void agrandirTable() {
		Object[] nouvelleTable = new Object[table.length*2];
		int j = taille/2;
		for (int i = indicePremier; i <= indiceDernier; i++) {
			nouvelleTable[j] = table[i];
			j++;
		}
		indicePremier = taille/2;
		indiceDernier = indicePremier+taille-1;
		table = nouvelleTable;
	}

	
	public void ajouterEnPremier(E element) {
		if (element == null) return;
		numVersion++;
		if (taille == 0) {
			table[table.length/2] = element;
			indicePremier = table.length/2;
			indiceDernier = indicePremier;
			taille++;
			return;
		}
		if (indicePremier == 0) agrandirTable();
		table[--indicePremier] = element;
		taille++;
	}

	@Override
	public void ajouterEnDernier(E element) {
		numVersion++;
		if (element == null) return;
		if (taille == 0) {
			table[table.length/2] = element;
			indicePremier = table.length/2;
			indiceDernier = indicePremier;
			taille++;
			return;
		}
		if (indiceDernier == table.length-1) agrandirTable();
		table[++indiceDernier] = element;
		taille++;
	}

	@Override
	public E premier() throws DequeVideException {
		if (this.estVide()) throw new DequeVideException();
		return (E) table[indicePremier];
	}

	@Override
	public E dernier() throws DequeVideException {
		if (this.estVide()) throw new DequeVideException();
		return (E) table[indiceDernier];
	}

	@Override
	public E supprimerPremier() throws DequeVideException {
		numVersion++;
		if (this.estVide()) throw new DequeVideException();
		E element = (E) table[indicePremier];
		table[indicePremier] = null;
		indicePremier++;
		taille--;
		return element;
	}

	@Override
	public E supprimerDernier() throws DequeVideException {
		numVersion++;
		if (this.estVide()) throw new DequeVideException();
		E element = (E) table[indiceDernier];
		table[indiceDernier] = null;
		indiceDernier--;
		taille--;
		return element;
	}

	@Override
	public Iterator<E> iterator() {
		return new IterateurImpl<E>();
	}

	private class IterateurImpl<E> implements Iterator<E>{

		private int indiceCourant;
		private int version;

		private IterateurImpl() {
			indiceCourant = indicePremier;
			version = numVersion;
		}

		@Override
		public boolean hasNext() {
			if (estVide()) return false;
			return indiceCourant <= indiceDernier;
		}

		@Override
		public E next() {
			if (!hasNext()) throw new NoSuchElementException();
			return (E) table[indiceCourant++];
		}

		@Override
		// A NE PAS COMPLETER : Les suppressions sont interdites
		public void remove() {
			throw new UnsupportedOperationException();			
		}

	}
}
