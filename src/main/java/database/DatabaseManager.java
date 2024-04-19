package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:sqlite:carwash.db";

    // Метод для подключения к базе данных
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }

    // Метод для создания таблицы пользователей
    public static void createUserTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (username TEXT PRIMARY KEY, password TEXT)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Метод для добавления нового пользователя в базу данных
    public static void addUser(String username, String password) {
        String sql = "INSERT INTO users(username, password) VALUES(?,?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Метод для получения данных пользователя из базы данных
    public static String getUserPassword(String username) {
        String sql = "SELECT password FROM users WHERE username = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("password");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // Метод для закрытия соединения с базой данных
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }


}
