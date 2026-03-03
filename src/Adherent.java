import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Adherent {
    private int id;
    private String nom;
    private Abonnement abonnement;
    private List<Reservation> reservations = new ArrayList<>();

    public Adherent(int id, String nom, Abonnement abonnement) {
        this.id = id;
        this.nom = nom;
        this.abonnement = abonnement;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Abonnement getAbonnement() {
        return abonnement;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public Reservation reserver(Seance s) {
        Reservation reservation = new Reservation(s);
        reservations.add(reservation);
        return reservation;
    }

    public double depensesTotales() {
        double total = 0;
        for (int i = 0; i < reservations.size(); i++) {
            Reservation reservation = reservations.get(i);
            if (reservation.getStatut() == Reservation.StatutReservation.CONFIRMEE) {
                total = total + reservation.coutPrestations();
            }
        }
        return total;
    }

    public List<Reservation> reservationsFutures() {
        LocalDateTime maintenant = LocalDateTime.now();
        List<Reservation> futures = new ArrayList<>();
        for (int i = 0; i < reservations.size(); i++) {
            Reservation reservation = reservations.get(i);
            if (reservation.getSeance().getDateHeure().isAfter(maintenant)
                    && reservation.getStatut() == Reservation.StatutReservation.CONFIRMEE) {
                futures.add(reservation);
            }
        }
        return futures;
    }

    @Override
    public String toString() {
        return "Adherent[id:" + id + ", nom:" + nom + ", abonnement:" + abonnement + "]";
    }
}
