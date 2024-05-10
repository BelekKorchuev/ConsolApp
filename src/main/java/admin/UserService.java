package admin;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class UserService {
    private static final String DATABASE_URL = "jdbc:sqlite:src/main/resources/mydatabase.db";

    public static void printUserList() {
        database.DatabaseManager.printUserList();
    }

    public static void printUserListWithIds() {
        database.DatabaseManager.printUserListWithIds();
    }

    public static void deleteUserByUsername(String username) {
        database.DatabaseManager.deleteUserByUsername(username);
    }

    public static void deleteUserById(int userId) {
        database.DatabaseManager.deleteUserById(userId);
    }

    public static void addUser(String username, String password) {
        String hashedPassword = hashPassword(password);
        database.DatabaseManager.addUser(username, hashedPassword);
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Ошибка при хешировании пароля: " + e.getMessage());
            return null;
        }
    }
}

