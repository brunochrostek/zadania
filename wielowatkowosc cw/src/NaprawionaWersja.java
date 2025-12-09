import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class NaprawionaWersja extends JFrame {

    private final JLabel statusLabel;
    private final JButton startButton;

    public NaprawionaWersja() {
        super("Wzorzec - Naprawa");

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
                startButton.setEnabled(false);

                SwingWorker<Void, String> worker = new SwingWorker<>() {

                    @Override
                    protected Void doInBackground() throws Exception {
                        for (int i = 0; i < 5; i++) {
                            Thread.sleep(1000);
                            publish("Pracuję... " + (i + 1) + "s");
                        }
                        return null;
                    }

                    @Override
                    protected void process(List<String> chunks) {
                        String latestStatus = chunks.get(chunks.size() - 1);
                        statusLabel.setText(latestStatus);
                    }

                    @Override
                    protected void done() {
                        statusLabel.setText("Zakończono pomyślnie!");
                        startButton.setEnabled(true);
                    }
                };

                worker.execute();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NaprawionaWersja::new);
    }
}