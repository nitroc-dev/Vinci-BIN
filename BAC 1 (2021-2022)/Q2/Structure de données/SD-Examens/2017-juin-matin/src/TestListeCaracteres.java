import java.util.Scanner;

public class TestListeCaracteres {

	private static Scanner scanner = new Scanner(System.in);

	/**
	 * Cette methode verifie qu'un resultat attendu est bien le resultat obtenu.
	 * 
	 * @param messageErreur message a afficher en cas de probleme
	 * @param attendu la valeur qu'on s'attendait a recevoir
	 * @param recu la valeur qu'on a reçu en realite
	 */
	private static void assertEquals(String messageErreur, Object attendu, Object recu) {
		if (attendu==null) {
			if (recu!=null) {
				System.out.println();
				System.out.println("     "+messageErreur+" attendu="+attendu+" reçu="+recu);
				System.exit(0);
			}
		} else if (!attendu.equals(recu)) {
			System.out.println();
			System.out.println("    "+messageErreur+" attendu="+attendu+" reçu="+recu);
			System.exit(0);			
		}
	}

	public static void main(String[] args) {

		System.out.println("*********************************************");
		System.out.println("Programme Test pour la classe ListeCaracteres");
		System.out.println("*********************************************");
		int choix = 0;
		do {
			System.out.println("1 -> Tester la methode inserer");
			System.out.println("2 -> Tester la methode contientExAequo");
			System.out.println("3 -> Tester la methode nombreOccurrences");
			System.out.println("4 -> Tester la methode contientCaractereTable");
			System.out.println();
			System.out.print("Entrez votre choix : ");
			choix = scanner.nextInt();
			switch (choix) {
			case 1:
				testInserer();
				break;
			case 2:
				testContientExAequo();
				break;
			case 3:
				testNombreOccurrences();
				break;
			case 4:
				testContientCaractereTable();
				break;
			default:
				break;
			}
		} while (choix >= 1 && choix <= 4 );
	}



	private static void testInserer() {
		char [] caracteres = {'a','b','c','d'};	
		ListeCaracteres l = new ListeCaracteres(caracteres);
		System.out.print("Test 1 : liste testee : "+l +"     appel methode : inserer(1,x)");	
		assertEquals("ko : booleen renvoye",true,l.inserer(1, 'x'));
		assertEquals("ko : liste apres insertion : "," a x b c d",l.toString());
		System.out.println(" : ok");
		l = new ListeCaracteres(caracteres);
		System.out.print("Test 2 : liste testee : "+l +"     appel methode : inserer(2,x)");	
		assertEquals("ko : booleen renvoye",true,l.inserer(2, 'x'));
		assertEquals("ko : liste apres insertion : "," a b x c d",l.toString());
		System.out.println(" : ok");
		l = new ListeCaracteres(caracteres);
		System.out.print("Test 3 : liste testee : "+l +"     appel methode : inserer(4,x)");	
		assertEquals("ko : booleen renvoye",true,l.inserer(4, 'x'));
		assertEquals("ko : liste apres insertion : "," a b c d x",l.toString());
		System.out.println(" : ok");
		l = new ListeCaracteres(caracteres);
		System.out.print("Test 4 : liste testee : "+l +"     appel methode : inserer(0,x)");	
		assertEquals("ko : booleen renvoye",true,l.inserer(0, 'x'));
		assertEquals("ko : liste apres insertion : "," x a b c d",l.toString());
		System.out.println(" : ok");
		l = new ListeCaracteres(caracteres);
		System.out.print("Test 5 : liste testee : "+l +"     appel methode : inserer(-1,x)");	
		assertEquals("ko : booleen renvoye",false,l.inserer(-1, 'x'));
		assertEquals("ko : liste apres insertion : "," a b c d",l.toString());
		System.out.println(" : ok");
		l = new ListeCaracteres(caracteres);
		System.out.print("Test 6 : liste testee : "+l +"     appel methode : inserer(5,x)");	
		assertEquals("ko : booleen renvoye",false,l.inserer(5, 'x'));
		assertEquals("ko : liste apres insertion : "," a b c d",l.toString());
		System.out.println(" : ok");
		l = new ListeCaracteres();
		System.out.print("Test 7 : liste vide :                appel methode : inserer(0,x)");	
		assertEquals("ko : booleen renvoye",true,l.inserer(0, 'x'));
		assertEquals("ko : liste apres insertion : "," x",l.toString());
		System.out.println(" : ok");
		
		System.out.println("Tous les tests ont reussi!");
		System.out.println();
		System.out.println();
		
	}

