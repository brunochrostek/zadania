import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class StudentForm {
    private JPanel mainPanel;
    private JTable table1;
    private JScrollPane scrollPane1;

    public StudentForm() {
        // Dane testowe
        List<Student> students = new ArrayList<>();
        students.add(new Student("Jan", "Kowalski", 4));
        students.add(new Student("Anna", "Nowak", 5));
        students.add(new Student("Piotr", "Zieliński", 3));

        // Podpięcie modelu do JTable
        StudentTableModel model = new StudentTableModel(students);
        table1.setModel(model);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}