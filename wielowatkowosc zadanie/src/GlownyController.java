import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

public class GlownyController implements ActionListener {

    private final GlownyView view;
    private final UzytkownikModel model;

    public GlownyController(GlownyView view, UzytkownikModel model) {
        this.view = view;
        this.model = model;

        this.view.addLoginListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Zaloguj")) {
            startLoginWorker();
        }
    }

    private void startLoginWorker() {
        final String login = view.getLogin();
        final String password = view.getPassword();

        SwingWorker<Boolean, Void> loginWorker = new SwingWorker<>() {

            @Override
            protected Boolean doInBackground() throws Exception {

                SwingUtilities.invokeLater(() -> {
                    view.setLoginButtonEnabled(false);
                    view.setStatus("Trwa weryfikacja danych...");
                });

                return model.walidujLogowanie(login, password);
            }

            @Override
            protected void done() {
                try {
                    view.setLoginButtonEnabled(true);

                    boolean result = get();

                    if (result) {
                        view.setStatus("Logowanie pomyślne!");
                    } else {
                        view.setStatus("Błędny login lub hasło!");
                    }

                } catch (InterruptedException | ExecutionException ex) {
                    view.setStatus("Błąd podczas logowania: " + ex.getMessage());
                }
            }
        };

        loginWorker.execute();
    }
}