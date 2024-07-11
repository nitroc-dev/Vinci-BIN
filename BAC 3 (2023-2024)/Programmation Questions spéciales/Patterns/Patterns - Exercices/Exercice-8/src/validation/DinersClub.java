package validation;

import domaine.CarteDeCredit;

import java.util.Calendar;

public class DinersClub extends Generateur {
    public DinersClub(Generateur successor) {
        super(successor);
    }

    @Override
    public boolean valider(String numero) {
        if (numero.startsWith("36")) {
            return numero.length() == 14;
        }
        return false;
    }

    @Override
    public CarteDeCredit creerCarte(String numero, Calendar dateExpiration, String nom) {
        if (valider(numero)) {
            return new domaine.DinersClub(numero, dateExpiration, nom);
        }
        return super.creerCarte(numero, dateExpiration, nom);
    }
}
