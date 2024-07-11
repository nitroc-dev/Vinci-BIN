package programme;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;


public class ProgrammesEtudiants {

	// ajouter/modifier attributs ici
	private Set<Etudiant> etudiants = new HashSet<>();
	private Map<Etudiant, Deque<UniteEnseignement>> etudiantsUEs;

	public ProgrammesEtudiants(Etudiant... etudiants) {
		for (Etudiant etudiant : etudiants) {
			this.etudiants.add(etudiant);
		}
		etudiantsUEs = new HashMap<>();
		for (Etudiant etudiant : etudiants) {
			etudiantsUEs.put(etudiant, new ArrayDeque<>());
		}
	}

	// A COMPLETER
	// Enregistre la validation de l'unit� d'enseignement par l'�tudiant et met �
	// jour le nombre d'ects valid� par l'�tudiant.
	// Si l'unit� d'enseignement a d�j� �t� valid�e par l'�tudiant, la m�thode
	// lance une RuntimeException avec le message 'ue d�j� valid�e'
	public void valider(Etudiant e, UniteEnseignement ue) {
		if (etudiantsUEs.get(e).contains(ue)) {
			throw new RuntimeException("ue d�j� valid�e");
		}
		e.setNbEctsValides(e.getNbEctsValides() + ue.getNbEcts());
		etudiantsUEs.get(e).add(ue);
	}

	// A COMPLETER
	// affiche la liste de tous les �tudiants (pr�nom, nom et nombre d'ects valid�s)
	// tri�s par le nombre d'ects valid�s
	// Voici un exemple de sortie attendue:
	// Alain Delcourt 10 ects
	// Pol Durant 8 ects
	// Jean Michel 0 ects
	// Si deux �tudiants ont le meme nombre d'ects valid�s, on affiche
	// les deux �tudiants dans n'importe quel sens.
	public void afficherEtudiantsTriesParEcts() {
		Queue<Etudiant> etudiantsTries = new PriorityQueue<>(Comparator.comparingInt(Etudiant::getNbEctsValides));
		etudiantsTries.addAll(etudiants);
		while (!etudiantsTries.isEmpty()) {
			Etudiant e = etudiantsTries.poll();
			System.out.println(e.getPrenom() + " " + e.getNom() + " " + e.getNbEctsValides() + " ects");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Etudiant e1= new Etudiant(123456, "Durant", "Pol");
		Etudiant e2= new Etudiant(123453, "Delcourt", "Alain");
		Etudiant e3= new Etudiant(123452, "Michel", "Jean");
		ProgrammesEtudiants p= new ProgrammesEtudiants(e1,e2,e3);
		UniteEnseignement sd2= new UniteEnseignement("SD2", 4);
		UniteEnseignement bd2= new UniteEnseignement("BD2", 6);
		UniteEnseignement mobile= new UniteEnseignement("Mobile", 4);
		p.valider(e1, sd2);
		p.valider(e1, mobile);
		p.valider(e2, bd2);
		p.valider(e2, mobile);
		p.afficherEtudiantsTriesParEcts();
		Thread.sleep(50); //cette ligne est uniquement presente pour l'affichage
		p.valider(e1, mobile);
	}
}
