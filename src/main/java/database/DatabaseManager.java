package database;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    // клиент метод для создания заказа в базе
    public static void addOrderClient(String name, int name_id, String car_model, int wash_type_id, String created_at) {
        try {
            resetAutoIncrement();
        } catch (SQLException e) {
            System.out.println("Ошибка при сбросе автоинкремента: " + e.getMessage());
        }

        String sql = "INSERT INTO orders (customer_name, customer_id, car_model, wash_type_id, created_at) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, name_id);
            ps.setString(3, car_model);
            ps.setInt(4, wash_type_id);
            ps.setString(5, created_at);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

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

    // метод для проверки нету ли уже таких паролей в базе
    public static boolean isPasswordExists(String password) {
        String sql = "SELECT COUNT(*) AS count FROM users WHERE password = ?";
        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, password);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0; // Возвращает true, если пароль уже существует в базе данных
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при проверке существующего пароля: " + e.getMessage());
        }
        return false; // Возвращает false в случае ошибки или если пароль не найден
    }

    // метод для проверки нету ли уже таких имен в базе
    public static boolean isUsernameExists(String username) {
        String sql = "SELECT COUNT(*) AS count FROM users WHERE username = ?";
        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int count = rs.getInt("count");
                return count > 0; // Возвращает true, если пароль уже существует в базе данных
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при проверке существующего имени клиента: " + e.getMessage());
        }
        return false; // Возвращает false в случае ошибки или если пароль не найден
    }

    // получение id пользователя после входа
    public static int getUserId(String username) {
        String sql = "SELECT id FROM users WHERE username = ?";
        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении ID пользователя: " + e.getMessage());
        } return -1;
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
    public static void addOrderAdmin(String name, int name_id, String car_model, int wash_type_id, String status, String created_at) {
        try {
            resetAutoIncrement();
        } catch (SQLException e) {
            System.out.println("Ошибка при сбросе автоинкремента: " + e.getMessage());
        }

        String sql = "INSERT INTO orders (customer_name, customer_id, car_model, wash_type_id, status, created_at) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, name_id);
            ps.setString(3, car_model);
            ps.setInt(4, wash_type_id);
            ps.setString(5, status);
            ps.setString(6, created_at);
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

    // Админ метод для удаления заказа из базы данных
    public static void deleteOrder(int orderId) {
        String sql = "DELETE FROM orders WHERE id = ?";
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

    // Метод для смещения списка после удаления заказа
    private static void shiftOrderIdsAfterDeletion(Connection conn, int deletedOrderId) throws SQLException {
        String sql = "UPDATE orders SET id = id - 1 WHERE id > ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, deletedOrderId);
            ps.executeUpdate();
        }
    }

    // Админ метод для изменнения статуса заказа в базе данных
    public static void updateOrderStatus(int orderId, String newStatus){
        String sql = "UPDATE orders SET status = ? WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setInt(2, orderId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());        }
    }



    // метод для вывода данных id и username из таблицы users
    public static void displayUsers() {
        String sql = "SELECT id, username FROM users";
        try (Connection conn = connect();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                System.out.println("| ID:" + id + ", имя: " + username + "|");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении списка пользователей: " + e.getMessage());
        }
    }

    // метод для вывода данных о сущ. услуг из таблицы services
    public static void displayServices() {
        String sql = "SELECT id, name, discrip, price FROM services";
        try (Connection conn = connect();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("discrip");
                double price = resultSet.getDouble("price");
                System.out.println("| ID:" + id + " || Название: " + name + ", || Цена:" + price+ " сом | \n| Описание: " + description + "| ");
                System.out.println("------------------------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении списка услуг: " + e.getMessage());
        }
    }

    // метод для вывода списка заказов для удаления и изменения статуса
    public static void displayOrders_Admin() {
        String sql = "SELECT id, customer_name, car_model, status, created_at FROM orders";
        try (Connection conn = connect();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String customer_name = resultSet.getString("customer_name");
                String car_model = resultSet.getString("car_model");
                String status = resultSet.getString("status");
                String created_at = resultSet.getString("created_at");
                System.out.println("| ID:" + id + " || Покупатель: " + customer_name + " || Модель машини: " + car_model + " || Статус выполнения: " + status + " || Создан в: " + created_at);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении списка заказов");
        }
    }

    // метод для вывода заказов(соединение 3 таблиц) для админа
    public static void displayOrdersDetails() {
    String sql = "SELECT users.username, orders.car_model, services.name, orders.status, orders.created_at " +
                 "FROM orders " +
                 "LEFT JOIN users ON orders.customer_id = users.id " +
                 "LEFT JOIN services ON orders.wash_type_id = services.id";
    try (Connection conn = connect();
         Statement statement = conn.createStatement();
         ResultSet resultSet = statement.executeQuery(sql)) {
        while (resultSet.next()) {
            String username = resultSet.getString("username");
            String carModel = resultSet.getString("car_model");
            String serviceName = resultSet.getString("name");
            String status = resultSet.getString("status");
            String createdAt = resultSet.getString("created_at");
            System.out.println("| Клиент:" + username + " || Модель машины: " + carModel + " || Сервис: " + serviceName + " || Статус: " + status + " || Создано: " + createdAt + "|");
        }
    } catch (SQLException e) {
        System.out.println("Ошибка при получении деталей заказов: " + e.getMessage());
    }
}

    // метод для вывода списка собственных заказов клиента
    public static void displayOrders_Client(int customer_id) {
        String sql = "SELECT customer_name, car_model, status, created_at FROM orders WHERE customer_id = ?";
        try (Connection conn = connect();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customer_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String customer_name = rs.getString("customer_name");
                String car_model = rs.getString("car_model");
                String status = rs.getString("status");
                String created_at = rs.getString("created_at");
                System.out.println("| Покупатель: " + customer_name + " || Модель машини: " + car_model + " || Статус выполнения: " + status + " || Создан в: " + created_at);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении списка заказов");
        }
    }


    // Метод для удаления пользователя из базы данных по его имени
    public static void deleteUserByUsername(String username) {
        String sql = "DELETE FROM users WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении пользователя: " + e.getMessage());
        }
    }

    public static void deleteUserById(int userId) {
        // Вывод списка пользователей с их ID перед удалением
        printUserListWithIds();

        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Пользователь с ID " + userId + " успешно удален.");
            } else {
                System.out.println("Пользователь с ID " + userId + " не найден.");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении пользователя: " + e.getMessage());
        }
    }

    public static void printUserListWithIds() {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conn = connect();
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT id, username FROM users");

            System.out.println("Список пользователей с их ID:");
            while (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String username = resultSet.getString("username");
                System.out.println("ID: " + userId + ", Имя пользователя: " + username);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении списка пользователей: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Ошибка при закрытии ресурсов: " + e.getMessage());
            }
        }
    }


    public static void printUserList() {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conn = connect();
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT id, username FROM users");

            while (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String username = resultSet.getString("username");
                System.out.println("ID: " + userId + ", Имя пользователя: " + username);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении списка пользователей: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Ошибка при закрытии ресурсов: " + e.getMessage());
            }
        }
    }




    // Метод для добавления нового вида мойки
    public static void addWashType(String washType, int price, String description) {
        String sql = "INSERT INTO services (name, price, discrip) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, washType);
            ps.setInt(2, price);
            ps.setString(3, description);
            ps.executeUpdate();
            System.out.println("Новый вид мойки успешно добавлен в базу данных.");
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении вида мойки: " + e.getMessage());
        }
    }

    // Метод для изменения существующего вида мойки
    public static void updateWashType(int washTypeId, String newWashType, int newPrice, String newDescription) {
        String sql = "UPDATE services SET name = ?, price = ?, discrip = ?  WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newWashType);
            ps.setInt(2, newPrice);
            ps.setString(3, newDescription);
            ps.setInt(4, washTypeId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Вид мойки успешно изменен.");
            } else {
                System.out.println("Вид мойки с указанным ID не найден.");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при изменении вида мойки: " + e.getMessage());
        }
    }


    public static void printWashTypesWithIds() {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conn = connect();
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT id, name FROM services");

            System.out.println("Список видов моек с их ID:");
            while (resultSet.next()) {
                int washTypeId = resultSet.getInt("id");
                String washTypeName = resultSet.getString("name");
                System.out.println("ID: " + washTypeId + ", Название: " + washTypeName);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении списка видов моек: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Ошибка при закрытии ресурсов: " + e.getMessage());
            }
        }
    }

    public static void deleteWashTypeById(int washTypeId) {
        String sql = "DELETE FROM services WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, washTypeId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Вид мойки с ID " + washTypeId + " успешно удален.");
            } else {
                System.out.println("Вид мойки с ID " + washTypeId + " не найден.");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении вида мойки: " + e.getMessage());
        }
    }


}
