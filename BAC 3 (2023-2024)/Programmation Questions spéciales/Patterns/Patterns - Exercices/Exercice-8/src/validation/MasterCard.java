package validation;

import domaine.CarteDeCredit;

import java.util.Calendar;

public class MasterCard extends Generateur {
    public MasterCard(Generateur successor) {
        super(successor);
    }

    @Override
    public boolean valider(String numero) {
        if (numero.startsWith("51") || numero.startsWith("52") || numero.startsWith("53") || numero.startsWith("54") || numero.startsWith("55")) {
            return numero.length() == 16;
        }
        return false;
    }

    @Override
    public CarteDeCredit creerCarte(String numero, Calendar dateExpiration, String nom) {
        if (valider(numero)) {
            return new domaine.MasterCard(numero, dateExpiration, nom);
        }
        return super.creerCarte(numero, dateExpiration, nom);
    }
}
