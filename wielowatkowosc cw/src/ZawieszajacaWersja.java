import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZawieszajacaWersja extends JFrame {

    private final JLabel statusLabel;
    private final JButton startButton;

    public ZawieszajacaWersja() {
        super("Antywzorzec - Zawieszenie");

        statusLabel = new JLabel("Gotowy. Kliknij Start.");
        startButton = new JButton("Start");

        JPanel panel = new JPanel();
        panel.add(startButton);
        panel.add(statusLabel);

        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(500, 300);

        this.setLocationRelativeTo(null);
        this.setVisible(true);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Blokuję... Proszę czekać!");
                startButton.setEnabled(false);

                try {
                    for (int i = 0; i < 5; i++) {
                        Thread.sleep(1000);
                        statusLabel.setText("Pracuję... " + (i + 1) + "s");
                    }
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    statusLabel.setText("Przerwano.");
                } finally {
                    statusLabel.setText("Zakończono blokadę.");
                    startButton.setEnabled(true);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ZawieszajacaWersja::new);
    }
}