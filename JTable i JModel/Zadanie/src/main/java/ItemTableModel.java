import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ItemTableModel extends AbstractTableModel {

    private final String[] columns = {"ID", "Nazwa", "Ilość", "Kategoria"};
    private final List<Item> items = new ArrayList<>();

    public int getRowCount() { return items.size(); }
    public int getColumnCount() { return columns.length; }
    public String getColumnName(int col) { return columns[col]; }

    public Object getValueAt(int row, int col) {
        Item i = items.get(row);
        return switch (col) {
            case 0 -> i.getId();
            case 1 -> i.getName();
            case 2 -> i.getQuantity();
            case 3 -> i.getCategory();
            default -> null;
        };
    }

    public void addItem(Item item) {
        items.add(item);
        fireTableRowsInserted(items.size() - 1, items.size() - 1);
    }

    public void removeItem(int row) {
        if (row >= 0 && row < items.size()) {
            items.remove(row);
            fireTableRowsDeleted(row, row);
        }
    }
}
