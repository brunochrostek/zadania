import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm {
    private JPanel mainPanel;
    private JTable table1;
    private JButton btnAddTestBook;

    private BookDAO bookDAO = new BookDAO();

    public MainForm() {

        String[] columns = {"Tytuł", "Autor", "Rok"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        table1.setModel(model);


        btnAddTestBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Book testBook = new Book("Mały Książę", "Antoine de Saint-Exupéry", 1943);


                bookDAO.addBook(testBook);


                refreshTable();
            }
        });
    }

    private void refreshTable() {
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);


        for (Book b : bookDAO.getAllBooks()) {
            model.addRow(new Object[]{b.getTitle(), b.getAuthor(), b.getYear()});
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Moja Biblioteka");
        frame.setContentPane(new MainForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}