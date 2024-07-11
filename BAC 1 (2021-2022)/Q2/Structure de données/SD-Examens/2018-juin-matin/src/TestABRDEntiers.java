import java.util.ArrayList;
import java.util.Scanner;


public class TestABRDEntiers {
	
	private final static Scanner scanner = new Scanner(System.in);	
	
	/**
	 * Cette methode verifie qu'un resultat attendu est bien un resultat obtenu.
	 * @param messageErreur message a afficher en cas de probleme
	 * @param attendu la valeur qu'on s'attendait a recevoir
	 * @param recu la valeur qu'on a reçu en realite
	 */
	
	private static void assertEquals(String messageErreur, Object attendu, Object recu) {
		if (attendu==null) {
			if (recu!=null) {
				System.out.println();
				System.out.println(messageErreur+". Attendu="+attendu+" reçu="+recu);
				System.exit(0);
			}
		} else if (!attendu.equals(recu)) {
			System.out.println();
			System.out.println(messageErreur+". Attendu="+attendu+" reçu="+recu);
			System.exit(0);			
		}
	}
	
	public static ABRDEntiers arbreEnonce(){
		
		ABRDEntiers a = new ABRDEntiers();
		a.insere(8);
		a.insere(4);
		a.insere(2);
		a.insere(6);
		a.insere(5);
		a.insere(7);
		a.insere(12);
		a.insere(8);
		a.insere(11);
		return a;
	}
	
	
	public static ABRDEntiers arbreVide(){
		return new ABRDEntiers();
	}
	

	public static void main(String[] args) {
		System.out.println("*****************************************");
		System.out.println("Programme Test pour la classe ABRDEntiers");
		System.out.println("*****************************************");

		int choix = 0;
		do {
			System.out.println();
			System.out.println("1 -> Tester la methode toArrayList()");
			System.out.println("2 -> Tester la methode nombreEntiersEntre()");
			System.out.println();
			System.out.print("Votre choix : ");
			choix = scanner.nextInt();
			switch (choix) {
			case 1:
				testToArrayList();
				break;
			case 2:
				testNombreEntiersEntre();
				break;
			default:
				break;
			}

		} while (choix >= 1 && choix <= 2);
		
		
		System.out.println();
		
		
		System.out.println("test toArrayList()");	
	}

	

	private static void testToArrayList() {
		System.out.print("test1 : arbre enonce");
		ABRDEntiers a = arbreEnonce();
		ArrayList<Integer> arrayListObtenue = a.toArrayList();
		assertEquals("test1 ko", "[2, 4, 5, 6, 7, 8, 8, 11, 12]", arrayListObtenue.toString());
		System.out.println(" ok");
		
		System.out.print("test2 : arbre vide");
		a = arbreVide();
		arrayListObtenue = a.toArrayList();
		assertEquals("test1 ko", "[]", arrayListObtenue.toString());
		System.out.println(" ok");
		
	}
	
	private static void testNombreEntiersEntre() {
		System.out.print("test1 : arbre enonce");
		ABRDEntiers a = arbreEnonce();
		assertEquals("test1 ko  entre 4 et 6", 3, a.nombreEntiersComprisEntre(4, 6));
		assertEquals("test1 ko  entre 0 et 15", 9, a.nombreEntiersComprisEntre(0, 15));
		try{
			assertEquals("test1 ko  entre 10 et 10", 0, a.nombreEntiersComprisEntre(10, 10));
		}catch (IllegalArgumentException e){
			System.out.println();
			System.out.println("test1 ko : 10 = 10 : il ne fallait pas lancer une exception");
			System.exit(0);
		}
		assertEquals("test1 ko  entre 8 et 8", 2, a.nombreEntiersComprisEntre(8, 8));
		assertEquals("test1 ko  entre 11 et 15", 2, a.nombreEntiersComprisEntre(11, 15));
		assertEquals("test1 ko  entre 3 et 9", 6, a.nombreEntiersComprisEntre(3, 9));
		assertEquals("test1 ko  entre 3 et 8", 6, a.nombreEntiersComprisEntre(3, 9));
		try{
			a.nombreEntiersComprisEntre(9, 3);
			System.out.println();
			System.out.println("test1 ko : 9 < 3 : il fallait lancer une exception");
			System.exit(0);	
			
		}catch (IllegalArgumentException e){
			
		}
		System.out.println(" ok");
		System.out.print("test2 : arbre vide");
		a = arbreVide();
		assertEquals("test2 ko  entre 4 et 6", 0, a.nombreEntiersComprisEntre(4, 6));
		System.out.println(" ok");
	}
}
