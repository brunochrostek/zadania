import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            ZadanieDAO model = new ZadanieDAO();

            ToDoView view = new ToDoView();

            ToDoController controller = new ToDoController(model, view);

            view.setVisible(true);
        });
    }
}