import java.sql.*;
import java.util.Scanner;

public class GestionConcert {

    private Connection conn;
    private PreparedStatement afficherConcerts;

    public GestionConcert() {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver manquant !");
            System.exit(1);
        }
        try {
            conn = DriverManager.getConnection(url, "postgres", "postgres");

            afficherConcerts = conn.prepareStatement("SELECT * FROM examen.afficher_concerts WHERE nom_artiste = ?");
        } catch (SQLException e) {
            System.out.println("Erreur de connexion !");
            System.exit(1);
        }
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erreur de fermeture !");
            System.exit(1);
        }
    }

    public void afficherConcerts(String artiste) {
        try {
            afficherConcerts.setString(1, artiste);
        } catch (SQLException e) {
            System.out.println("Erreur d'execution de la requete !");
            System.exit(1);
        }
        try (ResultSet rs = afficherConcerts.executeQuery()) {
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getInt(3));
            }
        } catch (SQLException e) {
            System.out.println("Erreur d'execution de la requete !");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        GestionConcert gestionConcert = new GestionConcert();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le nom d'un artiste :");
        String artiste = scanner.nextLine();
        gestionConcert.afficherConcerts(artiste);
    }
}
