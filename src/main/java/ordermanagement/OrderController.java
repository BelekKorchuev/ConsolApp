package ordermanagement;

import java.util.Scanner;

public class OrderController {
    private final Scanner scanner;

    public OrderController(OrderService orderService) {
        this.scanner = new Scanner(System.in);
    }

    // Методы для обработки запросов, например:
//   public List<Order> getAllOrders() {
//      return orderService.getAllOrders();
//   }

    // метод для создания заказа для админа
    public void addOrder_Admin() {
        System.out.println("Процесс добавления заказа:");
        System.out.print("Введите имя: ");
        String username = scanner.nextLine();
        scanner.nextLine();

        System.out.print("Введите модель машины: ");
        String car_model = scanner.nextLine();

        System.out.print("Введите тип мойки: ");
        String wash_type = scanner.nextLine();

        System.out.print("Введите статус: ");
        String status = scanner.nextLine();

        boolean added = OrderService.add_order_Admin(username, car_model, wash_type, status);
        if (added) {
            System.out.println("Заказ успешно добавлен!");
        } else {
            System.out.println("Ошибка. Повторите еще раз.");
        }
    }

    // метод для удаления заказа для админа
    public void deleteOrder_Admin() {
        System.out.println("Процесс удаления заказа: ");
        System.out.print("Введите ID заказа: ");
        int orderId = scanner.nextInt();

        boolean deleted = OrderService.delete_order_Admin(orderId);
        if (deleted) {
            System.out.println("Заказ успешно удален!");
        } else {
            System.out.println("Ошибка. Повторите еще раз.");
        }
    }

    public void updateOrderStatus_Admin() {
        System.out.println("Процесс изменения статуса заказа: ");
        System.out.print("Введите ID заказа: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Введите новый статус для заказа: ");
        String newStatus = scanner.nextLine();

        boolean updated = OrderService.update_orderStatus_Admin(orderId, newStatus);
        if (updated) {
            System.out.println("Статус заказа успешно изменен!");
        } else {
            System.out.println("Ошибка. Повторите еще раз.");
        }
    }
}

