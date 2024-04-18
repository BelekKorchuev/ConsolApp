package usermanagement;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


public class UserService {
    private final Map<String, String> userDatabase; // Замените на вашу базу данных

    public UserService() {
        this.userDatabase = new HashMap<>();
    }

    // Метод для регистрации нового пользователя
    public void registerUser(String username, String password) {
        // Шифрование пароля
        String encryptedPassword = encryptPassword(password);
        // Сохранение пользователя в базе данных
        userDatabase.put(username, encryptedPassword);
        System.out.println("Пользователь успешно зарегистрирован.");
    }

    // Метод для входа пользователя
    public boolean loginUser(String username, String password) {
        // Получаем хэшированный пароль из базы данных
        String storedPassword = userDatabase.get(username);
        if (storedPassword != null) {
            // Шифруем введенный пароль и сравниваем с хэшированным паролем из базы данных
            String encryptedPassword = encryptPassword(password);
            return storedPassword.equals(encryptedPassword);
        }
        return false;
    }

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
