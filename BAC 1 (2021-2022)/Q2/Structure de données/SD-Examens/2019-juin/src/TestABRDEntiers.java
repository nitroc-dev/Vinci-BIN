import java.util.Iterator;
import java.util.Scanner;


public class TestABRDEntiers {
	
	private final static Scanner scanner = new Scanner(System.in);
	
	/**
	 * Cette methode verifie qu'un resultat attendu est bien un resultat obtenu.
	 * 
	 * @param messageErreur message a afficher en cas de probleme
	 * @param attendu la valeur qu'on s'attendait a recevoir
	 * @param recu la valeur qu'on a recu en realite
	 */
	private static void assertEquals(String messageErreur, Object attendu, Object recu) {
		if (attendu==null) {
			if (recu!=null) {
				System.out.println(messageErreur+". Attendu="+attendu+" recu="+recu);
				System.exit(0);
			}
		} else if (!attendu.equals(recu)) {
			System.out.println(messageErreur+". Attendu="+attendu+" recu="+recu);
			System.exit(0);			
		}
	}

	public static void main(String[] args) {
		
		
		System.out.println("*****************************************");
		System.out.println("Programme Test pour la classe ABRDEntiers");
		System.out.println("*****************************************");
	
		int choix = 0;
		do {
			System.out.println();
			System.out.println("1 -> Tester la methode nombreOccurrences()");
			System.out.println("2 -> Tester la methode insere()");
			System.out.println("3 -> Tester la methode iterator()");
			System.out.println();
			System.out.print("Votre choix : ");
			choix = scanner.nextInt();
			switch (choix) {
			case 1:
				testNombreOccurrences();
				break;
			case 2:
				testInsere();
				break;

			case 3:
				testIterator();
				break;

			default:
				break;
			}

		} while (choix >= 1 && choix <= 3);

	}
	
	
	private static void testNombreOccurrences() {
		ABRDEntiers aTeste = new ABRDEntiers(1);
		System.out.println("l'arbre teste est l'arbre de l'enonce.");
		
		System.out.print("nombre occurrences de 12");
		assertEquals("\nnombre occurrences de 12 ko", 2, aTeste.nombreOccurrences(12));
		System.out.println(" ok");
		
		System.out.print("nombre occurrences de 7");
		assertEquals("\nnombre occurrences de 7 ko", 3, aTeste.nombreOccurrences(7));
		System.out.println(" ok");
		
		System.out.print("nombre occurrences de 1");
		assertEquals("\nnombre occurrences de 1 ko", 2, aTeste.nombreOccurrences(1));
		System.out.println(" ok");
		
		System.out.print("nombre occurrences de 15");
		assertEquals("\nnombre occurrences de 15 ko", 2, aTeste.nombreOccurrences(15));
		System.out.println(" ok");
		
		System.out.print("nombre occurrences de 13");
		assertEquals("\nnombre occurrences de 13 ko", 1, aTeste.nombreOccurrences(13));
		System.out.println(" ok");
		
		System.out.print("nombre occurrences de 14");
		assertEquals("\nnombre occurrences de 14 ko", 0, aTeste.nombreOccurrences(14));
		System.out.println(" ok");
		
		System.out.print("nombre occurrences de 14");
		assertEquals("\nnombre occurrences de 14 ko", 0, aTeste.nombreOccurrences(14));
		System.out.println(" ok");
		
		System.out.print("nombre occurrences de 17");
		assertEquals("\nnombre occurrences de 17 ko", 0, aTeste.nombreOccurrences(17));
		System.out.println(" ok");
		
		
		System.out.println("--> Tous les tests de la methode nombreOccurrences() avec l'arbre de l'enonce ont reussi.");

		System.out.println();
	}
	
	
	



