package usermanagement;

import ordermanagement.OrderManager;
import servicemanagement.SystemManager;

import java.util.Scanner;

public class AdminMenu {
    public static void adminmenu() {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Отображение меню администратора
            System.out.println("Administrator menu:");
            System.out.println("1. User management");
            System.out.println("2. Order management");
            System.out.println("3. System settings");
            System.out.println("4. Report generation");
            System.out.println("5. Exit");
            System.out.print(">> ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера после считывания числа

            switch (choice) {
                case 1:
                    UserManager.userMenu();
                    break;
                case 2:
                    OrderManager.orderMenu();
                    break;
                case 3:
                    SystemManager.serviceMenu();
                    break;
                case 4:
                    // отчет;
                    break;
                case 5:
                    System.out.println("Exiting the administrator menu.");
                    return;
                default:
                    System.out.println("Incorrect choice. Please try again");
            }
        }
    }
}

