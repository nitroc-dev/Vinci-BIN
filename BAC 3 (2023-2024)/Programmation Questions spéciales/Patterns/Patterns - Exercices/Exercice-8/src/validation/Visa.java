package validation;

import domaine.CarteDeCredit;

import java.util.Calendar;

public class Visa extends Generateur {
    public Visa(Generateur successor) {
        super(successor);
    }

    @Override
    public boolean valider(String numero) {
        if (numero.startsWith("4")) {
            return numero.length() == 16;
        }
        return false;
    }

    @Override
    public CarteDeCredit creerCarte(String numero, Calendar dateExpiration, String nom) {
        if (valider(numero)) {
            return new domaine.Visa(numero, dateExpiration, nom);
        }
        return super.creerCarte(numero, dateExpiration, nom);
    }
}
