package authentication;
import usermanager.*;
public class AuthService {
    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    // Метод для аутентификации пользователя
    public boolean authenticate(String username, String password) {
        String storedPassword = userService.getUserPassword(username);
        if (storedPassword != null) {
            // Проверка правильности пароля
            return storedPassword.equals(password);
        }
        return false;
    }

    // Метод для регистрации нового пользователя
    public void register(String username, String password) {
        userService.registerUser(username, password);
    }
}
