package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private final static String URL = "jdbc:postgresql://localhost:5432/covid";
    private final static String NAME = "postgres";
    private final static String PASSWORD = "admin123";

    private Connection connection = null;

    public Connection connectDB() {
        try {
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
