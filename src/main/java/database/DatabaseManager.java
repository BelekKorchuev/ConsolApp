package database;

import java.sql.*;

public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:sqlite:src/main/resources/mydatabase.db";

    // Метод для подключения к базе данных
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }

    // Метод для создания таблицы пользователей
//    public static void createUserTable() {
//        String sql = "CREATE TABLE IF NOT EXISTS users (username TEXT PRIMARY KEY, password TEXT)";
//        try (Connection conn = connect();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }

    // Метод для добавления нового пользователя в базу данных при регетстрации
    public static void addUser(String username, String password) {
        String sql = "INSERT INTO users(username, password) VALUES(?,?)";
        try (Connection conn = connect();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Метод для получения данных зашифрованного пароля пользователя из базы данных для входа
    public static String getUserPassword(String username) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = connect();
            String sql = "SELECT password FROM users WHERE username = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("password");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    // Админ метод для создания заказа в базе данных
    public static void addOrder(String name, String car_model, String wash_type, String status) {
        try {
            resetAutoIncrement();
        } catch (SQLException e) {
            System.out.println("Ошибка при сбросе автоинкремента: " + e.getMessage());
        }

        String sql = "INSERT INTO orders (customer_name, car_model, wash_type, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, car_model);
            ps.setString(3, wash_type);
            ps.setString(4, status);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Метод для сброса автоинкрементного счетчика при добавлении ногово заказа(вместе с addOrder)
    private static void resetAutoIncrement() throws SQLException {
        String sql = "DELETE FROM sqlite_sequence WHERE name='orders'";
        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.executeUpdate();
        }
    }

    // Метод для получения максимального ID заказа при добавдении заказа или удалении
    public static int getMaxOrderId() {
        String sql = "SELECT MAX(ID) AS max_id FROM orders";
        try (Connection conn = connect();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                return resultSet.getInt("max_id");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении максимального ID: " + e.getMessage());
        }
        // Если таблица пуста или возникла ошибка, возвращаем 0
        return 0;
    }

    // Админ метод для удаления заказа из базы данных
    public static void deleteOrder(int orderId) {
        String sql = "DELETE FROM orders WHERE ID = ?";
        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                // Проверяем, был ли удален последний заказ
                if (orderId != getMaxOrderId()) {
                    // Выполняем смещение списка для оставшихся заказов
                    shiftOrderIdsAfterDeletion(conn, orderId);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Метод для смещения списка после удаления заказа
    private static void shiftOrderIdsAfterDeletion(Connection conn, int deletedOrderId) throws SQLException {
        String sql = "UPDATE orders SET ID = ID - 1 WHERE ID > ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, deletedOrderId);
            ps.executeUpdate();
        }
    }

    // Админ метод для изменнения статуса заказа в базе данных
    public static void updateOrderStatus(int orderId, String newStatus){
        String sql = "UPDATE orders SET status = ? WHERE ID = ?";
        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setInt(2, orderId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());        }
    }
}
