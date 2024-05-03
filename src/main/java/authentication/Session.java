package authentication;

public class Session {
    private static Session instance;
    private String username;
    private int userId;

    private Session() {
        // Приватный конструктор, чтобы предотвратить создание объекта сессии извне
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public void setUserInfo(String username, int userId) {
        this.username = username;
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public int getUserId() {
        return userId;
    }
}
