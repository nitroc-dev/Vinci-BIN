package be.nitroc.algo.dessins;

import utils.Tortue;

public class DessinLunettes {

    public static Tortue tortue = new Tortue();

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            tortue.avancer(100);
            tortue.tournerADroite(90);
        }
        tortue.tournerAGauche(180);
        tortue.avancer(50);
        for (int i = 0; i < 4; i++) {
            tortue.avancer(100);
            tortue.tournerAGauche(90);
        }
    }
}
