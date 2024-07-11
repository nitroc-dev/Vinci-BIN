import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random ;

public class Ens1Test extends AbstractTest {
	private Field fieldTab;
	private Field fieldCard;

	
	public Ens1Test(){
		super(Ens1.class);
		Field[] champs = getCls().getDeclaredFields();
		if (champs.length > 2) {
			System.out.println("Il est inutile de d�clarer des champs suppl�mentaire dans Ens1.");
			System.exit(0);
		}
		for (Field f : champs){
			if (f.getType() == boolean[].class){
				fieldTab = f;
			}
			else if (f.getType() == int.class){
				fieldCard = f;
			}
			else {
				System.out.println("Que repr�sente le champ " + f.getName() + " de type " + f.getType().getName() + " ?");
				System.exit(0);
			}
		}
		if (fieldTab == null||fieldCard==null) {
			System.out.println("Vous avez supprim� un attribut de la classe Ens1");
			System.exit(0);
		}
		fieldTab.setAccessible(true);
		fieldCard.setAccessible(true);
	}
	
	@Override
	public boolean testEstVide() throws IllegalArgumentException,
			IllegalAccessException {
		boolean testOK = super.testEstVide();
		EnsembleInterface ens = creerEnsVide();
		if (!ens.estVide()){
			testOK = false;
			System.out.println("On a cr�� un ensemble vide et votre m�thode dit qu'il ne l'est pas !");
		}
		return testOK;
	}
	
	@Override
	public boolean testUnElement() throws IllegalArgumentException,MathException,
			IllegalAccessException {
		boolean testOK = super.testUnElement();
		EnsembleInterface ens = creerEnsVide();
		try {
			Elt elt = ens.unElement() ;
			testOK = false;
			System.out.println("L'ensemble est vide, votre m�thode aurait d� renvoyer une exception");
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
				ens = creerEnsemble(tabElt);
				Elt elt =  ens.unElement();
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
	
	@Override
	public boolean testCardinal() throws IllegalArgumentException,
			IllegalAccessException {
		boolean testOK = super.testCardinal();
		EnsembleInterface ens = creerEnsVide();
		if (ens.cardinal()!= 0){
			testOK = false;
			System.out.println("On a cr�� un ensemble vide.");
			System.out.println("Votre m�thode cardinal dit qu'il contient " + ens.cardinal() + " �l�ment(s).");
		}
		return testOK;
	}
	
	@Override
	public void visualiserToString() throws IllegalArgumentException,
			IllegalAccessException {
		super.visualiserToString();
		EnsembleInterface ens = creerEnsVide();
		System.out.println("Ensemble � afficher : ");
		System.out.println("{}");
		System.out.println("Cha�ne de caract�res renvoy�e par votre toString : ");
		System.out.println(ens);
	}

	private EnsembleInterface creerEnsVide() throws IllegalArgumentException, IllegalAccessException{
		EnsembleInterface ens = creerEnsemble(new Elt[0]);
		boolean[] tabB = (boolean[]) fieldTab.get(ens);
		tabB[0] = true;
		return ens;
	}
	
	@Override
	protected HashSet<Elt> toSet() throws IllegalArgumentException,
			IllegalAccessException {
		HashSet<Elt> elem = new HashSet<Elt>();
		boolean[] tab = (boolean[]) fieldTab.get(getEnsemble());
		for (int i = 1; i <= Elt.MAXELT.val();i++){
			if (tab[i])  elem.add(new Elt(i));
		}
		return elem;
	}
	
	@Override
	protected int card() throws IllegalArgumentException, IllegalAccessException {
		return fieldCard.getInt(getEnsemble());
	}

	private boolean[] initTab(Elt... elts){
		boolean[] tab = new boolean[Elt.MAXELT.val()+1];
		for (Elt e : elts){
			tab[e.val()] = true;
		}
		return tab;
	}
	
	@Override
	protected EnsembleInterface creerEnsemble(Elt... elts) throws IllegalArgumentException, IllegalAccessException {
		HashSet<Elt> ens = new HashSet<Elt>();
		for (Elt elt : elts) ens.add(elt);
		EnsembleInterface ensemble = new Ens1();
		boolean[] tabB = initTab(elts);
		fieldTab.set(ensemble, tabB);
		fieldCard.setInt(ensemble, ens.size());
		return ensemble;
	}
	

	
	protected boolean verifInit() throws IllegalAccessException{
		boolean[] tabTrouve = (boolean[]) fieldTab.get(getEnsemble());
		if (tabTrouve == null){
			System.out.println("Le tableau de booleens n'a pas �t� initialis� !");
			return false;
		}
		else if (tabTrouve.length!= Elt.MAXELT.val()+1){
			System.out.println("Le tableau de booleens n'a pas la bonne dimension !");
			return false;
		} 
		return true;
	}
	
}
