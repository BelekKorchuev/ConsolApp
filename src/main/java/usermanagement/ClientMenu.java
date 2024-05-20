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
            System.out.println("Меню пользователя:");
            System.out.println("1. Просмотреть каталога услуг");
            System.out.println("2. Создать заказ");
            System.out.println("3. Просмотреть все свои заказы");
            System.out.println("4. Выход");
            System.out.print("Выберите действие: ");

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
                    System.out.println("Выход из меню пользователя.");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте еще раз.");
            }
        }
    }

}
