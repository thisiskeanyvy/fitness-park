import java.util.ArrayList;
import java.util.List;

public class SalleDeSport {
    private List<Adherent> adherents = new ArrayList<>();
    private List<Seance> seances = new ArrayList<>();

    public void ajouterAdherent(Adherent a) {
        if (a != null) {
            adherents.add(a);
        }
    }

    public void ajouterSeance(Seance s) {
        if (s != null) {
            seances.add(s);
        }
    }

    public List<Seance> seancesDisponibles() {
        return seances;
    }

    public List<Adherent> adherentsAvecSauna() {
        List<Adherent> result = new ArrayList<>();
        for (int i = 0; i < adherents.size(); i++) {
            Adherent adherent = adherents.get(i);
            if (adherent.getAbonnement() != null && adherent.getAbonnement().permetAccesSauna()) {
                result.add(adherent);
            }
        }
        return result;
    }

    public double chiffreAffairesPrestations() {
        double total = 0;
        for (int i = 0; i < adherents.size(); i++) {
            Adherent adherent = adherents.get(i);
            total = total + adherent.depensesTotales();
        }
        return total;
    }

    public Adherent trouverAdherent(int id) {
        for (int i = 0; i < adherents.size(); i++) {
            Adherent adherent = adherents.get(i);
            if (adherent.getId() == id) {
                return adherent;
            }
        }
        throw new AdherentIntrouvableException(id);
    }
}