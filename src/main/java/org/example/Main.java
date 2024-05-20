package org.example;

import authentication.AuthController;
import usermanagement.AdminMenu;
import usermanagement.ClientMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AuthController authController = new AuthController();
        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Войти как администратор");
            System.out.println("2. Войти как клиент");
            System.out.println("3. Зарегистрироваться как клиент");
            System.out.println("0. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    authController.admin_Login();
                    AdminMenu.adminmenu();
                    break;
                case 2:
                    authController.client_Login();
                    ClientMenu.clientmenu();
                    break;
                case 3:
                    authController.client_Register();
                    break;
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

