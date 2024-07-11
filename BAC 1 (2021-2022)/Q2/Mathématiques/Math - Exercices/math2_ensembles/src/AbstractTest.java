import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ArrayList ;
import java.util.Random;

public abstract class AbstractTest {
	protected static final Elt[] ELTS = { new Elt(5), new Elt(14), new Elt(9),
			new Elt(25), new Elt(17), Elt.MAXELT };
	private static final Elt[] UNIVERS = new Elt[Elt.MAXELT.val()];
	static {
		for (int i = 0; i < Elt.MAXELT.val();i++){
			UNIVERS[i] = new Elt(i+1);
		}
	}
	private Class cls;
	private EnsembleInterface ensemble;

	public AbstractTest(Class cls) {
		this.cls = cls;
	}

	public boolean testConstructeur() throws IllegalArgumentException,
			IllegalAccessException, InstantiationException {
		boolean testOK = true;
		ensemble = (EnsembleInterface) cls.newInstance();
		if (!verifInit())
			return false;
		HashSet<Elt> contenu = new HashSet<Elt>();
		int card = card();
		HashSet<Elt> elemEns = toSet();
		if (card != 0) {
			testOK = false;
			System.out.println("Le cardinal a �t� mal initialis�.");
		} else if (!contenu.equals(elemEns)) {
			testOK = false;
			System.out
					.println("On a cr�� l'ensemble vide. Il ne devrait pas contenir d'�l�ments.");
			System.out.println("Votre ensemble contient les �lements : "
					+ enChaine(elemEns));
		}
		return testOK;

	}

	public boolean testEstVide() throws IllegalArgumentException,
			IllegalAccessException {
		boolean testOK = true;
		ensemble = creerEnsemble(new Elt[0]);
		if (!ensemble.estVide()) {
			testOK = false;
			System.out
					.println("L'ensemble cr�� est vide. Votre m�thode dit qu'il ne l'est pas.");
		}
		ensemble = creerEnsemble(ELTS);
		if (ensemble.estVide()) {
			testOK = false;
			System.out.println("Ensemble cr�� : " + enChaine(ELTS));
			System.out.println("Votre m�thode dit qu'il est vide.");
		}
		return testOK;
	}

	
	
	public boolean testUnElement() throws IllegalArgumentException,MathException,
	IllegalAccessException {
		boolean testOK = true;
		ensemble = creerEnsemble();
		try {
			Elt elt = ensemble.unElement() ;
			testOK = false;
			System.out.println("Votre m�thode renvoie un �l�ment alors que l'ensemble est vide");
		} catch (MathException e) {
			
		} catch (Exception e) {
			System.out.println("Votre m�thode aurait du lancer une MathException mais a lanc� une "+e.getClass());
			testOK = false ;
		} 
		
		try {
			for (int i=0 ; i<5 ; i++) {
				ArrayList<Elt> listElt = new ArrayList<Elt>() ;
				Random rand = new Random() ;
				int j=0 ;
				while (j<6) {
					Elt e = new Elt(rand.nextInt(Elt.MAXELT.val())+1);
					if (!listElt.contains(e)) {
						listElt.add(e) ;
						j++;
					}
				}
				Elt[] tabElt = listElt.toArray(new Elt[1]);
				ensemble = creerEnsemble(tabElt);
				Elt elt =  ensemble.unElement();
				if (elt==null) {
					testOK = false;
					System.out.println("Votre m�thode ne trouve pas d'�l�ment alors que l'ensemble n'est pas vide");
					System.out.println("Ensemble cr�� : " + enChaine(tabElt));
				} else if (!(Arrays.asList(tabElt).contains(elt))) {
					testOK = false ;
					System.out.println("Votre m�thode a renvoy� un �l�ment qui n'est pas dans l'ensemble") ;
					System.out.println("Ensemble cr�� : " + enChaine(tabElt));
					System.out.println("Elt renvoy� : "+elt) ;
				}
			}
		} catch(Exception e) {
			System.out.println("Votre m�thode a lanc� une "+e.getClass()+" alors que l'ensemble n'est pas vide ") ;
			testOK = false ;
		}
		return testOK;
	}
	
