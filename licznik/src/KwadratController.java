import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KwadratController {
    private KwadratModel model;
    private KwadratView view;

    public KwadratController(KwadratModel model, KwadratView view) {
        this.model = model;
        this.view = view;
        this.view.addObliczListener(new ObliczKwadratListener());
    }
    class ObliczKwadratListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String tekstWejsciowy = view.getLiczbaWejsciowa();
            int liczba = 0;
            int wynik = 0;

            try {
                liczba = Integer.parseInt(tekstWejsciowy);

                wynik = model.obliczKwadrat(liczba);

                view.setWynik(wynik);

            } catch (NumberFormatException ex) {
                view.displayErrorMessage("Niepoprawny format liczby. Wprowadź liczbę całkowitą.");
                view.setWynik(0);
            }
        }
    }
}