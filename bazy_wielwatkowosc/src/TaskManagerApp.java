import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManagerApp extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnLoad, btnAdd;
    private JLabel lblStatus;
    private JProgressBar progressBar;

    // Dane połączenia - DOPASUJ DO SWOJEJ BAZY
    private final String DB_URL = "jdbc:mysql://localhost:3306/watkowoscbaza";
    private final String DB_USER = "root";
    private final String DB_PASS = ""; // Twoje hasło

    public TaskManagerApp() {
        setTitle("Menedżer Zadań");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // GUI Setup
        tableModel = new DefaultTableModel(new Object[]{"ID", "Tytuł", "Opis", "Gotowe"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel pnlBottom = new JPanel(new GridLayout(2, 1));
        JPanel pnlButtons = new JPanel();
        btnLoad = new JButton("Wczytaj Zadania");
        btnAdd = new JButton("Dodaj Zadanie");
        lblStatus = new JLabel("Gotowy");
        progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setVisible(false);

        pnlButtons.add(btnLoad);
        pnlButtons.add(btnAdd);
        pnlBottom.add(pnlButtons);
        pnlBottom.add(lblStatus);
        add(pnlBottom, BorderLayout.SOUTH);
        add(progressBar, BorderLayout.NORTH);

        // Obsługa zdarzeń
        btnLoad.addActionListener(e -> loadTasks());
        btnAdd.addActionListener(e -> addTask());
    }

    private void loadTasks() {
        btnLoad.setEnabled(false);
        lblStatus.setText("Ładowanie danych... Proszę czekać.");
        progressBar.setVisible(true);
        new LoadTasksWorker().execute();
    }

    private void addTask() {
        String title = JOptionPane.showInputDialog(this, "Podaj tytuł zadania:");
        if (title != null && !title.isEmpty()) {
            lblStatus.setText("Dodawanie zadania...");
            new AddTaskWorker(title).execute();
        }
    }

    // --- ASYNCHRONICZNE WCZYTYWANIE ---
    class LoadTasksWorker extends SwingWorker<List<Task>, Void> {
        @Override
        protected List<Task> doInBackground() throws Exception {
            Thread.sleep(4000); // Sztuczne opóźnienie
            List<Task> tasks = new ArrayList<>();

            // Rejestracja sterownika (dla pewności w starszych projektach)
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM tasks")) {

                while (rs.next()) {
                    tasks.add(new Task(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getBoolean("is_done")
                    ));
                }
            }
            return tasks;
        }

        @Override
        protected void done() {
            try {
                List<Task> tasks = get();
                tableModel.setRowCount(0);
                for (Task t : tasks) {
                    tableModel.addRow(new Object[]{t.id, t.title, t.description, t.isDone});
                }
                lblStatus.setText("Gotowe. Wczytano " + tasks.size() + " zadań.");
            } catch (Exception e) {
                lblStatus.setText("Błąd: " + e.getMessage());
                e.printStackTrace();
            } finally {
                btnLoad.setEnabled(true);
                progressBar.setVisible(false);
            }
        }
    }

    // --- ASYNCHRONICZNE DODAWANIE ---
    class AddTaskWorker extends SwingWorker<Boolean, Void> {
        private String title;

        public AddTaskWorker(String title) { this.title = title; }

        @Override
        protected Boolean doInBackground() throws Exception {
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                 PreparedStatement pstmt = conn.prepareStatement("INSERT INTO tasks (title, description, is_done) VALUES (?, ?, ?)")) {
                pstmt.setString(1, title);
                pstmt.setString(2, "Opis zadania...");
                pstmt.setBoolean(3, false);
                return pstmt.executeUpdate() > 0;
            }
        }

        @Override
        protected void done() {
            try {
                if (get()) {
                    lblStatus.setText("Zadanie dodane pomyślnie.");
                    loadTasks();
                }
            } catch (Exception e) {
                lblStatus.setText("Błąd zapisu: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TaskManagerApp().setVisible(true));
    }
}