package admin;

import java.util.Scanner;

public class ManageUser {
    public static void manageUsers(Scanner scanner) {
        while (true) {
            // Отображение меню управления пользователями
            System.out.println("Меню управления пользователями:");
            System.out.println("1. Показать список пользователей");
            System.out.println("2. Добавить пользователя");
            System.out.println("3. Удалить пользователя по ID");
            System.out.println("4. Удалить пользователя по имени");
            System.out.println("5. Назад");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера после считывания числа

            // Обработка выбора управления пользователями
            switch (choice) {
                case 1:
                    UserController.printUserList();
                    break;
                case 2:
                    UserController.addUser(scanner);
                    break;
                case 3:
                    UserController.deleteUserById(scanner);
                    break;
                case 4:
                    UserController.deleteUserByUsername(scanner);
                    break;
                case 5:
                    System.out.println("Возврат в главное меню администратора.");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте еще раз.");
            }
        }
    }
}
