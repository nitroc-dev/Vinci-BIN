import java.util.HashMap;
import java.util.HashSet;


public class ExpressionArithmetique extends ArbreDeCaracteres { 
	
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
	 * calcule le nombre d'operations correspondant au type d'operateur passe en parametre que contient l'expression arithmetique
	 * Par ex : exp1 : + --> 1
	 *                 / --> 1
	 *                 ...
	 *          exp3 : + --> 4 
	 * @param operateur l'operateur verifie
	 * @return le nombre d'operations
	 * @throws IllegalArgumentException si le caractere passe en parametre n'est pas un operateur (+,-,*,/)
	 */
	public int nombreOperations(char operateur)  {
		if (operateur != '+' && operateur != '-' && operateur != '*' && operateur != '/') {
			throw new IllegalArgumentException("Le caractere n'est pas un operateur");
		}
		if (this.racine == null) {
			return 0;
		}
		return nombreOperations(this.racine, operateur);
	}

	private int nombreOperations(NoeudCaractere n, char operateur) {
		if (n == null) {
			return 0;
		}
		if (n.caractere == operateur) {
			return 1 + nombreOperations(n.gauche, operateur) + nombreOperations(n.droit, operateur);
		}
		return nombreOperations(n.gauche, operateur) + nombreOperations(n.droit, operateur);
	}

	
	/**
	 * verifie si l'arbre ne contient que des additions
	 * Par ex l'exp3 ne contient que des +
	 * @return true si l'expression arithmetique contient uniquement des additions, false sinon
	 */
	public boolean uniquementDesAdditions(){
		if (this.racine == null) {
			return true;
		}
		return uniquementDesAdditions(this.racine);
	}

	private boolean uniquementDesAdditions(NoeudCaractere n) {
		if (n.caractere == '+' || n.caractere == '-' || n.caractere == '*' || n.caractere == '/') {
			if (n.caractere == '+') {
				return uniquementDesAdditions(n.gauche) && uniquementDesAdditions(n.droit);
			}
			return false;
		}
		return true;
	}
	
	/**
	 * calcule le nombre d'entiers differents contenus dans l'expression arithmetique
	 * Par ex : exp2 contient 3 entiers differents : 1, 4 et 8
	 * @return le nombre d'entiers differents
	 */
	public int nombreEntiersDifferents() {
		if (this.racine == null) {
			return 0;
		}
		HashSet<Character> ensemble = new HashSet<>();
		nombreEntiersDifferents(racine, ensemble);
		return ensemble.size();
	}

	private int nombreEntiersDifferents(NoeudCaractere noeud, HashSet<Character> ensemble) {
		if (noeud == null) {
			return 0;
		}
		if (noeud.caractere != '+' && noeud.caractere != '-' && noeud.caractere != '*' && noeud.caractere != '/') {
			ensemble.add(noeud.caractere);
		}
		return nombreEntiersDifferents(noeud.gauche, ensemble) + nombreEntiersDifferents(noeud.droit, ensemble);
	}

} 

