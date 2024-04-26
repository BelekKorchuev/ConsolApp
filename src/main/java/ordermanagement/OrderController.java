package ordermanagement;

import java.util.Scanner;

public class OrderController {
    private final Scanner scanner;

    public OrderController(OrderService orderService) {
        this.scanner = new Scanner(System.in);
    }

    // Методы для вывода детального списка
    public void getAllOrders() {
        System.out.println("---------------------");
        System.out.println(" Все заказы: ");
        System.out.println("---------------------");
        OrderService.displayOrderDetails();
        System.out.println("---------------------");

    }

    // метод для создания заказа для админа
    public void addOrder_Admin() {
        System.out.println("Процесс добавления заказа:");
        System.out.println("Список пользователей(здесь можно просмотреть имя и id пользователей): ");
        System.out.println("---------------------");
        OrderService.displayUsers();
        System.out.println("---------------------");
        scanner.nextLine();

        System.out.print("Введите имя: ");
        String username = scanner.nextLine();

        System.out.print("Введите ваш ID: ");
        int name_id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Введите модель машины: ");
        String car_model = scanner.nextLine();

        System.out.println("Список услуг: ");
        System.out.println("---------------------");
        OrderService.displayServices();
        System.out.print("Выберите ID услуги: ");
        int wash_type_id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Введите статус: ");
        String status = scanner.nextLine();

        System.out.print("Введите дату: ");
        String created_at = scanner.nextLine();

        boolean added = OrderService.add_order_Admin(username, name_id, car_model, wash_type_id, status, created_at);
        if (added) {
            System.out.println("Заказ успешно добавлен!");
        } else {
            System.out.println("Ошибка. Повторите еще раз.");
        }
    }

    // метод для удаления заказа для админа
    public void deleteOrder_Admin() {
        System.out.println("Процесс удаления заказа: ");
        System.out.println("Список заказов: ");
        OrderService.displayOrders();
        System.out.print("Введите ID заказа: ");
        int orderId = scanner.nextInt();

        boolean deleted = OrderService.delete_order_Admin(orderId);
        if (deleted) {
            System.out.println("Заказ успешно удален!");
        } else {
            System.out.println("Ошибка. Повторите еще раз.");
        }
    }

    // метод для изменения статуса заказа для админа
    public void updateOrderStatus_Admin() {
        System.out.println("Процесс изменения статуса заказа: ");
        System.out.println("Список заказов: ");
        OrderService.displayOrders();
        System.out.print("Введите ID заказа: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Введите новый статус для заказа: ");
        String newStatus = scanner.nextLine();

        boolean updated = OrderService.update_orderStatus_Admin(orderId, newStatus);
        if (updated) {
            System.out.println("Статус заказа успешно изменен!");
            System.out.println("---------------------------");
        } else {
            System.out.println("Ошибка. Повторите еще раз.");
        }
    }
}

