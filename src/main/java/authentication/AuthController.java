package authentication;

import java.util.Scanner;

public class AuthController {
    private final AuthService authService;
    private final Scanner scanner;

    public AuthController(AuthService authService) {
        this.authService = authService;
        this.scanner = new Scanner(System.in);
    }

    public void adminLogin() {
        System.out.println("Введите имя пользователя:");
        String username = scanner.nextLine();

        System.out.println("Введите пароль:");
        String password = scanner.nextLine();

        boolean authenticated = authService.adminLogin(username, password);
        if (authenticated) {
            System.out.println("Вход выполнен успешно как администратор.");
            // Действия после успешного входа администратора
        } else {
            System.out.println("Ошибка аутентификации. Неверное имя пользователя или пароль.");
        }
    }
}