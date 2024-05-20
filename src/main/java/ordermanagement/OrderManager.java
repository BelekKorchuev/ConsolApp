package ordermanagement;

import usermanagement.UserController;

import java.util.Scanner;

public class OrderManager {
    public static void orderMenu() {
        while (true) {
            OrderController orderController = new OrderController();
            Scanner scanner = new Scanner(System.in);

            System.out.println("Меню управления заказами:");
            System.out.println("1. Просмотр всех заказов");
            System.out.println("2. Добавдение заказа");
            System.out.println("3. Удаление заказа");
            System.out.println("4. Изменение статуса заказа");
            System.out.println("5. Назад");
            System.out.print("Выберите действие: ");

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
                    System.out.println("Возврат в меню администратора.");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте еще раз.");
            }
        }
    }
}
