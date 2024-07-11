
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
				
				{0,0,0,	0,0,0, 9,0,0 }, // Région en 6, 6 illégale
				{0,0,1,	0,0,0, 0,0,0 },
				{0,0,0,	0,0,0, 0,0,9 }		
		};
		
		int [][] illegaleColonne = { // Colonne 8 illégale
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
				
				{4,0,0,	0,0,0, 0,0,4 }, // Ligne illégale
				{0,0,1,	0,0,0, 0,0,0 },
				{0,0,0,	0,0,0, 0,0,0 }		
		};
		
		
		SudokuGeneral grille;
		
		grille = new SudokuGeneral(legaleVide);
		if (!grille.isGrilleLegale())
			System.out.println("legaleVide indiquée comme non légale");
		else
			System.out.println("legaleVide ok!");
		
		
		
		grille = new SudokuGeneral(legaleB);
		if (!grille.isGrilleLegale())
			System.out.println("legaleB indiquée comme non légale");
		else
			System.out.println("legaleB ok!");
		
		// Coup légal
		if (!grille.isCoupLegal(1, 5, 1))
			System.out.println("Le coup 1, 5, 1 est devrait être légal et est indiqué comme illégal");
		else
			System.out.println("1, 5, 1 ok!");
		
		
		// Coup illégal colonne
		if (grille.isCoupLegal(0, 5, 1))
			System.out.println("Le coup 0, 5, 1 est devrait être illégal et est indiqué comme légal");
		else
			System.out.println("0, 5, 1 ok!");
		
		// Copu illégal ligne
		if (grille.isCoupLegal(7, 0, 1))
			System.out.println("Le coup 7, 5, 1 est devrait être illégal et est indiqué comme légal");
		else
			System.out.println("7, 5, 1 ok!");
		
		
		// Coup illégal région
		if (grille.isCoupLegal(4, 8, 2))
			System.out.println("Le coup 4, 8, 2 est devrait être illégal et est indiqué comme légal");
		else
			System.out.println("4, 8, 2 ok!");
		
		grille = new SudokuGeneral(illegaleColonne);
		if (grille.isGrilleLegale())
			System.out.println("illegaleColonne indiquée comme légale");
		else
			System.out.println("illegaleColonne ok!");
		
		grille = new SudokuGeneral(illegaleLigne);
		if (grille.isGrilleLegale())
			System.out.println("illegaleLigne indiquée comme légale");
		else
			System.out.println("illegaleLigne ok!");
		
		grille = new SudokuGeneral(illegaleRegion);
		if (grille.isGrilleLegale())
			System.out.println("illegaleRegion indiquée comme légale");
		else
			System.out.println("illegaleRegion ok!");		

	}
}
