package admin;

import java.util.Scanner;

public class SystemController {
    public static void addWashType(Scanner scanner) {
        System.out.println("Введите название мойки:");
        String name = scanner.nextLine();
        System.out.println("Введите описание мойки:");
        String description = scanner.nextLine();
        System.out.println("Введите цену мойки:");
        int price = scanner.nextInt();

        // Вызов метода для добавления нового вида мойки
        SystemService.addWashType(name , price , description);
        System.out.println("Новый вид мойки успешно добавлен.");
    }

    public static void updateWashType(Scanner scanner) {
        System.out.println("Введите ID мойки, которую нужно изменить:");
        int washTypeId = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера после считывания числа

        System.out.println("Введите новое название мойки:");
        String name = scanner.nextLine();
        System.out.println("Введите новое описание мойки:");
        String description = scanner.nextLine();
        System.out.println("Введите новую цену мойки:");
        int price = scanner.nextInt();

        // Вызов метода для изменения вида мойки
        SystemService.updateWashType(washTypeId, name, price , description );
        System.out.println("Вид мойки успешно изменен.");
    }

    public static void deleteWashTypeById(Scanner scanner) {
        // Вывод списка моек с их ID перед удалением
        database.DatabaseManager.printWashTypesWithIds();

        System.out.print("Введите ID мойки для удаления: ");
        int washTypeId = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера после считывания числа

        // Вызов метода для удаления вида мойки по его ID
        SystemService.deleteWashTypeById(washTypeId);
    }
}