	private static void testContientExAequo() {
		char [] caracteres1 = {'a','b','c','d'};	
		ListeCaracteres l = new ListeCaracteres(caracteres1);
		System.out.print("Test 1 : liste testee : "+l +"     appel methode : contientExAequo");	
		assertEquals("ko : booleen renvoye",false,l.contientExAequos());
		assertEquals("ko : liste apres test : "," a b c d",l.toString());
		System.out.println(" : ok");
		char [] caracteres2 = {'a','b','a','d'};	
		l = new ListeCaracteres(caracteres2);
		System.out.print("Test 2 : liste testee : "+l +"     appel methode : contientExAequo");	
		assertEquals("ko : booleen renvoye",true,l.contientExAequos());
		System.out.println(" : ok");
		char [] caracteres3 = {'a','b','c','a'};	
		l = new ListeCaracteres(caracteres3);
		System.out.print("Test 3 : liste testee : "+l +"     appel methode : contientExAequo");	
		assertEquals("ko : booleen renvoye",true,l.contientExAequos());
		System.out.println(" : ok");
		l = new ListeCaracteres();
		System.out.print("Test 4 : liste vide :                appel methode : contientExAequo");	
		assertEquals("ko : booleen renvoye",false,l.contientExAequos());
		System.out.println(" : ok");
		System.out.println("Tous les tests ont reussi!");
		System.out.println();
		System.out.println();
		
	}
	private static void testNombreOccurrences(){
		char [] caracteres = {'a','a','b','a'};
		
		ListeCaracteres l = new ListeCaracteres(caracteres);
		System.out.print("Test 1 : liste testee : "+l +"   appel methode : nombreOccurrences('a') : ");
		assertEquals("ko : nombre occurrences : ",3,l.nombreOccurrences('a'));
		assertEquals("la liste a ete modifiee! "," a a b a",l.toString());
		System.out.println("ok");
		
		l = new ListeCaracteres(caracteres);
		System.out.print("Test 2 : liste testee : "+l +"   appel methode : nombreOccurrences('b') : ");
		assertEquals("ko : nombre occurrences : ",1,l.nombreOccurrences('b'));
		assertEquals("la liste a ete modifiee! "," a a b a",l.toString());
		System.out.println("ok");
		
		l = new ListeCaracteres(caracteres);
		System.out.print("Test 3 : liste testee : "+l +"   appel methode : nombreOccurrences('x') : ");
		assertEquals("ko : nombre occurrences : ",0,l.nombreOccurrences('x'));
		assertEquals("la liste a ete modifiee! "," a a b a",l.toString());
		System.out.println("ok");
		
		l = new ListeCaracteres();
		System.out.print("Test 4 : liste vide :              appel methode : nombreOccurrences('a') : ");
		assertEquals("ko : nombre occurrences : ",0,l.nombreOccurrences('a'));
		System.out.println("ok");
		
		System.out.println("Tous les tests ont reussi!");
		System.out.println();
		System.out.println();
	}

	private static void testContientCaractereTable() {
		char [] caracteres1 = {'a','b','c','d'};
		char [] voyelles = {'a','e','i','o','u','y'};
		ListeCaracteres l = new ListeCaracteres(caracteres1);
		System.out.print("Test 1 : liste testee : "+l +"     appel methode : contientCaractereTable()");	
		assertEquals("ko : booleen renvoye",true,l.contientCaractereTable(voyelles));
		assertEquals("ko : liste apres test : "," a b c d",l.toString());
		System.out.println(" : ok");
		char [] caracteres2 = {'b','c','d','i'};	
		l = new ListeCaracteres(caracteres2);
		System.out.print("Test 2 : liste testee : "+l +"     appel methode : contientCaractereTable()");	
		assertEquals("ko : booleen renvoye",true,l.contientCaractereTable(voyelles));
		System.out.println(" : ok");
		char [] caracteres3 = {'b','o','u','d'};	
		l = new ListeCaracteres(caracteres3);
		System.out.print("Test 3 : liste testee : "+l +"     appel methode : contientCaractereTable()");	
		assertEquals("ko : booleen renvoye",true,l.contientCaractereTable(voyelles));
		System.out.println(" : ok");
		char [] caracteres4 = {'b','c','d'};	
		l = new ListeCaracteres(caracteres4);
		System.out.print("Test 4 : liste testee : "+l +"       appel methode : contientCaractereTable()");	
		assertEquals("ko : booleen renvoye",false,l.contientCaractereTable(voyelles));
		System.out.println(" : ok");
		l = new ListeCaracteres();
		System.out.print("Test 5 : liste vide :                appel methode : contientCaractereTable()");	
		assertEquals("ko : booleen renvoye",false,l.contientCaractereTable(voyelles));
		System.out.println(" : ok");
		System.out.println("Tous les tests ont reussi!");
		System.out.println();
		System.out.println();
	}

	



}
