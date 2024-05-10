package admin;

import java.util.Scanner;

public class UserController {
    public static void printUserList() {
        UserService.printUserList();
    }

    public static void addUser(Scanner scanner) {
        // Запрос имени пользователя и пароля
        System.out.println("Введите имя пользователя:");
        String username = scanner.nextLine();
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();

        // Добавление нового пользователя с паролем в базу данных
        UserService.addUser(username, password);
        System.out.println("Пользователь " + username + " успешно добавлен.");
    }

    public static void deleteUserById(Scanner scanner) {
        // Вывод списка пользователей с их ID перед удалением
        UserService.printUserListWithIds();

        System.out.print("Введите ID пользователя для удаления: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера после считывания числа

        // Удаление пользователя по его ID
        UserService.deleteUserById(userId);
    }

    public static void deleteUserByUsername(Scanner scanner) {
        System.out.print("Введите имя пользователя для удаления: ");
        String username = scanner.nextLine();

        // Удаление пользователя по его имени
        UserService.deleteUserByUsername(username);
    }
}


