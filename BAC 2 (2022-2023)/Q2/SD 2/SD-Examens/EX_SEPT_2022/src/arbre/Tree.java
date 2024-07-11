package arbre;

import java.util.Arrays;
import java.util.Iterator;

public class Tree implements Iterable<Tree> {

	private int value;

	private Tree parent;

	private Tree[] children;

	// *******************************************************
	// CONSTRUCTEURS
	// *******************************************************
	public Tree(int v, Tree[] chd) {
		value = v;
		children = chd;

		for (Tree child : chd) {
			child.parent = this;
		}
	}

	public Tree(int v) {
		this(v, new Tree[0]);
	}

	// *******************************************************
	// GETTERS
	// *******************************************************
	public int getValue() {
		return value;
	}

	public Tree getParent() {
		return parent;
	}

	public Tree[] getChildren() {
		return children;
	}

	// *******************************************************
	// ITERATEUR
	// *******************************************************
	public Iterator<Tree> iterator() {
		return Arrays.asList(children).iterator();
	}

	public int nbrChildren() {
		return children.length;
	}

	public boolean isLeaf() {
		return children.length == 0;
	}

	// Cette m�thode renvoie une copie de this c�d une nouvelle instance de Tree ayant les m�mes �l�ments au m�me endroit que l'objet courant
	public Tree clone() {
		Tree[] childrenClone = new Tree[children.length];
		for (int i = 0; i < children.length; i++) {
			childrenClone[i] = children[i].clone();
		}
		return new Tree(value, childrenClone);
	}

	// Cette m�thode imprime � la sortie standard tous les noeuds
	// suivi de leurs anc�tres jusqu�� la racine.
	public void afficherNoeudsAvecAncetres() {
		afficherPrivate(this);
	}

	private void afficherPrivate(Tree tree) {
		if (tree == null) {
			return;
		}

		System.out.print(tree.value + " ");

		Tree parent = tree.getParent();

		while (parent != null) {
			System.out.print(parent.getValue() + " ");
			parent = parent.getParent();
		}

		System.out.println();

		Tree[] children = tree.getChildren();
		for (Tree child : children) {
			afficherPrivate(child);
		}
	}

	public static void main(String[] args) {
		Tree l6 = new Tree(6);
		Tree l1 = new Tree(1);
		Tree t9 = new Tree(9, new Tree[] { l6, l1 });
		Tree l3 = new Tree(3);
		Tree l8 = new Tree(8);
		Tree t8 = new Tree(8, new Tree[] { l3, l8 });
		Tree l4 = new Tree(4);
		Tree t4 = new Tree(4, new Tree[] { t8, t9, l4});
		Tree t4bis= t4.clone();
		t4.afficherNoeudsAvecAncetres();
		System.out.println("-----------");
		t4bis.afficherNoeudsAvecAncetres();
	}
}

