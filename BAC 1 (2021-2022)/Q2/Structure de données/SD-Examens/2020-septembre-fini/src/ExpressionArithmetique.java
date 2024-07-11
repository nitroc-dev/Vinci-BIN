import java.util.HashSet;

public class ExpressionArithmetique extends ArbreDeCaracteres { 
	
	public ExpressionArithmetique () {
		super();
	}
	
	/**
	 * Cree une expression arithmetique a partir d'un arbre de caracteres
	 * @param a
	 */
	public ExpressionArithmetique (ArbreDeCaracteres a) {
		super(a);
	}

	public ExpressionArithmetique (char c) {
		super(c);
	}
	
	public ExpressionArithmetique (char c, ArbreDeCaracteres ag, ArbreDeCaracteres ad) {
		super(c, ag, ad);
	}
	
	/**
	 * calcule le nombre d'operateurs moins presents dans l'expression arithmetique
	 * si le parametre est 1, c'est le nombre de moins unaire qui sera renvoye
	 * si le parametre est 2, c'est le nombre de moins binaire qui sera renvoye
	 * @param i permet de distinguer l'operateur unaire de l'operateur binaire (1 ou 2)
	 * @return le nombre calcule
	 * @throws IllegalArgumentException si le parametre est invalide
	 */
	public int nombreMoins(int i) {
		if (i != 1 && i != 2) throw new IllegalArgumentException("Le parametre doit etre 1 ou 2");
		if (i == 1) return nombreMoinsUnaire(this.racine);
		return nombreMoinsBinaire(this.racine);
	}

	private int nombreMoinsUnaire(NoeudCaractere n) {
		if (n == null) return 0;
		if (n.caractere == '-') {
			if (n.gauche == null || n.droit == null) return 1 + nombreMoinsUnaire(n.gauche) + nombreMoinsUnaire(n.droit);
		}
		return nombreMoinsUnaire(n.gauche) + nombreMoinsUnaire(n.droit);
	}

	private int nombreMoinsBinaire(NoeudCaractere n) {
		if (n == null) return 0;
		if (n.caractere == '-') {
			if (n.gauche != null && n.droit != null) {
				if (isNumber(n.gauche.caractere) && isNumber(n.droit.caractere)) {
					return 1 + nombreMoinsBinaire(n.gauche) + nombreMoinsBinaire(n.droit);
				}
			}
		}
		return nombreMoinsBinaire(n.gauche) + nombreMoinsBinaire(n.droit);
	}

	private boolean isNumber(char caractere) {
		return caractere >= '0' && caractere <= '9';
	}

	/**
	 * verifie si tous les entiers contenus dans l'expression arithmetiques sont differents
	 * @return true si tous les entiers sont differents, false sinon
	 */
	public boolean entiersTousDifferents() {
		if (this.racine == null) return true;
		HashSet<Character> entiers = new HashSet<>();
		return entiersTousDifferents(this.racine, entiers);
	}

	private boolean entiersTousDifferents(NoeudCaractere n, HashSet<Character> entiers) {
		if (n.caractere >= '0' && n.caractere <= '9') {
			if (entiers.contains(n.caractere)) return false;
			entiers.add(n.caractere);
		}
		if (n.gauche != null) {
			if (!entiersTousDifferents(n.gauche, entiers)) return false;
		}
		if (n.droit != null) {
			if (!entiersTousDifferents(n.droit, entiers)) return false;
		}
		return true;
	}
} 

