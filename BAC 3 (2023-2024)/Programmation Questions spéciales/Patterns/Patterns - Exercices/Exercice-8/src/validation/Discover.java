package validation;

import domaine.CarteDeCredit;

import java.util.Calendar;

public class Discover extends Generateur {
    public Discover(Generateur successor) {
        super(successor);
    }

    @Override
    public boolean valider(String numero) {
        if (numero.startsWith("6011") || numero.startsWith("65")) {
            return numero.length() == 16;
        }
        return false;
    }

    @Override
    public CarteDeCredit creerCarte(String numero, Calendar dateExpiration, String nom) {
        if (valider(numero)) {
            return new domaine.Discover(numero, dateExpiration, nom);
        }
        return super.creerCarte(numero, dateExpiration, nom);
    }
}
