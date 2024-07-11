import java.sql.*;
import java.util.Scanner;

public class GestionCommandes {

    private Connection conn;
    private PreparedStatement afficherCommandes;

    public GestionCommandes() {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver manquant !");
            System.exit(1);
        }
        try {
            conn = DriverManager.getConnection(url, "postgres", "postgres");

            afficherCommandes = conn.prepareStatement("SELECT * FROM examen.afficher_commandes WHERE nom_client = ?");
        } catch (SQLException e) {
            System.out.println("Erreur de connexion !");
            System.exit(1);
        }
    }

    public void afficherCommandes(String client) {
        try {
            afficherCommandes.setString(1, client);
        } catch (SQLException e) {
            System.out.println("Erreur d'execution de la requete !");
            System.exit(1);
        }
        try (ResultSet rs = afficherCommandes.executeQuery()) {
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getDate(2) + " " + rs.getInt(3));
            }
        } catch (SQLException e) {
            System.out.println("Erreur d'execution de la requete !");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        GestionCommandes gestionCommandes = new GestionCommandes();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le nom du client :");
        String client = scanner.nextLine();
        gestionCommandes.afficherCommandes(client);
    }
}
