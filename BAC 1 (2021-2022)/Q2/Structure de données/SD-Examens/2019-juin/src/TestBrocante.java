
public class TestBrocante {
	public static void main(String[] args) {
		String[] tableRiverains = {"riv1","riv2","riv3"};
		Brocante brocante = new Brocante(8,tableRiverains );
		
		System.out.println("Au depart:");
		System.out.println(brocante.toString());
		
		System.out.println();
		System.out.println("reservation riverain riv1  emplacement 0");
		brocante.reserver("riv1", 0);
		System.out.println(brocante);
		
		System.out.println();
		System.out.println("reservation riverain riv4  emplacement 1 (Attention riv4 n'est pas un riverain!)");
		brocante.reserver("riv4", 0);
		System.out.println(brocante);
		
		System.out.println();
		System.out.println("reservation riverain riv1  emplacement 1");
		brocante.reserver("riv1", 1);
		System.out.println(brocante);
		
		System.out.println();
		System.out.println("reservation riverain riv1  emplacement 6");
		brocante.reserver("riv1", 6);
		System.out.println(brocante);
		
		System.out.println();
		System.out.println("reservation riverain riv1  emplacement 2 (Attention riv1 a deja 3 emplacements!)");
		brocante.reserver("riv1", 6);
		System.out.println(brocante);
		
		System.out.println();
		System.out.println("reservation riverain riv3  emplacement 4");
		brocante.reserver("riv3", 4);
		System.out.println(brocante);
		
		System.out.println();
		brocante.changerPhase();
		System.out.println("changement de phase");
		System.out.println(brocante);

		System.out.println();
		System.out.println("reservation riverain riv1");
		brocante.attribuerAutomatiquementEmplacement("riv1");
		System.out.println(brocante);

		System.out.println();
		System.out.println("reservation riverain riv2");
		brocante.attribuerAutomatiquementEmplacement("riv2");
		System.out.println(brocante);

		System.out.println();
		System.out.println("reservation terminée");
	}
}
