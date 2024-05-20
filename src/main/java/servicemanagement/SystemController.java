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
        System.out.print("Введите название мойки:");
        String name = scanner.nextLine();

        System.out.println("Введите описание мойки:");
        String description = scanner.nextLine();

        System.out.print("Введите цену мойки:");
        int price = scanner.nextInt();
        scanner.nextLine();

        // Вызов метода для добавления нового вида мойки
        SystemService.addWashType(name, price, description);
        System.out.println("Новый вид мойки успешно добавлен.");
    }

    public void updateWashType() {
        DatabaseManager.displayServices();
        System.out.print("Введите ID мойки, которую нужно изменить:");
        int washTypeId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Введите новое название мойки:");
        String name = scanner.nextLine();

        System.out.println("Введите новое описание мойки:");
        String description = scanner.nextLine();

        System.out.print("Введите новую цену мойки:");
        int price = scanner.nextInt();

        // Вызов метода для изменения вида мойки
        SystemService.updateWashType(washTypeId, name, price, description);
    }

    public void deleteWashType() {
        // Вывод списка моек с их ID перед удалением
        DatabaseManager.displayServices();

        System.out.print("Введите ID мойки для удаления: ");
        int washTypeId = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера после считывания числа

        // Вызов метода для удаления вида мойки по его ID
        SystemService.deleteWashType(washTypeId);
    }

    public void allWashType() {
        System.out.println("---------------------");
        System.out.println(" Все пользователи: ");
        System.out.println("---------------------");
        SystemService.displayAllWashType();
        System.out.println("---------------------");
    }

}