	public boolean testContient() throws IllegalArgumentException,
			IllegalAccessException {
		boolean testOK = true;
		ensemble = creerEnsemble(new Elt[0]);
		Elt e = new Elt(36);
		if (ensemble.contient(e)) {
			testOK = false;
			System.out.println("L'ensemble cr�� est vide.");
			System.out.println("Votre m�thode contient dit que l'�l�ment " + e
					+ " est dans l'ensemble.");
		}
		ensemble = creerEnsemble(ELTS);
		if (ensemble.contient(e)) {
			testOK = false;
			System.out.println("Ensemble cr�� : " + enChaine(ELTS));
			System.out.println("Votre m�thode contient dit que l'�l�ment " + e
					+ " est dans l'ensemble.");
		}

		for (Elt elt : ELTS) {
			if (!ensemble.contient(new Elt(elt))) {
				testOK = false;
				System.out.println("Ensemble cr�� : " + enChaine(ELTS));
				System.out.println("Votre m�thode contient dit que l'�l�ment "
						+ elt + " n'est pas dans l'ensemble.");
				break;
			}
		}
		try {
			ensemble.contient(null);
			testOK = false;
			System.out
					.println("On a appel� la m�thode contient avec null comme param�tre --> Il fallait lancer une IllegalArgumentException.");
		} catch (IllegalArgumentException iae) {

		}
		return testOK;
	}

	public boolean testAjouter() throws IllegalArgumentException,
			IllegalAccessException {
		boolean testOK = true;
		ensemble = creerEnsemble(ELTS);
		Elt elt = null;
		try {
			ensemble.ajouter(elt);
			testOK = false;
			System.out
					.println("On a appel� la m�thode ajouter avec null comme param�tre --> Il fallait lancer une IllegalArgumentException.");
		} catch (IllegalArgumentException iae) {

		}
		HashSet<Elt> contenu = new HashSet<Elt>();
		for (Elt e : ELTS)
			contenu.add(e);
		HashSet<Elt> contenuAvant = new HashSet<>(contenu);
		elt = new Elt(ELTS[1]);
		ensemble.ajouter(elt);
		int nouveauCard = card();
		if (nouveauCard != contenu.size()) {
			testOK = false;
			System.out.println("On a ajout� l'�l�ment " + elt
					+ " � l'ensemble " + enChaine(contenuAvant) + ".");
			System.out.println("Apr�s l'ajout, le cardinal doit valoir "
					+ contenu.size() + ".");
			System.out.println("Le cardinal de votre ensemble est � "
					+ nouveauCard + ".");
		}
		if (testOK) {
			HashSet<Elt> elemEns = toSet();
			if (!contenu.equals(elemEns)) {
				testOK = false;
				System.out.println("On a ajout� l'�l�ment " + elt
						+ " � l'ensemble " + enChaine(ELTS) + ".");
				System.out
						.println("Elements qui devraient �tre dans l'ensemble : "
								+ enChaine(contenu));
				System.out
						.println("Les �l�ments trouv�s dans votre ensemble sont : "
								+ enChaine(elemEns));
			}
		}
		elt = new Elt(36);
		ensemble.ajouter(elt);
		contenuAvant = new HashSet<>(contenu);
		contenu.add(elt);
		nouveauCard = card();
		if (nouveauCard != contenu.size()) {
			testOK = false;
			System.out.println("On a ajout� l'�l�ment " + elt
					+ " � l'ensemble " + enChaine(contenuAvant) + ".");
			System.out.println("Apr�s l'ajout, le cardinal doit valoir "
					+ contenu.size() + ".");
			System.out.println("Le cardinal de votre ensemble est � "
					+ nouveauCard + ".");
		}
		if (testOK) {
			HashSet<Elt> elemEns = toSet();
			if (!contenu.equals(elemEns)) {
				testOK = false;
				System.out.println("On a ajout� l'�l�ment " + elt
						+ " � l'ensemble " + enChaine(ELTS) + ".");
				System.out
						.println("Elements qui devraient �tre dans l'ensemble : "
								+ enChaine(contenu));
				System.out
						.println("Les �l�ments trouv�s dans votre ensemble sont : "
								+ enChaine(elemEns));
			}
		}
		return testOK;
	}

