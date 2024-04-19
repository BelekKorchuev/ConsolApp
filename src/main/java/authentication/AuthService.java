package authentication;

import database.DatabaseManager;

public class AuthService {

    private final DatabaseManager databaseManager;

    public AuthService(DatabaseManager databaseManager) {
            this.databaseManager = databaseManager;

        }

    // Метод для аутентификации пользователя
    public boolean authenticateClient(String username, String password) {
        String storedPassword = DatabaseManager.getUserPassword(username);
        if (storedPassword != null) {
            // Проверка правильности пароля
            return storedPassword.equals(password);
        }
        return false;
    }




    public boolean adminLogin(String username, String password) {
        // Получаем хэшированный пароль администратора из базы данных
        String storedPassword = DatabaseManager.getUserPassword(username);
        if (storedPassword != null) {
            // Проверяем правильность введенного пароля
            return storedPassword.equals(password);
        } else {
            System.out.println("Пользователь с таким именем не существует.");
            return false;
        }

    }
}