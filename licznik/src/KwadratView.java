import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class KwadratView extends JFrame {
    private JTextField liczbaText = new JTextField(10);
    private JButton obliczButton = new JButton("Oblicz Kwadrat");
    private JLabel wynikLabel = new JLabel("Wynik: ");

    public KwadratView() {
        super("Kalkulator MCV");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 200);
        this.setLayout(new FlowLayout());

        JPanel panel = new JPanel();
        panel.add(new JLabel("Wprowadź liczbę:"));
        panel.add(liczbaText);
        panel.add(obliczButton);
        panel.add(wynikLabel);

        this.add(panel);
        this.setLocationRelativeTo(null);
    }
    public String getLiczbaWejsciowa() {
        return liczbaText.getText();
    }
    public void setWynik(int wynik) {
        wynikLabel.setText("Wynik: " + wynik);
    }
    public void displayErrorMessage(String komunikatBledu) {
        JOptionPane.showMessageDialog(this, komunikatBledu, "Błąd Wejścia", JOptionPane.ERROR_MESSAGE);
    }
    public void addObliczListener(ActionListener listenForObliczButton) {
        obliczButton.addActionListener(listenForObliczButton);
    }
}