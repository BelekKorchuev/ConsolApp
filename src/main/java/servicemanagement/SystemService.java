package servicemanagement;

import database.DatabaseManager;

public class SystemService {

    public static void addWashType(String washType, int price, String description) {
        DatabaseManager.addWashType(washType, price, description);
    }

    public static void updateWashType(int washTypeId, String newWashType, int newPrice, String newDescription) {
        DatabaseManager.updateWashType(washTypeId, newWashType, newPrice, newDescription);
    }

    public static void deleteWashType(int washTypeId) {
        DatabaseManager.deleteWashType(washTypeId);
    }

    public static void displayAllWashType() {
        DatabaseManager.displayWashType();
    }

}
