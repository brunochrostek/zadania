import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GlownyView extends JFrame {

    private final JTextField loginField;
    private final JPasswordField passwordField;
    private final JButton loginButton;
    private final JLabel statusLabel;

    public GlownyView() {
            super("Logowanie");

        loginField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Zaloguj");
        statusLabel = new JLabel("Wprowadź dane logowania.");

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        formPanel.add(new JLabel("Nazwa użytkownika:"));
        formPanel.add(loginField);
        formPanel.add(new JLabel("Hasło:"));
        formPanel.add(passwordField);

        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(loginButton, BorderLayout.CENTER);
        mainPanel.add(statusLabel, BorderLayout.SOUTH);

        this.add(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
    }

    public String getLogin() {
        return loginField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void setStatus(String text) {
        statusLabel.setText(text);
    }

    public void setLoginButtonEnabled(boolean enabled) {
        loginButton.setEnabled(enabled);
    }

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GlownyView view = new GlownyView();
            UzytkownikModel model = new UzytkownikModel();
            GlownyController controller = new GlownyController(view, model);
            view.setVisible(true);
        });
    }
}