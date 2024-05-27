package ordermanagement;

import authentication.Session;

import java.util.Scanner;

public class OrderController {
    private final Scanner scanner;
    Session session = Session.getInstance();

    public OrderController() {
        this.scanner = new Scanner(System.in);
    }

    // Методы для вывода детального списка
    public void getAllOrders() {
        System.out.println("---------------------");
        System.out.println(" All orders: ");
        System.out.println("---------------------");
        OrderService.displayOrderDetails();
        System.out.println("---------------------");

    }

    // метод для создания заказа для админа
    public void addOrder_Admin() {
        System.out.println("Order adding process:");
        System.out.println("User list (here you can view the name and ID of users): ");
        System.out.println("---------------------");
        OrderService.displayUsers();
        System.out.println("---------------------");
        scanner.nextLine();

        System.out.print("Enter username:: ");
        String username = scanner.nextLine();

        System.out.print("Enter user ID: ");
        int name_id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter the car model: ");
        String car_model = scanner.nextLine();

        System.out.println("Service list: ");
        System.out.println("---------------------");
        OrderService.displayServices();
        System.out.print("Choose service ID: ");
        int wash_type_id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Specify the status: ");
        String status = scanner.nextLine();

        System.out.print("Enter the date: ");
        String created_at = scanner.nextLine();

        boolean added = OrderService.add_order_Admin(username, name_id, car_model, wash_type_id, status, created_at);
        if (added) {
            System.out.println("Order successfully added!");
        } else {
            System.out.println("Error. Please try again.");
        }
    }

    // метод для создания заказа для клиента
    public void addOrder_Client() {
        System.out.println("Order adding process:");

        String customer_name = session.getUsername();
        int customer_id = session.getUserId();

        System.out.print("Enter the car model: ");
        String car_model = scanner.nextLine();

        System.out.println("Service list: ");
        System.out.println("---------------------");
        OrderService.displayServices();
        System.out.print("Choose service ID: ");
        int wash_type_id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter the date: ");
        String created_at = scanner.nextLine();

        boolean added = OrderService.add_order_Client(customer_name, customer_id, car_model, wash_type_id, created_at);
        if (added) {
            System.out.println("Order successfully added!");
        } else {
            System.out.println("Error. Please try again.");
        }
    }

    // метод для удаления заказа для админа
    public void deleteOrder_Admin() {
        System.out.println("Order deletion process: ");
        System.out.println("Orders List: ");
        OrderService.displayOrders();
        System.out.print("Enter order ID: ");
        int orderId = scanner.nextInt();

        boolean deleted = OrderService.delete_order_Admin(orderId);
        if (deleted) {
            System.out.println("Order successfully deleted!");
        } else {
            System.out.println("Error. Please try again.");
        }
    }

    // метод для изменения статуса заказа для админа
    public void updateOrderStatus_Admin() {
        System.out.println("Order status change process: ");
        System.out.println("Orders list: ");
        OrderService.displayOrders();
        System.out.print("Enter order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter the new status for the order:");
        String newStatus = scanner.nextLine();

        boolean updated = OrderService.update_orderStatus_Admin(orderId, newStatus);
        if (updated) {
            System.out.println("---------------------------");
        } else {
            System.out.println("Error. Please try again..");
        }
    }

    // Методы для вывода собственных заказов клиента
    public void getAllClientOrders() {
        int customer_id = session.getUserId();
        System.out.println("---------------------");
        System.out.println(" All your own order: ");
        System.out.println("---------------------");
        OrderService.clientOwnOrder(customer_id);
        System.out.println("---------------------");

    }
}