	public boolean testEnlever() throws IllegalArgumentException,
			IllegalAccessException {
		boolean testOK = true;
		ensemble = creerEnsemble(ELTS);
		Elt elt = null;
		try {
			ensemble.enlever(elt);
			testOK = false;
			System.out
					.println("On a appel� la m�thode enlever avec null comme param�tre --> Il fallait lancer une IllegalArgumentException.");
		} catch (IllegalArgumentException iae) {

		}
		HashSet<Elt> contenu = new HashSet<Elt>();
		for (Elt e : ELTS)
			contenu.add(e);
		HashSet<Elt> contenuAvant = new HashSet<>(contenu);
		elt = new Elt(36);
		ensemble.enlever(elt);
		contenu.remove(elt);
		int nouveauCard = card();
		if (nouveauCard != contenu.size()) {
			testOK = false;
			System.out.println("On a enlev� l'�l�ment " + elt
					+ " de l'ensemble " + enChaine(contenuAvant));
			System.out.println("Apr�s la suppression, le cardinal doit valoir "
					+ contenu.size() + ".");
			System.out.println("Le cardinal de votre ensemble est � "
					+ nouveauCard + ".");
		}
		if (testOK) {
			HashSet<Elt> elemEns = toSet();
			if (!contenu.equals(contenuAvant)) {
				testOK = false;
				System.out.println("On a enlev� l'�l�ment " + elt
						+ " de l'ensemble " + enChaine(ELTS) + ".");
				System.out
						.println("Elements qui devraient �tre dans l'ensemble : "
								+ enChaine());
				System.out
						.println("Les �l�ments trouv�s dans votre ensemble sont : "
								+ enChaine(elemEns));
			}
		}

		elt = new Elt(ELTS[2]);
		ensemble.enlever(elt);
		contenuAvant = new HashSet<>(contenu);
		contenu.remove(elt);
		nouveauCard = card();
		if (nouveauCard != contenu.size()) {
			testOK = false;
			System.out.println("On a enlev� l'�l�ment " + elt
					+ " de l'ensemble " + enChaine(contenuAvant));
			System.out.println("Apr�s la suppression, le cardinal doit valoir "
					+ contenu.size() + ".");
			System.out.println("Le cardinal de votre ensemble est � "
					+ nouveauCard + ".");
		}
		if (testOK) {
			HashSet<Elt> elemEns = toSet();
			if (!contenu.equals(elemEns)) {
				testOK = false;
				System.out.println("On a enlev� l'�l�ment " + elt
						+ " de l'ensemble " + enChaine(contenuAvant) + ".");
				System.out
						.println("Elements qui devraient �tre dans l'ensemble : "
								+ enChaine(contenu));
				System.out
						.println("Les �l�ments trouv�s dans votre ensemble sont : "
								+ enChaine(elemEns));
			}
		}
		if (testOK) {
			elt = new Elt(ELTS[1]);
			ensemble.enlever(elt);
			contenuAvant = new HashSet<>(contenu);
			contenu.remove(elt);
			nouveauCard = card();
			if (nouveauCard != contenu.size()) {
				testOK = false;
				System.out.println("On a enlev� l'�l�ment " + elt
						+ " de l'ensemble " + enChaine(contenuAvant) + ".");
				System.out
						.println("Apr�s la suppression, le cardinal doit valoir "
								+ contenu.size() + ".");
				System.out.println("Le cardinal de votre ensemble est � "
						+ nouveauCard + ".");
			}
			if (testOK) {
				HashSet<Elt> elemEns = toSet();
				if (!contenu.equals(elemEns)) {
					testOK = false;
					System.out.println("On a enlev� l'�l�ment " + elt
							+ " de l'ensemble " + enChaine(contenuAvant) + ".");
					System.out
							.println("Elements qui devraient �tre dans l'ensemble : "
									+ enChaine(contenu));
					System.out
							.println("Les �l�ments trouv�s dans votre ensemble sont : "
									+ enChaine(elemEns));
				}
			}
		}
		if (testOK){
			for (Elt e : ELTS){
				ensemble.enlever(e);
				contenuAvant = new HashSet<>(contenu);
				contenu.remove(e);
				nouveauCard = card();
				if (nouveauCard != contenu.size()) {
					testOK = false;
					System.out.println("On a enlev� l'�l�ment " + e
							+ " de l'ensemble " + enChaine(contenuAvant));
					System.out.println("Apr�s la suppression, le cardinal doit valoir "
							+ contenu.size() + ".");
					System.out.println("Le cardinal de votre ensemble est � "
							+ nouveauCard + ".");
					break;
				}
				if (testOK) {
					HashSet<Elt> elemEns = toSet();
					if (!contenu.equals(elemEns)) {
						testOK = false;
						System.out.println("On a enlev� l'�l�ment " + e
								+ " de l'ensemble " + enChaine(contenuAvant) + ".");
						System.out
								.println("Elements qui devraient �tre dans l'ensemble : "
										+ enChaine(contenu));
						System.out
								.println("Les �l�ments trouv�s dans votre ensemble sont : "
										+ enChaine(elemEns));
						break;
					}
				}
			}
		}
		if (testOK){
			ensemble = creerEnsemble(UNIVERS);
			contenu = new HashSet<>(Arrays.asList(UNIVERS));
			contenuAvant = new HashSet<>(contenu);
			ensemble.enlever(elt);
			contenu.remove(elt);
			nouveauCard = card();
			if (nouveauCard != contenu.size()) {
				testOK = false;
				System.out.println("On a enlev� l'�l�ment " + elt
						+ " de l'ensemble " + enChaine(contenuAvant));
				System.out.println("Apr�s la suppression, le cardinal doit valoir "
						+ contenu.size() + ".");
				System.out.println("Le cardinal de votre ensemble est � "
						+ nouveauCard + ".");
			}
			if (testOK) {
				HashSet<Elt> elemEns = toSet();
				if (!contenu.equals(elemEns)) {
					testOK = false;
					System.out.println("On a enlev� l'�l�ment " + elt
							+ " de l'ensemble " + enChaine(contenuAvant) + ".");
					System.out
							.println("Elements qui devraient �tre dans l'ensemble : "
									+ enChaine(contenu));
					System.out
							.println("Les �l�ments trouv�s dans votre ensemble sont : "
									+ enChaine(elemEns));
				}
			}
		}
		return testOK;
	}

