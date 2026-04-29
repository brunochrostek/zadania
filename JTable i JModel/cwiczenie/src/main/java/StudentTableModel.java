import javax.swing.table.AbstractTableModel;
import java.util.List;

public class StudentTableModel extends AbstractTableModel {

    private final String[] columnNames = {"Imię", "Nazwisko", "Ocena"};
    private final List<Student> students;

    public StudentTableModel(List<Student> students) {
        this.students = students;
    }

    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student s = students.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> s.getFirstName();
            case 1 -> s.getLastName();
            case 2 -> s.getGrade();
            default -> null;
        };
    }
}