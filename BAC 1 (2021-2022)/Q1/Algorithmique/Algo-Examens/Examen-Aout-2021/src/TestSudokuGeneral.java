
public class TestSudokuGeneral {
	
	public static void main(String[] args) {
		int [][] legaleVide = {
				{0,0,0,	0,0,0, 0,0,0 },
				{0,0,0,	0,0,0, 0,0,0 },
				{0,0,0,	0,0,0, 0,0,0 },
				
				{0,0,0,	0,0,0, 0,0,0 },
				{0,0,0,	0,0,0, 0,0,0 },
				{0,0,0,	0,0,0, 0,0,0 },
				
				{0,0,0,	0,0,0, 0,0,0 },
				{0,0,0,	0,0,0, 0,0,0 },
				{0,0,0,	0,0,0, 0,0,0 }								
		};
		
		int [][] legaleB = {
				{1,0,0,	0,0,0, 0,0,0 },
				{0,0,0,	0,0,0, 0,0,0 },
				{0,0,0,	0,0,0, 0,0,0 },
				
				{0,1,0,	0,0,2, 4,5,6 },
				{2,0,0,	0,0,0, 9,8,7 },
				{0,0,0,	0,0,0, 1,3,2 },
				
				{0,0,0,	0,0,0, 0,0,0 },
				{0,0,1,	0,0,0, 0,0,0 },
				{0,0,0,	0,0,0, 0,0,0 }								
		};
		
		int [][] illegaleRegion = {
				{1,0,0,	0,0,0, 0,0,0 },
				{0,0,0,	0,0,0, 0,0,0 },
				{0,0,0,	0,0,0, 0,0,0 },
				
				{0,1,0,	0,0,2, 4,5,6 },
				{2,0,0,	0,0,0, 9,8,7 },
				{0,0,0,	0,0,0, 1,3,2 },
				
				{0,0,0,	0,0,0, 9,0,0 }, // R�gion en 6, 6 ill�gale
				{0,0,1,	0,0,0, 0,0,0 },
				{0,0,0,	0,0,0, 0,0,9 }		
		};
		
		int [][] illegaleColonne = { // Colonne 8 ill�gale
				{1,0,0,	0,0,0, 0,0,3 },
				{0,0,0,	0,0,0, 0,0,0 },
				{0,0,0,	0,0,0, 0,0,0 },
				
				{0,1,0,	0,0,2, 4,5,6 },
				{2,0,0,	0,0,0, 9,8,7 },
				{0,0,0,	0,0,0, 1,3,2 },
				
				{0,0,0,	0,0,0, 0,0,0 },
				{0,0,1,	0,0,0, 0,0,0 },
				{0,0,0,	0,0,0, 0,0,2 }		
		};
		
		int [][] illegaleLigne = {
				{1,0,0,	0,0,0, 0,0,0 },
				{0,0,0,	0,0,0, 0,0,0 },
				{0,0,0,	0,0,0, 0,0,0 },
				
				{0,1,0,	0,0,2, 4,5,6 },
				{2,0,0,	0,0,0, 9,8,7 },
				{0,0,0,	0,0,0, 1,3,2 },
				
				{4,0,0,	0,0,0, 0,0,4 }, // Ligne ill�gale
				{0,0,1,	0,0,0, 0,0,0 },
				{0,0,0,	0,0,0, 0,0,0 }		
		};
		
		
		SudokuGeneral grille;
		
		grille = new SudokuGeneral(legaleVide);
		if (!grille.isGrilleLegale())
			System.out.println("legaleVide indiqu�e comme non l�gale");
		else
			System.out.println("legaleVide ok!");
		
		
		
		grille = new SudokuGeneral(legaleB);
		if (!grille.isGrilleLegale())
			System.out.println("legaleB indiqu�e comme non l�gale");
		else
			System.out.println("legaleB ok!");
		
		// Coup l�gal
		if (!grille.isCoupLegal(1, 5, 1))
			System.out.println("Le coup 1, 5, 1 est devrait �tre l�gal et est indiqu� comme ill�gal");
		else
			System.out.println("1, 5, 1 ok!");
		
		
		// Coup ill�gal colonne
		if (grille.isCoupLegal(0, 5, 1))
			System.out.println("Le coup 0, 5, 1 est devrait �tre ill�gal et est indiqu� comme l�gal");
		else
			System.out.println("0, 5, 1 ok!");
		
		// Copu ill�gal ligne
		if (grille.isCoupLegal(7, 0, 1))
			System.out.println("Le coup 7, 5, 1 est devrait �tre ill�gal et est indiqu� comme l�gal");
		else
			System.out.println("7, 5, 1 ok!");
		
		
		// Coup ill�gal r�gion
		if (grille.isCoupLegal(4, 8, 2))
			System.out.println("Le coup 4, 8, 2 est devrait �tre ill�gal et est indiqu� comme l�gal");
		else
			System.out.println("4, 8, 2 ok!");
		
		grille = new SudokuGeneral(illegaleColonne);
		if (grille.isGrilleLegale())
			System.out.println("illegaleColonne indiqu�e comme l�gale");
		else
			System.out.println("illegaleColonne ok!");
		
		grille = new SudokuGeneral(illegaleLigne);
		if (grille.isGrilleLegale())
			System.out.println("illegaleLigne indiqu�e comme l�gale");
		else
			System.out.println("illegaleLigne ok!");
		
		grille = new SudokuGeneral(illegaleRegion);
		if (grille.isGrilleLegale())
			System.out.println("illegaleRegion indiqu�e comme l�gale");
		else
			System.out.println("illegaleRegion ok!");		

	}
}
