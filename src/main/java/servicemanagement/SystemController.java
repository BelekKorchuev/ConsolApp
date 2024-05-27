package servicemanagement;

import database.DatabaseManager;
import usermanagement.UserService;

import java.util.Scanner;

public class SystemController {
    private final Scanner scanner;

    public SystemController() {
        this.scanner = new Scanner(System.in);
    }

    public void addWashType() {
        System.out.print("Enter car wash name: ");
        String name = scanner.nextLine();

        System.out.println("Enter car wash description:");
        String description = scanner.nextLine();

        System.out.print("Enter car wash price: ");
        int price = scanner.nextInt();
        scanner.nextLine();

        // Вызов метода для добавления нового вида мойки
        SystemService.addWashType(name, price, description);
        System.out.println("Car wash type was successfully added.");
    }

    public void updateWashType() {
        DatabaseManager.displayServices();
        System.out.print("Enter the ID of the car wash to be modified: ");
        int washTypeId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter the new car wash name: ");
        String name = scanner.nextLine();

        System.out.println("Enter the new car wash description:");
        String description = scanner.nextLine();

        System.out.print("Enter the new car wash price: ");
        int price = scanner.nextInt();

        // Вызов метода для изменения вида мойки
        SystemService.updateWashType(washTypeId, name, price, description);
    }

    public void deleteWashType() {
        // Вывод списка моек с их ID перед удалением
        DatabaseManager.displayServices();

        System.out.print("Enter the ID of the car wash to delete: ");
        int washTypeId = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера после считывания числа

        // Вызов метода для удаления вида мойки по его ID
        SystemService.deleteWashType(washTypeId);
    }

    public void allWashType() {
        System.out.println("---------------------");
        System.out.println("All services: ");
        System.out.println("---------------------");
        SystemService.displayAllWashType();
        System.out.println("---------------------");
    }

}
