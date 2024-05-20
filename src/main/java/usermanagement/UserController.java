package usermanagement;

import authentication.AuthService;
import ordermanagement.OrderService;

import java.util.Scanner;


public class UserController {
    private final Scanner scanner;

    public UserController() {
        this.scanner = new Scanner(System.in);
    }

    public void printUserList() {
        System.out.println("---------------------");
        System.out.println(" Все пользователи: ");
        System.out.println("---------------------");
        UserService.UserListWithIds();
        System.out.println("---------------------");
    }

     public void addClient() {
        System.out.println("Регистрация:");
        System.out.print("Введите имя пользователя:");
        String username = scanner.nextLine();

        System.out.print("Введите пароль:");
        String password = scanner.nextLine();

        boolean authenticated = AuthService.clientRegister(username, password);
        if (authenticated) {
            System.out.println("Слиент успешно зарегестрирован!\n" +
                    "Для входа нужно еще раз ввести данные.");
            // Действия после успешного входа администратора
        } else {
            System.out.println("Ошибка аутентификации. Повторите еще раз.");
        }
    }

    public void deleteClient() {
        // Вывод списка пользователей с их ID перед удалением
        UserService.UserListWithIds();

        System.out.print("Введите ID пользователя для удаления: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        UserService.deleteUserById(userId);
    }

}


