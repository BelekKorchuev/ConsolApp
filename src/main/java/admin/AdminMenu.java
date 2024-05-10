package admin;

import java.util.Scanner;

public class AdminMenu {
    public static void main(String[] args) {
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

            // Обработка выбора администратора
            switch (choice) {
                case 1:
                    ManageUser.manageUsers(scanner);
                    break;
                case 2:
                    // управление заказами;
                    break;
                case 3:
                    // настройки системы;
                    SystemSettings.manageSystemSettings(scanner);
                    break;
                case 4:
                    // отчет;
                    break;
                case 5:
                    System.out.println("Выход из меню администратора.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Неверный выбор. Попробуйте еще раз.");
            }
        }
    }
}

