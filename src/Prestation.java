public class Prestation {
    private String code;
    private String libelle;
    private double prix;

    public Prestation(String code, String libelle, double prix) {
        this.code = code;
        this.libelle = libelle;
        this.prix = prix;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Prestation[code:" + code + ", libelle:" + libelle + ", prix:" + prix + "]";
    }
}
