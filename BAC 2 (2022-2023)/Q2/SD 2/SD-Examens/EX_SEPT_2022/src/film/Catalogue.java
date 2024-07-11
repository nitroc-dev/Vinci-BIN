package film;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Catalogue {

	//Vous pouvez ajouter des attributs et/ou des constructeurs
	private Map<Integer, List<Film>> catalogue;

	public Catalogue() {
		catalogue = new HashMap<>();
	}

	//ajoute le film au catalogue
	public void ajouterFilm(Film f) {
		catalogue.computeIfAbsent(f.getAnnee(), k -> new ArrayList<>()).add(f);
	}

	// affiche tous les films de l'ann�e en param�tre par ordre alphab�tique
	// utilise le toString() de Film
	public void afficherFilmParOrdreAlphabetique(int annee) {
		List<Film> films = catalogue.getOrDefault(annee, Collections.emptyList());
		films.stream().sorted(Comparator.comparing(Film::getNom)).forEach(System.out::println);
		if (films.isEmpty()) {
			System.out.println("Aucun film de l'année " + annee);
		}
	}
}
