package ordermanagement;

import usermanagement.UserController;

import java.util.Scanner;

public class OrderManager {
    public static void orderMenu() {
        while (true) {
            OrderController orderController = new OrderController();
            Scanner scanner = new Scanner(System.in);

            System.out.println("Orders management menu:");
            System.out.println("1. View all orders");
            System.out.println("2. Add order");
            System.out.println("3. Delete order");
            System.out.println("4. Change order status");
            System.out.println("5. Back");
            System.out.print(">>> ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера после считывания числа

            switch (choice) {
                case 1:
                    orderController.getAllOrders();
                    break;
                case 2:
                    orderController.addOrder_Admin();
                    break;
                case 3:
                    orderController.deleteOrder_Admin();
                    break;
                case 4:
                    orderController.updateOrderStatus_Admin();
                    break;
                case 5:
                    System.out.println("Return to administrator menu.");
                    return;
                default:
                    System.out.println("Incorrect choice. Please try again.");
            }
        }
    }
}
