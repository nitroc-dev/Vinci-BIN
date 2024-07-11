import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AnalyseurDeTexte {

	private final List<Observer> observers;

	public AnalyseurDeTexte() {
		observers = new ArrayList<>();
	}

	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	public void unregisterObserver(Observer observer) {
		observers.remove(observer);
	}

	private void notifyObservers(String line) {
		for (Observer observer : observers) {
			observer.processLine(line);
		}
	}

	private void notifyObservers() {
		for (Observer observer : observers) {
			observer.printResult();
		}
	}

	public void lireFichier(String file) throws IOException {
		BufferedReader lecteurAvecBuffer = null;
		String ligne;
		try {
			lecteurAvecBuffer = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.println("Erreur d'ouverture");
		}

		while ((ligne = lecteurAvecBuffer.readLine()) != null) {
			notifyObservers(ligne);
		}

		lecteurAvecBuffer.close();

		notifyObservers();
	}
}
