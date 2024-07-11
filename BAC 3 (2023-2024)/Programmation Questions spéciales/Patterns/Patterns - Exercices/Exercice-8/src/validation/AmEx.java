package validation;

import domaine.CarteDeCredit;

import java.util.Calendar;

public class AmEx extends Generateur {
    public AmEx(Generateur successor) {
        super(successor);
    }

    @Override
    public boolean valider(String numero) {
        if (numero.startsWith("37") || numero.startsWith("34")) {
            return numero.length() == 15;
        }
        return false;
    }

    @Override
    public CarteDeCredit creerCarte(String numero, Calendar dateExpiration, String nom) {
        if (valider(numero)) {
            return new domaine.AmEx(numero, dateExpiration, nom);
        }
        return super.creerCarte(numero, dateExpiration, nom);
    }
}
