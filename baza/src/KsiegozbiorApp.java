import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class KsiegozbiorApp extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JTextField tytulField, autorField, rokField;
    private JButton addButton, deleteButton, updateButton;

    private Connection conn;

    public KsiegozbiorApp() {

        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/biblioteka", "root", "");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Błąd połączenia z bazą: " + e.getMessage(),
                    "Błąd", JOptionPane.ERROR_MESSAGE);
        }
        
        model = new DefaultTableModel(new String[]{"ID", "Tytuł", "Autor", "Rok"}, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(model);
        table.setFillsViewportHeight(true);

        tytulField = new JTextField(20);
        autorField = new JTextField(20);
        rokField = new JTextField(6);

        addButton = new JButton("Dodaj");
        deleteButton = new JButton("Usuń");
        updateButton = new JButton("Aktualizuj");

        setLayout(new BorderLayout(8, 8));
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buildBottomPanel(), BorderLayout.SOUTH);

        addButton.addActionListener(e -> dodajKsiazke());
        deleteButton.addActionListener(e -> usunKsiazke());
        updateButton.addActionListener(e -> aktualizujKsiazke());

        odswiezTabele();

        setTitle("Księgozbiór");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();
        setMinimumSize(new Dimension(720, getHeight()));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel buildBottomPanel() {
        JPanel bottom = new JPanel(new BorderLayout(8, 8));
        bottom.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(new JLabel("Tytuł:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; formPanel.add(tytulField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; formPanel.add(new JLabel("Autor:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; formPanel.add(autorField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; formPanel.add(new JLabel("Rok wydania:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; formPanel.add(rokField, gbc);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 8));
        buttonsPanel.add(addButton);
        buttonsPanel.add(updateButton);
        buttonsPanel.add(deleteButton);

        bottom.add(formPanel, BorderLayout.CENTER);
        bottom.add(buttonsPanel, BorderLayout.SOUTH);
        return bottom;
    }

    private void dodajKsiazke() {
        if (!walidujFormularz()) return;
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO ksiazki (tytul, autor, rok_wydania) VALUES (?, ?, ?)")) {
            ps.setString(1, tytulField.getText().trim());
            ps.setString(2, autorField.getText().trim());
            ps.setInt(3, Integer.parseInt(rokField.getText().trim()));
            ps.executeUpdate();
            czyscFormularz();
            odswiezTabele();
        } catch (SQLException ex) {
            pokazBlad(ex);
        }
    }

    private void usunKsiazke() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Wybierz rekord do usunięcia.");
            return;
        }
        int id = (int) model.getValueAt(selectedRow, 0);
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM ksiazki WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
            odswiezTabele();
        } catch (SQLException ex) {
            pokazBlad(ex);
        }
    }

    private void aktualizujKsiazke() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Wybierz rekord do aktualizacji.");
            return;
        }
        if (!walidujFormularz()) return;
        int id = (int) model.getValueAt(selectedRow, 0);
        try (PreparedStatement ps = conn.prepareStatement(
                "UPDATE ksiazki SET tytul=?, autor=?, rok_wydania=? WHERE id=?")) {
            ps.setString(1, tytulField.getText().trim());
            ps.setString(2, autorField.getText().trim());
            ps.setInt(3, Integer.parseInt(rokField.getText().trim()));
            ps.setInt(4, id);
            ps.executeUpdate();
            odswiezTabele();
        } catch (SQLException ex) {
            pokazBlad(ex);
        }
    }

    private void odswiezTabele() {
        model.setRowCount(0);
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM ksiazki ORDER BY id")) {
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("tytul"),
                        rs.getString("autor"),
                        rs.getInt("rok_wydania")
                });
            }
        } catch (SQLException ex) {
            pokazBlad(ex);
        }
    }

    private boolean walidujFormularz() {
        String tytul = tytulField.getText().trim();
        String autor = autorField.getText().trim();
        String rokText = rokField.getText().trim();

        if (tytul.isEmpty() || autor.isEmpty() || rokText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Wypełnij wszystkie pola.");
            return false;
        }
        try {
            int rok = Integer.parseInt(rokText);
            if (rok < 0 || rok > 2100) {
                JOptionPane.showMessageDialog(this, "Podaj prawidłowy rok (0–2100).");
                return false;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Rok musi być liczbą całkowitą.");
            return false;
        }
        return true;
    }

    private void czyscFormularz() {
        tytulField.setText("");
        autorField.setText("");
        rokField.setText("");
    }

    private void pokazBlad(Exception ex) {
        JOptionPane.showMessageDialog(this, "Wystąpił błąd: " + ex.getMessage(),
                "Błąd", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(KsiegozbiorApp::new);
    }
}
