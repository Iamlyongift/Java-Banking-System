package dbConnect;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestSQLite {
    public static void main(String[] args) throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:bank.db");
        System.out.println("SQLite connected successfully!");
        conn.close();
    }
}
