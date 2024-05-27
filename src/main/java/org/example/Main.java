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
            System.out.println("Menu:");
            System.out.println("1. Log in as Administrator");
            System.out.println("2. Log in as Client");
            System.out.println("3. Register as Client");
            System.out.println("0. Exit");
            System.out.print(">> ");

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
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Incorrect choice. Please try again.");
            }
        }
    }


}

