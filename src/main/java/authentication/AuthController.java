package authentication;

import java.util.Scanner;

public class AuthController {
    private final Scanner scanner;

    public AuthController() {
        this.scanner = new Scanner(System.in);
    }

    public void admin_Login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        boolean authenticated = AuthService.adminLogin(username, password);
        if (authenticated) {
            System.out.println("Logged in successfully as administrator.");
        } else {
            System.out.println("Authentication error. Incorrect username or password.");
        }
    }

    public void client_Login() {
        System.out.print("Enter username:");
        String username = scanner.nextLine();

        System.out.print("Enter password:");
        String password = scanner.nextLine();

        boolean authenticated = AuthService.clientLogin(username, password);
        if (authenticated) {
            System.out.println("Logged in successfully as a client.");
        } else {
            System.out.println("Authentication error. Incorrect username or password.");
        }
    }

    public void client_Register() {
        System.out.println("Registration:");
        System.out.print("Enter username:");
        String username = scanner.nextLine();

        System.out.print("Enter password:");
        String password = scanner.nextLine();

        boolean authenticated = AuthService.clientRegister(username, password);
        if (authenticated) {
            System.out.println("You have been successfully registered!");
        } else {
            System.out.println("Authentication error. Please try again.");
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