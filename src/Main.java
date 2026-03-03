import java.time.LocalDate;
import java.time.LocalDateTime;

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
    }
}