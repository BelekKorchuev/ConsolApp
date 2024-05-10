package admin;

import java.util.Scanner;

public class SystemSettings {
    public static void manageSystemSettings(Scanner scanner) {
        while (true) {
            // Отображение меню настройки системы
            System.out.println("Меню настройки системы:");
            System.out.println("1. Добавить вид новой мойки");
            System.out.println("2. Изменить вид мойки");
            System.out.println("3. Удалить вид мойки по ID");
            System.out.println("4. Назад");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера после считывания числа

            // Обработка выбора настройки системы
            switch (choice) {
                case 1:
                    addWashType(scanner);
                    break;
                case 2:
                    updateWashType(scanner);
                    break;
                case 3:
                    deleteWashTypeById(scanner);
                    break;
                case 4:
                    System.out.println("Возврат в главное меню администратора.");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте еще раз.");
            }
        }
    }

    public static void addWashType(Scanner scanner) {
        System.out.println("Введите название мойки:");
        String name = scanner.nextLine();
        System.out.println("Введите описание мойки:");
        String description = scanner.nextLine();
        System.out.println("Введите цену мойки:");
        double price = scanner.nextDouble();

        // Вызов метода для добавления нового вида мойки
        database.DatabaseManager.addWashType(name , price , description);
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
        double price = scanner.nextDouble();

        // Вызов метода для изменения вида мойки
        database.DatabaseManager.updateWashType(washTypeId, name, price , description );
        System.out.println("Вид мойки успешно изменен.");
    }

    public static void deleteWashTypeById(Scanner scanner) {
        // Вывод списка моек с их ID перед удалением
        database.DatabaseManager.printWashTypesWithIds();

        System.out.print("Введите ID мойки для удаления: ");
        int washTypeId = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера после считывания числа

        // Вызов метода для удаления вида мойки по его ID
        database.DatabaseManager.deleteWashTypeById(washTypeId);
    }
}


