public class AdherentIntrouvableException extends RuntimeException {
    public AdherentIntrouvableException(int id) {
        super("Adhérent introuvable avec id :" + id);
    }
}