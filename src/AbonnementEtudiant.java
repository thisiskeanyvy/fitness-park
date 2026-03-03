import java.time.LocalDate;

public class AbonnementEtudiant extends Abonnement {
    private double reductionPourcent;

    public AbonnementEtudiant(String reference, LocalDate dateDebut, int dureeMois, double prixMensuel, double reductionPourcent) {
        super(reference, dateDebut, dureeMois, prixMensuel);
        this.reductionPourcent = reductionPourcent;
    }

    public double getReductionPourcent() {
        return reductionPourcent;
    }

    @Override
    public double coutTotal() {
        double totalSansReduction = super.coutTotal();
        return totalSansReduction * (1 - reductionPourcent / 100.0);
    }

    @Override
    public boolean permetAccesSauna() {
        return false;
    }

    @Override
    public int creditsCoachInclus() {
        return 0;
    }
}
