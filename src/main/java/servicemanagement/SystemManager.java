package servicemanagement;

import java.util.Scanner;

public class SystemManager {
    public static void serviceMenu() {
        SystemController systemController = new SystemController();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("System settings menu:");
            System.out.println("1. View all types of services");
            System.out.println("2. Add a new car wash type");
            System.out.println("3. Modify a car wash type");
            System.out.println("4. Delete a car wash type by ID");
            System.out.println("5. Back");
            System.out.print(">>> ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера после считывания числа

            switch (choice) {
                case 1:
                    systemController.allWashType();
                    break;
                case 2:
                    systemController.addWashType();
                    break;
                case 3:
                    systemController.updateWashType();
                    break;
                case 4:
                    systemController.deleteWashType();
                    break;
                case 5:
                    System.out.println("Return to administrator menu.");
                    return;
                default:
                    System.out.println("Incorrect choice. Please try again.");
            }
        }
    }
}


