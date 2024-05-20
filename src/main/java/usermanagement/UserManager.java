package usermanagement;


import java.util.Scanner;

public class UserManager {
    public static void userMenu() {
        while (true) {
            UserController userController = new UserController();
            Scanner scanner = new Scanner(System.in);

            System.out.println("Меню управления пользователями:");
            System.out.println("1. Показать список пользователей");
            System.out.println("2. Добавить пользователя");
            System.out.println("3. Удалить пользователя по ID");
            System.out.println("4. Назад");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера после считывания числа

            // Обработка выбора управления пользователями
            switch (choice) {
                case 1:
                    userController.printUserList();
                    break;
                case 2:
                    userController.addClient();
                    break;
                case 3:
                    userController.deleteClient();
                    break;
                case 4:
                    System.out.println("Возврат в меню администратора.");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте еще раз.");
            }
        }
    }
}