	public boolean testCardinal() throws IllegalArgumentException,
			IllegalAccessException {
		boolean testOK = true;
		ensemble = creerEnsemble(new Elt[0]);
		if (ensemble.cardinal() != 0) {
			testOK = false;
			System.out.println("L'ensemble cr�� est vide.");
			System.out.println("Votre m�thode cardinal dit qu'il contient "
					+ ensemble.cardinal() + " �l�ment(s).");
		}
		ensemble = creerEnsemble(ELTS);
		if (ensemble.cardinal() != ELTS.length) {
			testOK = false;
			System.out.println("Ensemble cr�� : " + enChaine(ELTS));
			System.out.println("Votre m�thode cardinal dit qu'il contient "
					+ ensemble.cardinal() + " �l�ment(s).");
		}
		return testOK;
	}

	public boolean testComplementer() throws IllegalArgumentException,
			IllegalAccessException {
		boolean testOK = true;
		HashSet<Elt> complem = new HashSet<>();
		for (int i = 1; i <= Elt.MAXELT.val(); i++)
			complem.add(new Elt(i));
		ensemble = creerEnsemble(new Elt[0]);
		ensemble.complementer();
		int nouveauCard = card();
		if (nouveauCard != complem.size()) {
			testOK = false;
			System.out
					.println("On a pris le compl�mentaire de l'ensemble vide.");
			System.out
					.println("Apr�s avoir fait le compl�mentaire, le cardinal devrait valoir "
							+ complem.size() + ".");
			System.out.println("Le cardinal de votre ensemble est � "
					+ nouveauCard + ".");
		}
		if (testOK) {
			HashSet<Elt> elemEns = toSet();
			if (!complem.equals(elemEns)) {
				testOK = false;
				System.out
						.println("On a pris le compl�mentaire de l'ensemble vide.");
				System.out
						.println("Elements qui devraient �tre dans l'ensemble : ");
				System.out.println(enChaine(complem));
				System.out
						.println("Les �l�ments trouv�s dans votre ensemble sont : "
								+ enChaine(elemEns));
			}
		}
		ensemble = creerEnsemble(ELTS);
		for (Elt e : ELTS)
			complem.remove(e);
		ensemble.complementer();
		nouveauCard = card();
		if (nouveauCard != complem.size()) {
			testOK = false;
			System.out.println("On a pris le compl�mentaire de l'ensemble "
					+ enChaine(ELTS) + ".");
			System.out
					.println("Apr�s avoir fait le compl�mentaire, le cardinal devrait valoir "
							+ complem.size() + ".");
			System.out.println("Le cardinal de votre ensemble est � "
					+ nouveauCard + ".");
		}
		if (testOK) {
			HashSet<Elt> elemEns = toSet();
			if (!complem.equals(elemEns)) {
				testOK = false;
				System.out.println("On a pris le compl�mentaire de l'ensemble "
						+ enChaine(ELTS) + ".");
				System.out
						.println("Elements qui devraient �tre dans l'ensemble : ");
				System.out.println(enChaine(complem));
				System.out
						.println("Les �l�ments trouv�s dans votre ensemble sont : "
								+ enChaine(elemEns));
			}
		}
		return testOK;
	}

	public void visualiserToString() throws IllegalArgumentException,
			IllegalAccessException {
		ensemble = creerEnsemble(new Elt[0]);
		System.out.println("Ensemble � afficher : ");
		System.out.println("{}");
		System.out
				.println("Cha�ne de caract�res renvoy�e par votre toString : ");
		System.out.println(ensemble);
		ensemble = creerEnsemble(ELTS);
		System.out
				.println("Ensemble � afficher (l'ordre dans lequel se trouvent les �l�ments n'a pas d'importance): ");
		System.out.println(enChaine(ELTS));
		System.out
				.println("Cha�ne de caract�res renvoy�e par votre toString : ");
		System.out.println(ensemble);
	}

