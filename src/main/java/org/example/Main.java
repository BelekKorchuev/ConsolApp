package org.example;

import authentication.AuthController;
import authentication.AuthService;
import database.DatabaseManager;
import ordermanagement.OrderController;
import ordermanagement.OrderService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AuthController authController = new AuthController();
        OrderController orderController = new OrderController();
        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Войти как администратор");
            System.out.println("2. Войти как клиент");
            System.out.println("3. Зарегистрироваться как клиент");
            System.out.println("0. Выход");
            System.out.println("4. create order");
            System.out.println("5. delete order");
            System.out.println("6. update order status  ");
//            System.out.println("7. register admin  ");
            System.out.println("8. all orders  ");

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
                case 0:
                    System.out.println("До свидания!");
                    System.exit(0);
                    break;
                case 4:
                    orderController.addOrder_Admin();
                    break;
                case 5:
                    orderController.deleteOrder_Admin();
                    break;
                case 6:
                    orderController.updateOrderStatus_Admin();
                    break;
//                case 7:
//                    authController.admin_Register();
//                    break;
                case 8:
                    orderController.getAllOrders();
                    break;
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
            }
        }
    }


}

