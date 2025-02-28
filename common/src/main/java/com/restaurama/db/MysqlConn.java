package com.restaurama.db;

import java.util.ArrayList;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Main Class
public class MysqlConn {
    // Class entry point, the main method


    // Method to establish and return a new connection with MySQL DB
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://127.0.0.1:3307/restaurants?useSSL=false&allowPublicKeyRetrieval=true";
        String user = "admin";
        String password = "adminpassword";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }

    // Method to test the connection established by getConnection() and confirm success connection to the DB
    public static void testConnection() throws ClassNotFoundException, SQLException {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Connected to DB: " + conn.getMetaData().getDatabaseProductName());
            }
        } catch (SQLException e) {
            System.out.println("Error trying to connect to DB: " + e.getMessage());
        }
    }

    // Method to perform MySQL queries, the query is provided as an argument
    public static List<Map<String, Object>> dbQueryExec(String query, Object... params) {
        List<Map<String, Object>> results = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }

            if (query.trim().toUpperCase().startsWith("SELECT")) {
                try (ResultSet rs = pstmt.executeQuery(query)) {
                    // Get metadata to retrieve count of columns for the results loop
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnCount = rsmd.getColumnCount();

                    // Iterate over the table, each row and store each column data in map<key, val>
                    while (rs.next()) {
                        Map<String, Object> row = new HashMap<>();
                        for (int i = 1; i <= columnCount; i++) {
                            row.put(rsmd.getColumnName(i), rs.getObject(i));
                        }
                        results.add(row);
                    }
                }
            }
            else {
                // Handle non-SELECT queries (e.g., INSERT, UPDATE, DELETE)
                int affectedRows = pstmt.executeUpdate();
                Map<String, Object> updateInfo = new HashMap<>();
                updateInfo.put("affectedRows", affectedRows);
                results.add(updateInfo);
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error executing query: " + e.getMessage());
            // Optionally rethrow as a runtime exception
            throw new RuntimeException("Database query failed", e);
        }
        return results;
    }
}