	public boolean testConstructeurRecopie() throws IllegalArgumentException,
			IllegalAccessException, InstantiationException,
			ClassNotFoundException, InvocationTargetException {
		boolean testOK = true;
		try {
			Constructor constr = cls.getConstructor(EnsembleInterface.class);
			try {
				constr.newInstance((EnsembleInterface) null);
				testOK = false;
				System.out
						.println("Appel du constructeur avec un param�tre null --> Il fallait lancer une IllegalArgumentException !");
			} catch (InvocationTargetException exc) {
				if (!(exc.getCause() instanceof IllegalArgumentException)) {
					throw exc;
				}
			}
			HashSet<Elt> contenu = new HashSet<Elt>();
			for (Elt e : ELTS)
				contenu.add(e);
			for (int i = 1; i <= TestEnsemble.NB_IMPLEMENTATIONS; i++) {
				Class clT = Class.forName("Ens" + i + "Test");
				EnsembleInterface aCopier = ((AbstractTest) clT.newInstance())
						.creerEnsemble(ELTS);
				ensemble = (EnsembleInterface) constr.newInstance(aCopier);
				if (!verifInit())
					return false;
				if (card() != ELTS.length) {
					testOK = false;
					System.out
							.println("Le cardinal n'a pas �t� bien initialis� !");
				}
				HashSet<Elt> trouve = toSet();
				if (!trouve.equals(contenu)) {
					testOK = false;
					System.out.println("Elements de l'ensemble recopi� : "
							+ enChaine(contenu));
					System.out.println("Elements dans votre copie : "
							+ enChaine(trouve));
				}
				if (!testOK)
					return false;
			}
			return testOK;

		} catch (NoSuchMethodException e) {
			System.out
					.println("Vous n'avez pas �crit le constructeur ayant comme param�tre un objet de type EnsembleInterface ou celui-ci n'est pas public !");
			return false;
		} 

	}

	public boolean testConstructeurSingleton() throws IllegalArgumentException,
			IllegalAccessException, InstantiationException,
			InvocationTargetException {
		boolean testOK = true;
		try {
			Constructor constr = cls.getConstructor(Elt.class);
			try {
				constr.newInstance((Elt) null);
				testOK = false;
				System.out
						.println("Appel du constructeur avec un param�tre null --> Il fallait lancer une IllegalArgumentException !");
			} catch (InvocationTargetException exc) {
				if (!(exc.getCause() instanceof IllegalArgumentException)) {
					throw exc;
				}
			}
			HashSet<Elt> contenu = new HashSet<Elt>();
			Elt e = new Elt(12);
			contenu.add(e);
			ensemble = (EnsembleInterface) constr.newInstance(e);
			if (!verifInit())
				return false;
			if (ensemble.cardinal() != 1) {
				testOK = false;
				System.out.println("Le cardinal n'a pas �t� bien initialis� !");
			}
			HashSet<Elt> trouve = toSet();
			if (!trouve.equals(contenu)) {
				testOK = false;
				System.out.println("Element de l'ensemble cr�� : "
						+ enChaine(contenu));
				System.out.println("Elements dans votre copie : "
						+ enChaine(trouve));
			}
			if (!testOK)
				return false;

			return true;

		} catch (NoSuchMethodException exc) {
			System.out
					.println("Vous n'avez pas �crit le constructeur ayant comme param�tre un objet de type Elt ou celui-ci n'est pas public !");
			return false;
		} 
	}

	public Class getCls() {
		return cls;
	}

	protected static String enChaine(Elt... elts) {
		if (elts.length == 0)
			return "{}";
		String s = "{" + elts[0];
		for (int i = 1; i < elts.length; i++) {
			s += "," + elts[i];
		}
		return s + "}";

	}

	private static String enChaine(HashSet<Elt> set) {
		String result = "{";
		if (set.isEmpty())
			return result + "}";
		Iterator<Elt> it = set.iterator();
		result += it.next();
		while (it.hasNext()) {
			result += "," + it.next();
		}
		return result + "}";
	}

	protected EnsembleInterface getEnsemble() {
		return ensemble;
	}

	protected abstract EnsembleInterface creerEnsemble(Elt... elts)
			throws IllegalArgumentException, IllegalAccessException;

	protected abstract HashSet<Elt> toSet() throws IllegalArgumentException,
			IllegalAccessException;

	protected abstract int card() throws IllegalArgumentException,
			IllegalAccessException;

	protected abstract boolean verifInit() throws IllegalAccessException;

}
