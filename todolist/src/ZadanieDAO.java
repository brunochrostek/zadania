import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ZadanieDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/lista";
    // Zmień na swoje dane logowania
    private static final String USER = "root";
    private static final String PASS = "";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public List<Zadanie> pobierzWszystkieZadania() throws SQLException {
        List<Zadanie> zadania = new ArrayList<>();
        // Użycie try-with-resources automatycznie zamknie Statement i ResultSet
        String sql = "SELECT id, tresc, status FROM zadania";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String tresc = rs.getString("tresc");
                boolean status = rs.getBoolean("status");
                zadania.add(new Zadanie(id, tresc, status));
            }
        }
        return zadania;
    }

    public void dodajZadanie(String tresc) throws SQLException {
        String sql = "INSERT INTO zadania (tresc, status) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tresc);
            pstmt.setBoolean(2, false);
            pstmt.executeUpdate();
        }
    }

    public void usunZadanie(int id) throws SQLException {
        String sql = "DELETE FROM zadania WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public void zmienStatusZadania(int id, boolean nowyStatus) throws SQLException {
        String sql = "UPDATE zadania SET status = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBoolean(1, nowyStatus);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        }
    }
}