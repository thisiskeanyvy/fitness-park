import java.util.ArrayList;
import java.util.List;

public class Reservation {

    public enum StatutReservation {
        CONFIRMEE,
        ANNULEE
    }

    private Seance seance;
    private List<Prestation> prestations = new ArrayList<>();
    private StatutReservation statut;

    public Reservation(Seance seance) {
        this.seance = seance;
        this.statut = StatutReservation.CONFIRMEE;
    }

    public Seance getSeance() {
        return seance;
    }

    public StatutReservation getStatut() {
        return statut;
    }

    public List<Prestation> getPrestations() {
        return prestations;
    }

    public void ajouterPrestation(Prestation p) {
        if (p != null && statut == StatutReservation.CONFIRMEE) {
            prestations.add(p);
        }
    }

    public double coutPrestations() {
        double total = 0;
        for (int i = 0; i < prestations.size(); i++) {
            Prestation p = prestations.get(i);
            total = total + p.getPrix();
        }
        return total;
    }

    public void annuler() {
        this.statut = StatutReservation.ANNULEE;
    }

    @Override
    public String toString() {
        return "Reservation[seance:" + seance.getNom() + ", nbPrestations:" + prestations.size() + ", statut:" + statut + "]";
    }
}
