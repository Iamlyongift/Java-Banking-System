package dbConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    //private static final String DB_URL = "jdbc:sqlite:bank_accounts.db";
    private static final String DB_URL =
            "jdbc:sqlite:C:/Users/user/IdeaProjects/Bank Account Application/bank_accounts.db";


    // Get database connection
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Database connected successfully!");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return conn;
    }

    // Create the accounts table if it doesn't exist
    public static void createTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS accounts (
                account_number TEXT PRIMARY KEY,
                account_holder_name TEXT NOT NULL,
                balance REAL NOT NULL
            );
        """;  // ✅ This was missing!

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table created successfully!");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    // Test connection
    public static void main(String[] args) {
        System.out.println("Testing database connection...");
        createTable();
        System.out.println("Database setup complete!");
    }
}