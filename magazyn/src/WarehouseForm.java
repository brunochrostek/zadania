import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WarehouseForm {
    private JPanel mainPanel;
    private JTable tableProducts;
    private JButton btnAdd;
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtQuantity;
    private JTextField txtPrice;
    private JLabel lblTotal;

    private ProductDAO productDAO = new ProductDAO();

    public WarehouseForm() {

        String[] columns = {"ID", "Nazwa", "Ilość", "Cena"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        tableProducts.setModel(model);


        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int id = Integer.parseInt(txtId.getText());
                String name = txtName.getText();
                int quantity = Integer.parseInt(txtQuantity.getText());
                double price = Double.parseDouble(txtPrice.getText());

                Product p = new Product(id, name, quantity, price);
                productDAO.addProduct(p);


                refreshTable();
                refreshTotal();
            }
        });
    }

    private void refreshTable() {
        DefaultTableModel model = (DefaultTableModel) tableProducts.getModel();
        model.setRowCount(0);

        for (Product p : productDAO.getProducts()) {
            model.addRow(new Object[]{
                    p.getId(),
                    p.getName(),
                    p.getQuantity(),
                    p.getPrice()
            });
        }
    }

    private void refreshTotal() {
        double total = productDAO.getTotalValue();
        lblTotal.setText("Łączna wartość magazynu: " + total + " zł");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("System Zarządzania Magazynem");
        frame.setContentPane(new WarehouseForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}