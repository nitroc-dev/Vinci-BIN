
public class TestEntrepot {
	
	public static void main(String [] args){
		Entrepot e = new Entrepot(5);
		Societe societe1 = new Societe(1, "nom1");
		Societe societe3 = new Societe(3, "nom3");
		Societe societe999 = new Societe(999, "nom999");
		System.out.println("la societe numero 3 s'est vue attribuer le hangar numero "+e.attribuerHangar(societe3));
		System.out.println("les hangars de la societe nom3 : "+e.listeHangars(societe3));
		System.out.println();
		System.out.println("la societe numero 1 s'est vue attribuer le hangar numero "+e.attribuerHangar(societe1));
		System.out.println("les hangars de la societe nom1 : "+e.listeHangars(societe1));
		System.out.println();
		System.out.println("la societe numero 3 s'est vue attribuer le hangar numero "+e.attribuerHangar(societe3));
		System.out.println("les hangars de la societe nom3 : "+e.listeHangars(societe3));
		System.out.println();
		System.out.println("la societe numero 3 s'est vue attribuer le hangar numero "+e.attribuerHangar(societe3));
		System.out.println("les hangars de la societe nom3 : "+e.listeHangars(societe3));
		System.out.println();
		System.out.println("la societe numero 3 s'est vue attribuer le hangar numero "+e.attribuerHangar(societe3));
		System.out.println("les hangars de la societe nom3 : "+e.listeHangars(societe3));
		System.out.println();
		System.out.println("la societe numero 3 s'est vue attribuer le hangar numero "+e.attribuerHangar(societe3));
		System.out.println("les hangars de la societe nom3 : "+e.listeHangars(societe3));
		System.out.println();
		System.out.println("la societe numero 999 s'est vue attribuer le hangar numero "+e.attribuerHangar(societe999));
		System.out.println("les hangars de la societe nom999 : "+e.listeHangars(societe999));
		
	}
}
