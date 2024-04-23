package ordermanagement;

import database.DatabaseManager;

public class OrderService {
    private static DatabaseManager databaseManager;


//    public List<Order> getAllOrders() {
//        return databaseManager.getAllOrders();
//    }

    // Админ метод что обрашяется к методу для добавдения заказа
    public static boolean add_order_Admin(String name, String car_model, String wash_type, String status) {
        DatabaseManager.addOrder(name, car_model, wash_type, status);
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
}

