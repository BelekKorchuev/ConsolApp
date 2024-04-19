package org.example;

import authentication.AuthController;
import authentication.AuthService;
import database.DatabaseManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager(); // Создание экземпляра DatabaseManager
        AuthService authService = new AuthService(databaseManager);
        AuthController authController = new AuthController(authService);
        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Войти как администратор");
            System.out.println("2. Войти как клиент");
            System.out.println("3. Зарегистрироваться как клиент");
            System.out.println("4. Зарегистрироваться как admin");
            System.out.println("0. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера после ввода числа

            switch (choice) {
                case 1:
                    authController.admin_Login();
                    break;
                case 2:
                    authController.client_Login();
                    break;
                case 3:
                    authController.client_Register();
                    break;
                case 4:
                    authController.admin_Register();
                case 0:
                    System.out.println("До свидания!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
            }
        }
    }


}

