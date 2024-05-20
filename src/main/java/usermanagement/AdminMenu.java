package usermanagement;

import ordermanagement.OrderManager;
import servicemanagement.SystemManager;

import java.util.Scanner;

public class AdminMenu {
    public static void adminmenu() {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Отображение меню администратора
            System.out.println("Меню администратора:");
            System.out.println("1. Управление пользователями");
            System.out.println("2. Управление заказами");
            System.out.println("3. Настройки системы");
            System.out.println("4. Генерация отчетов");
            System.out.println("5. Выход");
            System.out.print("Выберите действие: ");

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
                    System.out.println("Выход из меню администратора.");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте еще раз.");
            }
        }
    }
}

