import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class StudentApp extends JFrame {

    public StudentApp() {
        setTitle("Lista studentów");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        List<Student> students = new ArrayList<>();
        students.add(new Student("Jan", "Kowalski", 4));
        students.add(new Student("Anna", "Nowak", 5));
        students.add(new Student("Piotr", "Zieliński", 3));

        StudentTableModel model = new StudentTableModel(students);

        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentApp().setVisible(true);
        });
    }
}