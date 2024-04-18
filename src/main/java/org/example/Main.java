package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
         try (Connection connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/mydatabase.db")) {
            System.out.println("Подключение к базе данных успешно!");
        } catch (SQLException e) {
            System.err.println("Ошибка при подключении к базе данных: " + e.getMessage());
        }
    }
}