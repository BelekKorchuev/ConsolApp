package authentication;

import database.DatabaseManager;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthService {
    private final DatabaseManager databaseManager;

    public AuthService(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    // Метод для аутентификации пользователя
    public boolean clientLogin(String username, String password) {

        String storedPassword = DatabaseManager.getUserPassword(username);
        String encryptedPassword = encryptPassword(password);
        if (storedPassword != null) {
            // Проверка правильности пароля
            return storedPassword.equals(encryptedPassword);
        } else {
            System.out.println("Пользователь с таким именем не существует.");
            return false;
        }
    }

    // метод для аутентификации админа
    public boolean adminLogin(String username, String password) {
        // Получаем хэшированный пароль администратора из базы данных
        String encryptedPassword = encryptPassword(password);
        String storedPassword = DatabaseManager.getUserPassword(username);
        if (storedPassword != null) {
            // Проверяем правильность введенного пароля
            if (storedPassword.equals(encryptedPassword)){
                return true;
            } else {
            System.out.println("Неверный пароль.");
            return false;
            }

        } else {
            System.out.println("Пользователь с таким именем не существует.");
            return false;
        }
    }

    //Метод для регистрации нового пользователя
    public boolean clientRegister(String username, String password) {
        // Шифрование пароля
        String encryptedPassword = encryptPassword(password);
//         Сохранение пользователя в базе данных
        DatabaseManager.addUser(username, encryptedPassword);
        return true;
    }

    // использовалось для регистрации админа, дальнейшая использование не требуется
//    public boolean adminRegister(String username, String password) {
//        // Шифрование пароля
//        String encryptedPassword = encryptPassword(password);
//        // Сохранение пользователя в базе данных
//        DatabaseManager.addUser(username, encryptedPassword);
//        return true;
//    }

    // Метод для шифрования пароля
    private String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}