import javax.swing.*;

public class Magazynform {
    private JPanel mainPanel;
    private JTextField idField;
    private JTextField nameField;
    private JTextField quantityField;
    private JTextField categoryField;
    private JButton addButton;
    private JButton deleteButton;
    private JTable table1;
    private JLabel nazwa1;

    private ItemTableModel model = new ItemTableModel();

    public Magazynform() {

        table1.setModel(model);

        addButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                String category = categoryField.getText();

                Item item = new Item(id, name, quantity, category);
                model.addItem(item);

                clearFields();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        mainPanel,
                        "Ilość i ID muszą być liczbami!",
                        "Błąd",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = table1.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(
                        mainPanel,
                        "Zaznacz wiersz do usunięcia!",
                        "Brak wyboru",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            model.removeItem(selectedRow);
        });
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        quantityField.setText("");
        categoryField.setText("");
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}

