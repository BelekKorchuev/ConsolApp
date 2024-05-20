package usermanagement;

import database.DatabaseManager;

public class UserService {

    public static void UserListWithIds() {
        DatabaseManager.UserList();
    }

    public static void deleteUserById(int userId) {
        DatabaseManager.deleteUserById(userId);
    }


}

