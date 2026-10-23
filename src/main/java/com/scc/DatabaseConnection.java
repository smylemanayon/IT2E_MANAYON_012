
package com.scc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private static final String URL = "jdbc:sqlite:tollgate.db";

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(URL);
    }

    public static void initialize() {

        String sql = """
                CREATE TABLE IF NOT EXISTS passage_logs (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    plate_number TEXT NOT NULL,
                    vehicle_class TEXT NOT NULL,
                    toll_fee REAL NOT NULL,
                    payment_method TEXT NOT NULL,
                    passage_time TEXT DEFAULT CURRENT_TIMESTAMP
                )
                """;

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

            System.out.println("Database connected successfully!");

        } catch (SQLException e) {

            System.out.println(
                    "Database Error: " + e.getMessage()
            );
        }
    }
}
