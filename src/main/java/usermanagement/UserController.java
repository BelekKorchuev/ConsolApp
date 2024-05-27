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
        System.out.println(" All clients: ");
        System.out.println("---------------------");
        UserService.UserListWithIds();
        System.out.println("---------------------");
    }

     public void addClient() {
        System.out.println("Registration:");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        boolean authenticated = AuthService.clientRegister(username, password);
        if (authenticated) {
            System.out.println("Client successfully registered!");
            // Действия после успешного входа администратора
        } else {
            System.out.println("Authentication error. Please try again.");
        }
    }

    public void deleteClient() {
        // Вывод списка пользователей с их ID перед удалением
        UserService.UserListWithIds();

        System.out.print("Enter the user ID for deletion: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        UserService.deleteUserById(userId);
    }

}


