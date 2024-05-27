package usermanagement;

import ordermanagement.OrderController;
import servicemanagement.SystemController;

import java.util.Scanner;

public class ClientMenu {

     public static void clientmenu() {
         SystemController systemController = new SystemController();
         OrderController orderController = new OrderController();
         Scanner scanner = new Scanner(System.in);

        while (true) {
            // Отображение меню администратора
            System.out.println("Client menu:");
            System.out.println("1. View service catalog");
            System.out.println("2. Create order");
            System.out.println("3. View all your orders");
            System.out.println("4. Exit");
            System.out.print(">> ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера после считывания числа

            // Обработка выбора администратора
            switch (choice) {
                case 1:
                    systemController.allWashType();
                    break;
                case 2:
                    orderController.addOrder_Client();
                    break;
                case 3:
                    orderController.getAllClientOrders();
                    break;
                case 4:
                    System.out.println("Exiting the client menu.");
                    return;
                default:
                    System.out.println("Incorrect choice. Please try again.");
            }
        }
    }

}
