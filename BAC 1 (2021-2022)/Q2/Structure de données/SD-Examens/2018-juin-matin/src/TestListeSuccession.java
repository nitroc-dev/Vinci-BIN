
public class TestListeSuccession {
	
	public static void main(String [] args){
		
		System.out.println("La famille royale britannique : ");
		
		ListeSuccession<String> l = new ListeSuccession<>("Elisabeth");
		//System.out.println(l);
		l.ajouterEnFin("Charles");
		//System.out.println(l);
		l.ajouterEnFin("William");
		//System.out.println(l);
		l.ajouterEnFin("Georges");
		//System.out.println(l);
		l.ajouterEnFin("Charlotte");
		//System.out.println(l);
		l.ajouterEnFin("Louis");
		//System.out.println(l);
		l.ajouterEnFin("Harry");
		System.out.println(l);
	
		
		System.out.println("En tete de la monarchie : "+l.donnerLeader());
		System.out.println("Voici l'ordre de succession : ");
		System.out.println("En premier : "+ l.donnerElement(1));
		System.out.println("En deuxieme : "+ l.donnerElement(2));
		System.out.println("En troisieme : "+ l.donnerElement(3));
		System.out.println("En quatrieme : "+ l.donnerElement(4));
		System.out.println("En cinquieme : "+ l.donnerElement(5));
		System.out.println("En sixieme : "+ l.donnerElement(6));
		
		System.out.println("Charles a la position "+l.donnerPosition("Charles") + " dans l'ordre de succession");
		System.out.println("William a la position "+l.donnerPosition("William") + " dans l'ordre de succession");
		System.out.println("Harry a la position "+l.donnerPosition("Harry") + " dans l'ordre de succession");
		
		System.out.println("Apres abdication d'Elisabeth, voici la liste");
		l.supprimerLeader();
		System.out.println(l);
		
		
		
		//l.donnerElement(-1);
		//l.donnerElement(7);
		
		//l.ajouterEnFin(null);
		
		//l.ajouterEnFin("Louis");
		//System.out.println(l);
		
		//l.donnerPosition(null);
		
		
		
		
		//l = new ListeSuccession<String>("Elisabeth");
		//System.out.println(l.supprimerLeader());
		
	}

}
