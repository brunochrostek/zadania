import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ToDoView extends JFrame {
    private JTextField trescZadaniaText = new JTextField(20);
    private JButton dodajButton = new JButton("Dodaj");
    private JButton usunButton = new JButton("Usuń");

    private DefaultTableModel tableModel;
    private JTable zadaniaTable;

    public ToDoView() {
        super("Aplikacja To-Do (MariaDB MVC)");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 450);
        this.setLayout(new BorderLayout(10, 10));

        String[] columnNames = {"ID", "Treść Zadania", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        zadaniaTable = new JTable(tableModel);

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Nowe Zadanie:"));
        inputPanel.add(trescZadaniaText);
        inputPanel.add(dodajButton);
        inputPanel.add(usunButton);

        this.add(inputPanel, BorderLayout.NORTH);
        this.add(new JScrollPane(zadaniaTable), BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
    }

    public String getNowaTrescZadania() {
        String tresc = trescZadaniaText.getText();
        trescZadaniaText.setText("");
        return tresc;
    }

    public int getWybraneZadanieId() {
        int selectedRow = zadaniaTable.getSelectedRow();
        if (selectedRow != -1) {

            return (Integer) tableModel.getValueAt(selectedRow, 0);
        }
        return -1;
    }

    public void ustawListeZadan(List<Zadanie> zadania) {
        tableModel.setRowCount(0);
        for (Zadanie z : zadania) {
            tableModel.addRow(new Object[]{z.getId(), z.getTresc(), z.isStatus() ? "ZROBIONE" : "DO ZROBIENIA"});
        }
    }

    public void addDodajListener(ActionListener listenForDodaj) {
        dodajButton.addActionListener(listenForDodaj);
    }

    public void addUsunListener(ActionListener listenForUsun) {
        usunButton.addActionListener(listenForUsun);
    }

    public void displayErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Błąd Bazy Danych", JOptionPane.ERROR_MESSAGE);
    }
}