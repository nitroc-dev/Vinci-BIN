package be.nitroc.algo.dessins;

import utils.Tortue;

public class DessinEtoile2 {

    public static Tortue tortue = new Tortue();

    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            tortue.avancer(50);
            tortue.tournerAGauche(60);
            tortue.avancer(50);
            tortue.tournerADroite(120);
        }
    }
}
