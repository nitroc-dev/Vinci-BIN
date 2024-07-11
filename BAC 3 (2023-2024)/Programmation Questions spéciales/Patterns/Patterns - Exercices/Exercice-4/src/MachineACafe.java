public class MachineACafe {

	public enum State {
		PAS_ASSEZ {
			@Override
			public void selectionnerBoisson(MachineACafe machine, ToucheBoisson boisson) {
				throw new IllegalStateException();
			}

			@Override
			public void entrerMonnaie(MachineACafe machine) {
				ToucheBoisson boisson = machine.boisson;
				if (boisson.getPrix() > machine.montantEnCours) {
					machine.afficherPasAssez(boisson);
				} else {
					machine.montantEnCours -= boisson.getPrix();
					machine.afficherBoisson(boisson);
					boisson = null;
					machine.afficherMontant();
					if (machine.montantEnCours == 0)
						machine.etatCourant = State.INACTIF;
					else
						machine.etatCourant = State.COLLECTE;
				}
			}
		}, COLLECTE {
			@Override
			public void selectionnerBoisson(MachineACafe machine, ToucheBoisson toucheBoisson) {
				if (toucheBoisson.getPrix() > machine.montantEnCours) {
					machine.boisson = toucheBoisson;
					machine.afficherPasAssez(machine.boisson);
					machine.boisson = toucheBoisson;
					machine.etatCourant =  State.PAS_ASSEZ;
					return;
				}
				machine.montantEnCours -= toucheBoisson.getPrix();
				machine.afficherBoisson(toucheBoisson);
				machine.afficherMontant();
				if (machine.montantEnCours == 0)
					machine.etatCourant = State.INACTIF;
				else
					machine.etatCourant = State.COLLECTE;
			}
		}, INACTIF {
			@Override
			public void selectionnerBoisson(MachineACafe machine, ToucheBoisson toucheBoisson) {
				machine.afficherPasAssez(toucheBoisson);
			}

			@Override
			public void rendreMonnaie(MachineACafe machine) {
				machine.etatCourant = State.INACTIF;
			}
		};

		public void entrerMonnaie(MachineACafe machine) {
			machine.etatCourant = State.COLLECTE;
		}

		public void rendreMonnaie(MachineACafe machine) {
			machine.afficherRetour();
			machine.montantEnCours = 0;
			machine.boisson = null;
			machine.etatCourant = State.INACTIF;
		}

		public abstract void selectionnerBoisson(MachineACafe machine, ToucheBoisson toucheBoisson);
	}

	private State etatCourant = State.INACTIF;
	private int montantEnCours = 0;
	private ToucheBoisson boisson = null;
	
	public void afficherMontant() {
		System.out.println(montantEnCours + " cents disponibles");
	}
	
	public void afficherRetour() {
		System.out.println(montantEnCours + " cents rendus");
	}
	
	public void afficherPasAssez(ToucheBoisson toucheBoisson) {
		System.out.println("Vous n'avez pas introduit un montant suffisant pour un " + toucheBoisson);
		System.out.println("Il manque encore " + (toucheBoisson.getPrix() - montantEnCours) + " cents");
	}

	public void afficherBoisson(ToucheBoisson toucheBoisson) {
		System.out.println("Voici un " + toucheBoisson);
	}

	public void entrerMonnaie(Piece piece) {
		montantEnCours += piece.getValeur();
		afficherMontant();

		etatCourant.entrerMonnaie(this);
	}
	
	public void selectionnerBoisson(ToucheBoisson toucheBoisson) {
		etatCourant.selectionnerBoisson(this, toucheBoisson);
	}
	
	public void rendreMonnaie() {
		etatCourant.rendreMonnaie(this);
	}
}
