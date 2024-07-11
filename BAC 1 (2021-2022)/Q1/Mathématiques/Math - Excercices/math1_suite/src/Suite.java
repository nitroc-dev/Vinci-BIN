public class Suite extends SuiteDeBase {

	private static final int MAX = Elt.MAXELT.val();

	public Suite() {
		super();
	}

	public Suite(SuiteDeBase s) {
		super(s);
	}

	public Suite(String st) {
		super(st);
	}

	public Suite(Elt t, Suite c) {
		super(t, c);
	}

	/**
	 *  Construit la concatenation des Suite s1 et s2
	 * @param s1
	 * @param s2
	 */
	public Suite(Suite s1, Suite s2) {
		super(s2);
		this.ajouter(s1);
	}

	public Suite corps() {
		return (Suite) super.corps();
	}

	@Override
	public int hashCode(){
		int prime = 31;
		int result = 1;
		for (Elt e : this){
			result = result *prime + e.hashCode();
		}
		return result;
	}

	/** Renvoie la longueur de la Suite courante */
	public int longueur() {
		if (estVide()) {
			return 0;
		} else {
			return this.corps().longueur() + 1;
		}
	}

	/** renvoie TRUE si e a au moins une occurrence dans la Suite courante
	 * @throws IllegalArgumentException en cas de paramÃštre invalide*/
	public boolean contient(Elt e){
		if (e == null) throw new IllegalArgumentException();
		if (estVide()) return false;
		return this.tete().equals(e) || this.corps().contient(e);
	}

	/** renvoie le nombre d'occurrences de e dans la Suite courante
	 * @throws IllegalArgumentException en cas de paramÃštre invalide*/
	public int nombreOccur(Elt e){
		if (e == null) {
			throw new IllegalArgumentException();
		}
		if (estVide()) {
			return 0;
		}
		if (tete().equals(e)) {
			return corps().nombreOccur(e) + 1;
		} else {
			return corps().nombreOccur(e);
		}
	}

	/** renvoie la position de la premiÃšre occurrence de e dans la Suite courante ;
	 * renvoie 0 si e n'a pas d'occurrence dans la Suite courante
	 * @throws IllegalArgumentException en cas de paramÃštre invalide*/
	public int position(Elt e) {
		if (e == null) {
			throw new IllegalArgumentException();
		}
		return positionBis(e);
	}

	private int positionBis(Elt e) {
		if (!contient(e)) {
			return 0;
		}
		if (tete().equals(e)) {
			return 1;
		} else {
			return corps().position(e) + 1;
		}
	}

	/**
	 * renvoie le i-Ãšme Ã©lÃ©ment de la Suite courante s'il existe ;
	 * @throws IllegalArgumentException s'il n'exite pas de i-Ãšme Ã©lÃ©ment
	 */
	public Elt iEme(int i) {
		if (i <= 0) {
			throw new IllegalArgumentException();
		}
		return iEmeBis(i);
	}

	private Elt iEmeBis(int i) {
		if (estVide()) {
			throw new IllegalArgumentException();
		}
		if (i == 1) {
			return tete();
		} else {
			return corps().iEmeBis(i - 1);
		}
	}

	/**
	 * renvoie le dernier élément de la Suite courante si elle est non vide,
	 *
	 * @throws MathException si elle est vide
	 */
	public Elt dernier() {
		if (estVide()) {
			throw new MathException();
		}
		return dernierBis();
	}

	public Elt dernierBis() {
		if (corps().estVide()) {
			return tete();
		} else {
			return corps().dernierBis();
		}
	}


	@Override
	/** renvoie true si la Suite courante est Ã©gale Ã  o*/
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (!o.getClass().equals(this.getClass()))
			return false ;
		Suite suite = (Suite) o;
		return equalsBis(suite);
	}

	private boolean equalsBis(Suite suite) {
		if (this.estVide() != suite.estVide()) {
			return false;
		}
		if (this.estVide() && suite.estVide()) {
			return true;
		} else if (this.tete().equals(suite.tete())) {
			return this.corps().equalsBis(suite.corps());
		} else {
			return false;
		}
	}

	/** renvoie true si la Suite courante est un prÃ©fixe de s
	 * @throws IllegalArgumentException en cas de paramÃštre invalide*/
	public boolean prefixe(Suite s){
		if (s == null) {
			throw new IllegalArgumentException();
		}
		return prefixeBis(s);
	}

	private boolean prefixeBis(Suite s){
		if (this.estVide()) {
			return true;
		}
		if (s.estVide()) {
			return false;
		}
		if (this.tete().equals(s.tete())) {
			return corps().prefixeBis(s.corps());
		} else {
			return false;
		}
	}

	/** renvoie true si la Suite courante est une sous-suite de s
	 * @throws IllegalArgumentException en cas de paramÃštre invalide*/
	public boolean sousSuite(Suite s){
		if (s == null) {
			throw new IllegalArgumentException();
		}
		return sousSuiteBis(s);
	}

	private boolean sousSuiteBis(Suite s){
		if (this.estVide()) {
			return true;
		}
		if (s.estVide()) {
			return false;
		}
		if (this.tete().equals(s.tete())) {
			return corps().sousSuiteBis(s.corps());
		} else {
			return sousSuiteBis(s.corps());
		}
	}


	/** ajoute s Ã  gauche de la Suite courante
	 * @throws IllegalArgumentException en cas de paramÃštre invalide*/
	public void ajouter(Suite s){
		if (s == null) {
			throw new IllegalArgumentException();
		}
		ajouterBis(s);
	}

	private void ajouterBis(Suite s) {
		if (!s.estVide()) {
			this.ajouterBis(s.corps());
			this.ajouter(s.tete());
		}
	}


	/** ajoute s Ã  l'envers et Ã  gauche de la Suite courante
	 * @throws IllegalArgumentException en cas de paramÃ©tre invalide*/
	public void ajouterALEnvers(Suite s){
		if (s == null) {
			throw new IllegalArgumentException();
		}
		ajouterALEnversBis(s);
	}

	private void ajouterALEnversBis(Suite s) {
		if (!s.estVide()) {
			this.ajouter(s.tete());
			this.ajouterALEnversBis(s.corps());
		}
	}


	/** renvoie la Suite courante inversÃ©e*/
	public Suite inverse(){
		Suite suite = new Suite();
		suite.ajouterALEnvers(this);
		return suite;
	}



	/** renvoie une Suite contenant, une et une seule fois, tous les Ã©lÃ©ments de la Suite courante*/
	public Suite reduite(){
		if (this.estVide()) {
			return new Suite();
		}
		if (corps().contient(tete())) {
			return corps().reduite();
		} else {
			return new Suite(tete(), corps().reduite());
		}
	}

	/**
	 * Renvoie la Suite courante de laquelle on a supprimÃ© tous les Ã©lÃ©ments Ã  partir du k-iÃšme
	 * Si la suite n'a pas k Ã©lÃ©ments, elle renvoie une copie de la suite.
	 *
	 * @throws IllegalArgumentException en cas de paramÃštre invalide
	 */
	public Suite tronquee(int k) {
		if (k <= 0) {
			throw new  IllegalArgumentException();
		}
		return tronqueeBis(k);
	}

	private Suite tronqueeBis(int k) {
		if (this.estVide()) {
			return new Suite();
		}
		if (k == 1) {
			return new Suite();
		} else {
			return new Suite(tete(), corps().tronqueeBis(k - 1));
		}
	}


	/** renvoie une copie de la Suite courante dans laquelle on a supprimÃ© la premiÃšre occurrence (Ã©ventuelle) de x
	 * @throws IllegalArgumentException en cas de paramÃštre invalide*/
	public Suite moinsPremOcc(Elt x){
		if (x == null) {
			throw new IllegalArgumentException();
		}
		return moinsPremOccBis(x);
	}

	private Suite moinsPremOccBis(Elt x) {
		if (this.estVide()) {
			return new Suite();
		}
		if (!tete().equals(x)) {
			return new Suite(tete(), corps().moinsPremOccBis(x));
		}
		return corps();

	}


	/** renvoie une copie de la Suite courante dans laquelle on a supprimÃ© toutes les occurrences de x
	 * @throws IllegalArgumentException en cas de paramÃštre invalide*/
	public Suite moinsToutesOcc(Elt x){
		if (x == null) {
			throw new IllegalArgumentException();
		}
		return moinsToutesOccBis(x);
	}

	private Suite moinsToutesOccBis(Elt x) {
		if (this.estVide()) {
			return new Suite();
		}
		if (this.tete().equals(x)) {
			return new Suite(corps().moinsToutesOccBis(x));// juste corps().moinsToutesOccBis() suffit car cela renvoie une nouvelle suite donc le new Suite(...) ne sert à rien
		}
		return new Suite(tete(), corps().moinsToutesOccBis(x));

	}

	/**
	 * renvoie une Suite constituee de tous les Elt ayant plus d'une occurrence dans la Suite courante
	 */
	public Suite doublons() {// Horriblement inefficace
		if (this.estVide()) {
			return new Suite();
		}
		if (this.nombreOccur(tete()) > 1) {
			return new Suite(tete(), this.moinsToutesOcc(tete()).doublons());
		}
		return new Suite(this.corps().doublons());
	}

	public Suite doublonsToo() {
		if (this.estVide()) {
			return new Suite();
		}
		Suite suiteDoublons = new Suite().doublonsToo();
		if (corps().contient(tete()) && !suiteDoublons.contient(tete())) {
			suiteDoublons.ajouter(tete());
		}
		return suiteDoublons;
	}

	/** renvoie true ssi la Suite courante contient au moins k Elt distincts
	 * @throws IllegalArgumentException en cas de paramÃštre invalide*/
	public boolean auMoinsK(int k){
		if (k < 0) {
			throw new IllegalArgumentException();
		}
		return auMoinsKBis(k);
	}

	private boolean auMoinsKBis(int k) {
		if (k <= 0) {// ComplÃštement incohÃ©rent avec la mÃ©thode public !!!! Il faut seulement mettre if (k==0)
			return true;
		}
		if (this.estVide()) {
			return false;
		}
		return this.moinsToutesOcc(tete()).auMoinsKBis(k - 1);// Correct mais pas trÃšs efficace. Une autre solution est d'utiliser la méthode contient :
		// if (this.corps().contient(this.tete()))
		//    return this.corps().auMoinsKBis(k)
		// else
		//    return this.corps().auMoinsKBis(k-1)
	}


	/** renvoie true ssi tous les element de la Suite sont distincts */
	public boolean tousDistincts(){
		return auMoinsK(this.longueur());
	}

	/** renvoie une copie de la Suite courante dans laquelle toutes les occurrences de x ont Ã©tÃ© remplacÃ©es par y
	 * @throws IllegalArgumentException en cas de paramÃštre invalide*/
	public Suite substitut(Elt x, Elt y){
		if (x == null || y == null) {
			throw new IllegalArgumentException();
		}
		return substitutBis(x, y);
	}

	private Suite substitutBis(Elt x, Elt y) {
		if (this.estVide()) {
			return new Suite();
		}
		if (this.tete().equals(x)) {
			return new Suite(y, corps().substitutBis(x, y));
		}
		return new Suite(tete(), corps().substitutBis(x, y));
	}

	/** renvoie true si la Suite courante est strictement croissante*/
	public boolean estTriee(){
		if (this.estVide() || corps().estVide()) {
			return true;
		}
		if (tete().val() >= corps().tete().val()) {
			return false;
		}
		return corps().estTriee();
	}


} // class Suite
