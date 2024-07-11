package validation;

import java.util.Calendar;

import domaine.CarteDeCredit;

public abstract class Generateur {

	private Generateur successor;

	public Generateur(Generateur successor) {
		this.successor = successor;
	}

	public abstract boolean valider(String numero);

	public CarteDeCredit creerCarte(String numero, Calendar dateExpiration, String nom) {
		if (successor == null) {
			return null;
		}
		return successor.creerCarte(numero, dateExpiration, nom);
	}
}
