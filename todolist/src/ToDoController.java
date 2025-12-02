import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ToDoController {
    private ZadanieDAO modelDAO;
    private ToDoView view;

    public ToDoController(ZadanieDAO modelDAO, ToDoView view) {
        this.modelDAO = modelDAO;
        this.view = view;

        this.view.addDodajListener(new DodajZadanieListener());
        this.view.addUsunListener(new UsunZadanieListener());

        aktualizujWidok();
    }

    private void aktualizujWidok() {
        try {
            view.ustawListeZadan(modelDAO.pobierzWszystkieZadania());
        } catch (SQLException e) {
            view.displayErrorMessage("Błąd podczas pobierania zadań: " + e.getMessage());
            e.printStackTrace();
        }
    }

    class DodajZadanieListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String tresc = view.getNowaTrescZadania();
            if (tresc != null && !tresc.trim().isEmpty()) {
                try {
                    modelDAO.dodajZadanie(tresc.trim());

                    aktualizujWidok();
                } catch (SQLException ex) {
                    view.displayErrorMessage("Błąd podczas dodawania zadania: " + ex.getMessage());
                }
            }
        }
    }

    class UsunZadanieListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int idDoUsuniecia = view.getWybraneZadanieId();
            if (idDoUsuniecia != -1) {
                try {
                    modelDAO.usunZadanie(idDoUsuniecia);

                    aktualizujWidok();
                } catch (SQLException ex) {
                    view.displayErrorMessage("Błąd podczas usuwania zadania: " + ex.getMessage());
                }
            } else {
                view.displayErrorMessage("Wybierz zadanie do usunięcia.");
            }
        }
    }
}