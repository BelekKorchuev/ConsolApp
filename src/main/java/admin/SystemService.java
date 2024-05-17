package admin;

public class SystemService {
    private static final String DATABASE_URL = "jdbc:sqlite:src/main/resources/mydatabase.db";
    public static void addWashType(String washType, int price, String description) {
        database.DatabaseManager.addWashType(washType, price, description);
    }
    public static void updateWashType(int washTypeId, String newWashType, int newPrice, String newDescription) {
        database.DatabaseManager.updateWashType(washTypeId, newWashType, newPrice, newDescription);
    }
    public static void deleteWashTypeById(int washTypeId) {
        database.DatabaseManager.deleteWashTypeById(washTypeId);
    }
}
