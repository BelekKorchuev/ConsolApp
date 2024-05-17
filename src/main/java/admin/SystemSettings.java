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
                    SystemController.addWashType(scanner);
                    break;
                case 2:
                    SystemController.updateWashType(scanner);
                    break;
                case 3:
                    SystemController.deleteWashTypeById(scanner);
                    break;
                case 4:
                    System.out.println("Возврат в главное меню администратора.");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте еще раз.");
            }
        }
    }
}


