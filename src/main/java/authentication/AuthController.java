package authentication;

import java.util.Scanner;

public class AuthController {
    private final Scanner scanner;

    public AuthController() {
        this.scanner = new Scanner(System.in);
    }

    public void admin_Login() {
        System.out.print("Введите имя пользователя:");
        String username = scanner.nextLine();

        System.out.print("Введите пароль:");
        String password = scanner.nextLine();

        boolean authenticated = AuthService.adminLogin(username, password);
        if (authenticated) {
            System.out.println("Вход выполнен успешно как администратор.");
            // Действия после успешного входа администратора
        } else {
            System.out.println("Ошибка аутентификации. Неверное имя пользователя или пароль.");
        }
    }

    public void client_Login() {
        System.out.print("Введите имя пользователя:");
        String username = scanner.nextLine();

        System.out.print("Введите пароль:");
        String password = scanner.nextLine();

        boolean authenticated = AuthService.clientLogin(username, password);
        if (authenticated) {
            System.out.println("Вход выполнен успешно как клиента.");
            // Действия после успешного входа администратора
        } else {
            System.out.println("Ошибка аутентификации. Неверное имя пользователя или пароль.");
        }
    }

    public void client_Register() {
        System.out.println("Регистрация:");
        System.out.print("Введите имя пользователя:");
        String username = scanner.nextLine();

        System.out.print("Введите пароль:");
        String password = scanner.nextLine();

        boolean authenticated = AuthService.clientRegister(username, password);
        if (authenticated) {
            System.out.println("Вы успешно зарегестрированы!\n" +
                    "Для входа нужно еще раз ввести данные.");
            client_Login();
            // Действия после успешного входа администратора
        } else {
            System.out.println("Ошибка аутентификации. Повторите еще раз.");
        }
    }

    //использовалась только для регистрации админа в дальнейшем не будет использоватся
//    public void admin_Register() {
//        System.out.println("Регистрация:");
//        System.out.println("Введите имя пользователя:");
//        String username = scanner.nextLine();
//
//        System.out.println("Введите пароль:");
//        String password = scanner.nextLine();
//
//        boolean authenticated = AuthService.adminRegister(username, password);
//        if (authenticated) {
//            System.out.println("Вы успешно зарегестрированы!\n" +
//                    "Для входа нужно еще раз ввести данные.");
//            admin_Login();
//            // Действия после успешного входа администратора
//        } else {
//            System.out.println("Ошибка аутентификации. Повторите еще раз.");
//        }
//    }
}