package usermanagement;


import java.util.Scanner;

public class UserManager {
    public static void userMenu() {
        while (true) {
            UserController userController = new UserController();
            Scanner scanner = new Scanner(System.in);

           System.out.println("User management menu:");
            System.out.println("1. Show clients list");
            System.out.println("2. Add client");
            System.out.println("3. Delete client by ID");
            System.out.println("4. Back");
            System.out.print(">> ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера после считывания числа

            // Обработка выбора управления пользователями
            switch (choice) {
                case 1:
                    userController.printUserList();
                    break;
                case 2:
                    userController.addClient();
                    break;
                case 3:
                    userController.deleteClient();
                    break;
                case 4:
                    System.out.println("Return to administrator menu.");
                    return;
                default:
                    System.out.println("Incorrect choice. Please try again.");
            }
        }
    }
}
