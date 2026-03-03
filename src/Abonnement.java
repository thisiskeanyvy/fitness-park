import java.time.LocalDate;

public abstract class Abonnement {
    private String reference;
    private LocalDate dateDebut;
    private int dureeMois;
    private double prixMensuel;

    public Abonnement(String reference, LocalDate dateDebut, int dureeMois, double prixMensuel) {
        this.reference = reference;
        this.dateDebut = dateDebut;
        this.dureeMois = dureeMois;
        this.prixMensuel = prixMensuel;
    }

    public String getReference() {
        return reference;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate dateFin() {
        return dateDebut.plusMonths(dureeMois);
    }

    public double coutTotal() {
        return prixMensuel * dureeMois;
    }

    public int getDureeMois() {
        return dureeMois;
    }

    public double getPrixMensuel() {
        return prixMensuel;
    }

    public abstract boolean permetAccesSauna();

    public abstract int creditsCoachInclus();

    @Override
    public String toString() {
        return "Abonnement[ref:" + reference + ", dateDebut:" + dateDebut + ", dureeMois:" + dureeMois + ", prixMensuel:" + prixMensuel + "]";
    }
}
