package org.example;

import authentication.AuthController;
import ordermanagement.OrderController;
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
            System.out.println("4. create order as admin");
            System.out.println("5. create order as client");
            System.out.println("6. delete order");
            System.out.println("7. update order status  ");
//            System.out.println("8. register admin  ");
            System.out.println("9. all order of clients");
            System.out.println("10. my own orders");

            int choice = scanner.nextInt();
            scanner.nextLine();

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
                    orderController.addOrder_Client();
                    break;
                case 6:
                    orderController.deleteOrder_Admin();
                    break;
                case 7:
                    orderController.updateOrderStatus_Admin();
                    break;
//                case 8:
//                    authController.admin_Register();
//                    break;
                case 9:
                    orderController.getAllOrders();
                    break;
                case 10:
                    orderController.getAllClientOrders();
                    break;
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
            }
        }
    }


}

