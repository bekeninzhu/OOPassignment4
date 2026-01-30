package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/vet_clinic_db"; // Update with your database name
    private static final String USER = "postgres";  // PostgreSQL username
    private static final String PASSWORD = "postgres"; // PostgreSQL password

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println(" Connected to the database!");
        } catch (SQLException e) {
            System.out.println(" Connection failed: " + e.getMessage());
        }
        return connection;
    }

}
