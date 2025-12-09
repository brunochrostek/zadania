public class UzytkownikModel {

    private static final String PRAWIDLOWY_LOGIN = "admin";
    private static final String PRAWIDLOWE_HASLO = "haslo123";
    private static final int SYMULOWANY_OPÓŹNIENIE_MS = 2500; // 2.5 sekundy

    public boolean walidujLogowanie(String user, String pass) {
        try {
            Thread.sleep(SYMULOWANY_OPÓŹNIENIE_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
        return PRAWIDLOWY_LOGIN.equals(user) && PRAWIDLOWE_HASLO.equals(pass);
    }
}