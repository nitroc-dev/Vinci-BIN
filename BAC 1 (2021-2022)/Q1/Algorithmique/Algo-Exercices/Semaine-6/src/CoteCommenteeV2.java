import java.util.Scanner;

public class CoteCommenteeV2 {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Entrer la cote de l'elève");
        double cote = Utilitaires.lireReelComprisEntre(0,20);
        donnerCommentaire(cote);
    }

    private static void donnerCommentaire(double cote) {
        if (cote < 10) {
            System.out.println("L'étudiant n'a pas validé l'UE");
        }
        else if (cote <= 14) {
            System.out.println("L'étudiant a validé l'UE");
        }
        else if (cote <= 16) {
            System.out.println("L'étudiant a validé l'UE avec une belle cote");
        }
        else {
            System.out.println("L'étudiant a validé l'UE avec une très belle cote");
        }
    }


}

