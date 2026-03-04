import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Prestation sauna = new Prestation("SAUNA", "Accès sauna", 5.0);
        Prestation coach = new Prestation("COACH", "Séance avec coach", 25.0);
        Prestation serviette = new Prestation("SERVIETTE", "Location serviette", 2.0);

        Seance s1 = new Seance(1, "BodyPump", LocalDateTime.now().plusDays(1), 20);
        Seance s2 = new Seance(2, "Yoga", LocalDateTime.now().plusDays(3), 15);
        Seance s3 = new Seance(3, "CrossFit", LocalDateTime.now().minusDays(1), 10);

        Abonnement basic = new AbonnementBasic("refBASIC1", LocalDate.now(), 6, 30.0);
        Abonnement premium = new AbonnementPremium("refPrem1", LocalDate.now(), 12, 50.0, 5);

        Adherent adherentBasic = new Adherent(1, "Alice", basic);
        Adherent adherentPremium = new Adherent(2, "Bob", premium);

        SalleDeSport salle = new SalleDeSport();
        salle.ajouterAdherent(adherentBasic);
        salle.ajouterAdherent(adherentPremium);
        salle.ajouterSeance(s1);
        salle.ajouterSeance(s2);
        salle.ajouterSeance(s3);

        Reservation r1 = adherentBasic.reserver(s1);
        r1.ajouterPrestation(serviette);

        Reservation r2 = adherentPremium.reserver(s2);
        r2.ajouterPrestation(sauna);
        r2.ajouterPrestation(coach);

        Reservation r3 = adherentPremium.reserver(s3);
        r3.ajouterPrestation(coach);
        r3.annuler();

        System.out.println(adherentBasic);
        System.out.println(adherentPremium);

        for (int i = 0; i < adherentPremium.reservationsFutures().size(); i++) {
            Reservation reservation = adherentPremium.reservationsFutures().get(i);
            System.out.println(reservation);
        }

        for (int i = 0; i < salle.adherentsAvecSauna().size(); i++) {
            Adherent adherent = salle.adherentsAvecSauna().get(i);
            System.out.println(adherent);
        }

        System.out.println(salle.chiffreAffairesPrestations() + " €");

        try {
            salle.trouverAdherent(999);
        } catch (AdherentIntrouvableException e) {
            System.out.println(e.getMessage());
        }

        try {
            String driverClassName = "com.mysql.jdbc.Driver";
            String dbNameRoot = "mysql";
            String login = "root";
            String motdepasse = "Root1414&*";
            String urlRoot = "jdbc:mysql://localhost:3306/" + dbNameRoot + "?useSSL=false&serverTimezone=UTC"; //j'ai mis SSL à false car j'ai zscaler qui bloque sinon

            Class.forName(driverClassName);

            Connection connRoot = DriverManager.getConnection(urlRoot, login, motdepasse);
            Statement stRoot = connRoot.createStatement();
            stRoot.executeUpdate("CREATE DATABASE IF NOT EXISTS fitnesspark");
            connRoot.close();

            String dbName = "fitnesspark";
            String url = "jdbc:mysql://localhost:3306/" + dbName + "?useSSL=false&serverTimezone=UTC";
            Connection conn = DriverManager.getConnection(url, login, motdepasse);

            Statement st = conn.createStatement();

            String sqlAdherent = "CREATE TABLE IF NOT EXISTS Adherent (id INT PRIMARY KEY, nom VARCHAR(100) NOT NULL)";
            st.executeUpdate(sqlAdherent);

            String sqlSeance = "CREATE TABLE IF NOT EXISTS Seance (id INT PRIMARY KEY, nom VARCHAR(100) NOT NULL, dateHeure DATETIME, capaciteMax INT)";
            st.executeUpdate(sqlSeance);

            String sqlPrestation = "CREATE TABLE IF NOT EXISTS Prestation (code VARCHAR(20) PRIMARY KEY, libelle VARCHAR(100) NOT NULL, prix DOUBLE)";
            st.executeUpdate(sqlPrestation);

            String sqlReservation = "CREATE TABLE IF NOT EXISTS Reservation (id INT PRIMARY KEY AUTO_INCREMENT, adherent_id INT, seance_id INT, statut VARCHAR(20), FOREIGN KEY (adherent_id) REFERENCES Adherent(id), FOREIGN KEY (seance_id) REFERENCES Seance(id))";
            st.executeUpdate(sqlReservation);

            String sqlReservationPrestation = "CREATE TABLE IF NOT EXISTS ReservationPrestation (reservation_id INT, prestation_code VARCHAR(20), FOREIGN KEY (reservation_id) REFERENCES Reservation(id), FOREIGN KEY (prestation_code) REFERENCES Prestation(code))";
            st.executeUpdate(sqlReservationPrestation);

            st.executeUpdate("DELETE FROM Adherent");
            st.executeUpdate("INSERT INTO Adherent (id, nom) VALUES (" + adherentBasic.getId() + ", '" + adherentBasic.getNom() + "')");
            st.executeUpdate("INSERT INTO Adherent (id, nom) VALUES (" + adherentPremium.getId() + ", '" + adherentPremium.getNom() + "')");

            ResultSet rs = st.executeQuery("SELECT id, nom FROM Adherent");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                System.out.println("Adherent en base : " + id + " - " + nom);
            }
            rs.close();

            conn.close();
            System.out.println("Connexion MySQL et test JDBC OK");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC MySQL introuvable : " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getMessage());
        }
    }
}