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
	 * calcule le nombre d'additions que contient l'expression arithmetique
	 * Par ex : exp1 : 1 addition
	 *          exp3 : 4 additions
	 * @return le nombre d'additions
	 */
	public int nombreAdditions()  {
		return nombreAdditions(racine);
	}

	private int nombreAdditions(NoeudCaractere n) {
		if (n == null) return 0;
		if (n.caractere == '+') {
			return 1 + nombreAdditions(n.gauche) + nombreAdditions(n.droit);
		}
		return nombreAdditions(n.gauche) + nombreAdditions(n.droit);
	}

	/**
	 * verifie si l'arbre ne contient que des operateurs du type passe en parametre
	 * Par ex l'exp2 ne contient que des +
	 * @param operateur l'operateur verifie
	 * @return true si l'expression arithmetique contient uniquement des operateurs du type passe en parametre, false sinon
	 * @throws IllegalArgumentException si le caractere passe en parametre n'est pas un operateur
	 */
	public boolean uniquementDes(char operateur){
		if (operateur != '+' && operateur != '-' && operateur != '*' && operateur != '/') {
			throw new IllegalArgumentException("Le caractere passe en parametre n'est pas un operateur");
		}
		return uniquementDes(racine, operateur);
	}

	private boolean uniquementDes(NoeudCaractere n, char operateur) {
		if (n == null) return true;
		if (n.caractere == '+' || n.caractere == '-' || n.caractere == '*' || n.caractere == '/') {
			if (n.caractere == operateur) {
				return uniquementDes(n.gauche, operateur) && uniquementDes(n.droit, operateur);
			} else {
				return false;
			}
		}
		return uniquementDes(n.gauche, operateur) && uniquementDes(n.droit, operateur);
	}

	/**
	 * calcule le nombre d'entiers differents contenus dans l'expression arithmetique
	 * Par ex : exp2 contient 3 entiers differents : 1, 4 et 8
	 * @return le nombre d'entiers differents
	 */
	public int nombreEntiersDifferents(){
		HashSet<Integer> set = new HashSet<Integer>();
		nombreEntiersDifferents(racine, set);
		return set.size();
	}

	private int nombreEntiersDifferents(NoeudCaractere n, HashSet<Integer> set) {
		if (n == null) return 0;
		if (n.caractere == '+' || n.caractere == '-' || n.caractere == '*' || n.caractere == '/') {
			int a = nombreEntiersDifferents(n.gauche, set);
			int b = nombreEntiersDifferents(n.droit, set);
			return a + b;
		} else {
			int nombre = Integer.parseInt(n.caractere+"");
			if (set.contains(nombre)) return 0;
			set.add(nombre);
			return 1;
		}
	}

} 

