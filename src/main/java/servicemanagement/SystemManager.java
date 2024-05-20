package servicemanagement;

import java.util.Scanner;

public class SystemManager {
    public static void serviceMenu() {
        SystemController systemController = new SystemController();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Меню настройки системы:");
            System.out.println("1. Просмотреть все виды услуг");
            System.out.println("2. Добавить вид новой мойки");
            System.out.println("3. Изменить вид мойки");
            System.out.println("4. Удалить вид мойки по ID");
            System.out.println("5. Назад");
            System.out.print("Выберите действие: ");

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
                    System.out.println("Возврат в меню администратора.");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте еще раз.");
            }
        }
    }
}


