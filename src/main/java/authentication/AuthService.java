package authentication;

import database.DatabaseManager;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthService {

    // Метод для аутентификации пользователя
    public static boolean clientLogin(String username, String password) {
        String storedPassword = DatabaseManager.getUserPassword(username);
        if (storedPassword != null) {
            String encryptedPassword = encryptPassword(password);
            if (storedPassword.equals(encryptedPassword)) {
                int userId = DatabaseManager.getUserId(username);
                Session session = Session.getInstance();
                session.setUserInfo(username, userId);
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

    // метод для аутентификации админа
    public static boolean adminLogin(String username, String password) {
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
    public static boolean clientRegister(String username, String password) {
        boolean isUsernameExists = DatabaseManager.isUsernameExists(username);
        if (!isUsernameExists) {
            String encryptedPassword = encryptPassword(password);
            boolean isPasswordExists = DatabaseManager.isPasswordExists(encryptedPassword);
            if (!isPasswordExists) {
                String encryptedPassword2 = encryptPassword(password);
                // Сохранение пользователя в базе данных
                DatabaseManager.addUser(username, encryptedPassword2);
            } else {
                System.out.println("Такой пароль уже существует.");
                return false;
            }
        } else {
            System.out.println("Такое имя уже существует.");
            return false;
        }
        return true;
    }

    // использовалось для регистрации админа, дальнейшая использование не требуется
//    public static boolean adminRegister(String username, String password) {
//        // Шифрование пароля
//        String encryptedPassword = encryptPassword(password);
//        // Сохранение пользователя в базе данных
//        databaseManager.addUser(username, encryptedPassword);
//        return true;
//    }

    // Метод для шифрования пароля
    private static String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}