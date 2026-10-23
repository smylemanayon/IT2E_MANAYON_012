
package com.scc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScenarioDao {

    // RECORD VEHICLE PASSAGE
    public boolean recordPassage(
            String plateNumber,
            String vehicleClass,
            double tollFee,
            String paymentMethod) {

        String sql = """
                INSERT INTO passage_logs
                (plate_number, vehicle_class, toll_fee, payment_method)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, plateNumber);
            pstmt.setString(2, vehicleClass);
            pstmt.setDouble(3, tollFee);
            pstmt.setString(4, paymentMethod);

            pstmt.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Record failed: " + e.getMessage()
            );

            return false;
        }
    }

    // VIEW PASSAGE LOGS
    public void viewPassageLogs() {

        String sql = """
                SELECT id, plate_number, vehicle_class,
                       toll_fee, payment_method
                FROM passage_logs
                ORDER BY id ASC
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println();

            System.out.println(
                    "================================================================================"
            );

            System.out.printf(
                    "%48s%n",
                    "EXPRESSWAY PASSAGE LOGS"
            );

            System.out.println(
                    "================================================================================"
            );

            System.out.println(
                    "+----+-----------------+----------+----------+----------+"
            );

            System.out.printf(
                    "| %-2s | %-15s | %-8s | %-8s | %-8s |%n",
                    "ID",
                    "PLATE NO",
                    "CLASS",
                    "TOLL FEE",
                    "PAYMENT"
            );

            System.out.println(
                    "+----+-----------------+----------+----------+----------+"
            );

            int totalRecords = 0;

            while (rs.next()) {

                int id = rs.getInt("id");

                String plateNumber =
                        rs.getString("plate_number");

                String vehicleClass =
                        rs.getString("vehicle_class");

                double tollFee =
                        rs.getDouble("toll_fee");

                String paymentMethod =
                        rs.getString("payment_method");

                System.out.printf(
                        "| %-2d | %-15s | %-8s | %8.2f | %-8s |%n",
                        id,
                        plateNumber,
                        vehicleClass,
                        tollFee,
                        paymentMethod
                );

                totalRecords++;
            }

            System.out.println(
                    "+----+-----------------+----------+----------+----------+"
            );

            System.out.println();

            System.out.println(
                    "Total Records Displayed: " + totalRecords
            );

            System.out.println();

        } catch (SQLException e) {

            System.out.println(
                    "Unable to retrieve logs: " + e.getMessage()
            );
        }
    }
}
