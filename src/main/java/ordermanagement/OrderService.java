package ordermanagement;

import database.DatabaseManager;

public class OrderService {
    private static DatabaseManager databaseManager;


//    public List<Order> getAllOrders() {
//        return databaseManager.getAllOrders();
//    }

    // Админ метод что обрашяется к методу для добавдения заказа
    public static boolean add_order_Admin(String name, int name_id, String car_model, int wash_type_id, String status, String created_at) {
        DatabaseManager.addOrder(name, name_id, car_model, wash_type_id, status, created_at);
        return true;
    }

    // Админ метод что обрашяется к методу для удаления заказа
    public static boolean delete_order_Admin(int orderId) {
        DatabaseManager.deleteOrder(orderId);
        return true;
    }

    // Админ метод что обрашяется к методу для изменения статуса заказа
    public static boolean update_orderStatus_Admin(int orderId, String newStatus) {
        DatabaseManager.updateOrderStatus(orderId, newStatus);
        return true;
    }

    // метод что обрашяется к методу вывода всех пользователей(для админа)
    public static void displayUsers() {
        DatabaseManager.displayUsers();
    }

    // метод что обрашается к методу вывода услуг(для админа)
    public static void displayServices() {
        DatabaseManager.displayServices();
    }

    // метод что обрашается к методу вывода списка заказов(для админа)
    public static void displayOrders() {
        DatabaseManager.displayOrders();
    }

    public static void displayOrderDetails() {
        DatabaseManager.displayOrdersDetails();
    }
}