	private static void testInsere() {
		System.out.println("Le scenario suivi part d'un arbre vide"); 
		System.out.println("A la fin des insertions, l'arbre devrait correspondre a l'arbre de l'enonce");
		ABRDEntiers a = new ABRDEntiers();
		a.insere(12);
		System.out.println("insere 12");
		System.out.println(a.toString());
		assertEquals("insere 12 contenu ko", "[ 12(1x) ]", a.toString());

		a.insere(7);
		System.out.println("insere 7");
		System.out.println(a.toString());
		assertEquals("insere 7 contenu ko", "[  [ 7(1x) ] 12(1x) [ ]  ]", a.toString());

		a.insere(7);
		System.out.println("insere 7");
		System.out.println(a.toString());
		assertEquals("insere 7 contenu ko", "[  [ 7(2x) ] 12(1x) [ ]  ]", a.toString());

		a.insere(7);
		System.out.println("insere 7");
		System.out.println(a.toString());
		assertEquals("insere 7 contenu ko", "[  [ 7(3x) ] 12(1x) [ ]  ]", a.toString());

		a.insere(3);
		System.out.println("insere 3");
		System.out.println(a.toString());
		assertEquals("insere 3 contenu ko", "[  [  [ 3(1x) ] 7(3x) [ ]  ] 12(1x) [ ]  ]", a.toString());

		a.insere(15);
		System.out.println("insere 15");
		System.out.println(a.toString());
		assertEquals("insere 15 contenu ko", "[  [  [ 3(1x) ] 7(3x) [ ]  ] 12(1x) [ 15(1x) ]  ]", a.toString());

		a.insere(15);
		System.out.println("insere 15");
		System.out.println(a.toString());
		assertEquals("insere 15 contenu ko", "[  [  [ 3(1x) ] 7(3x) [ ]  ] 12(1x) [ 15(2x) ]  ]", a.toString());

		a.insere(1);
		System.out.println("insere 1");
		System.out.println(a.toString());
		assertEquals("insere 1 contenu ko", "[  [  [  [ 1(1x) ] 3(1x) [ ]  ] 7(3x) [ ]  ] 12(1x) [ 15(2x) ]  ]", a.toString());


		a.insere(8);
		System.out.println("insere 8");
		System.out.println(a.toString());
		assertEquals("insere 8 contenu ko", "[  [  [  [ 1(1x) ] 3(1x) [ ]  ] 7(3x) [ 8(1x) ]  ] 12(1x) [ 15(2x) ]  ]", a.toString());
	
		
		a.insere(13);
		System.out.println("insere 13");
		System.out.println(a.toString());
		assertEquals("insere 13 contenu ko", "[  [  [  [ 1(1x) ] 3(1x) [ ]  ] 7(3x) [ 8(1x) ]  ] 12(1x) [  [ 13(1x) ] 15(2x) [ ]  ]  ]", a.toString());
		

		a.insere(12);
		System.out.println("insere 12");
		System.out.println(a.toString());
		assertEquals("insere 12 contenu ko", "[  [  [  [ 1(1x) ] 3(1x) [ ]  ] 7(3x) [ 8(1x) ]  ] 12(2x) [  [ 13(1x) ] 15(2x) [ ]  ]  ]", a.toString());

		a.insere(1);
		System.out.println("insere 1");
		System.out.println(a.toString());
		assertEquals("insere 1 contenu ko", "[  [  [  [ 1(2x) ] 3(1x) [ ]  ] 7(3x) [ 8(1x) ]  ] 12(2x) [  [ 13(1x) ] 15(2x) [ ]  ]  ]", a.toString());

		System.out.println();
		System.out.println("--> Tous les tests de la methode insere() pour construire l'arbre de l'enonce ont reussi.");

		System.out.println();
	}


	private static void testIterator() {
		ABRDEntiers aTeste = new ABRDEntiers(1);
		System.out.println("l'arbre teste est l'arbre de l'enonce.");
		int[] entiers = {1, 1, 3, 7, 7, 7, 8, 12, 12, 13, 15, 15};
		int i=0;
		for (Integer entier : aTeste) {
			if(i>11){
				System.out.println("nombre next ko. Attendu=12 reçu=13");
				System.exit(0);
			}
			System.out.print("next :"+entiers[i]);
			assertEquals("\nnext ko", entiers[i], entier);
			System.out.println(" ok");
			i++;
		}
		assertEquals("nombre next ko", 12, i);
		System.out.println();
		System.out.println();
		System.out.println("--> L'arbre teste a bien ete parcouru par votre iterateur.");
	}
	






}
