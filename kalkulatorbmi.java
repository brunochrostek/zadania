import javax.swing.*;
import java.awt.*;

public class kalkulatorbmi extends JFrame {
    private JPanel mainPanel;
    private JTextField wagaText;
    private JTextField wzrostText;
    private JButton obliczButton;
    private JLabel wynikLabel;
    private JLabel wagaLabel;
    private JLabel wzrostLabel;

    public kalkulatorbmi() {
        setTitle("Kalkulator BMI");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,150);
        setLocationRelativeTo(null);

        obliczButton.addActionListener(e -> obliczBMI());
    }

    private void obliczBMI() {
        try {
            double waga = Double.parseDouble(wagaText.getText().replace(",", "."));
            double wzrostCm = Double.parseDouble(wzrostText.getText().replace(",", "."));

            double wzrostM = wzrostCm / 100;
            double bmi = waga / (wzrostM * wzrostM);

            String kategoria = interpretujBMI(bmi);
            wynikLabel.setText(String.format("Twoje BMI: %.2f (%s)", bmi, kategoria));
            wynikLabel.setForeground(new Color(0, 100, 0));

        } catch (NumberFormatException ex) {
            wynikLabel.setText("Błąd: Wpisz poprawne liczby!");
            wynikLabel.setForeground(Color.RED);
        }
    }

    private String interpretujBMI(double bmi) {
        if (bmi < 18.5) return "Niedowaga";
        if (bmi < 25) return "Waga prawidłowa";
        if (bmi < 30) return "Nadwaga";
        return "Otyłość";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            kalkulatorbmi frame = new kalkulatorbmi();
            frame.setVisible(true);
        });
    }
}