package com.restaurama;

import java.sql.*;
import java.util.*;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import com.restaurama.db.MysqlConn;
import com.restaurama.formatter.beautify_output;

// This class handles the DB CRUD operations on the Restaurant DB
public class RestaurantDB {
    private static Set<String> VALID_COLUMNS;

    static {
        try {
            VALID_COLUMNS = getColumnsNamesFromDB();
        } catch (SQLException e) {
            System.out.println("Error while reading columns names from database" + e.getMessage());
        }
    }

    public static List<Map<String, Object>> getRestaurantsList(String query) throws SQLException {
        // fetching all restaurants from restaurants db using dbQueryExec method mf mysqlConn common class

        try {
            List<Map<String, Object>> restaurants = MysqlConn.dbQueryExec(query);
            beautify_output.beautify_table(restaurants);
            return restaurants;
        }
        finally {
            AbandonedConnectionCleanupThread.checkedShutdown();
        }
    }
    public static void addRestaurantToDB(String query) throws ClassNotFoundException, SQLException {
        List<String> params = new ArrayList<>();
        Scanner userInput = new Scanner(System.in);

        try {
            System.out.println("Enter restaurant name: ");
            String restaurantName = userInput.nextLine();

            System.out.println("Enter restaurant address: ");
            String restaurantAddress = userInput.nextLine();

            System.out.println("Enter restaurant contact info (Phone #): ");
            String restaurantContactInfo = userInput.nextLine();

            System.out.println("Enter restaurant rating on 1-5 scale: ");
            String restaurantRating = userInput.nextLine();

            params.add(restaurantName);
            params.add(restaurantAddress);
            params.add(restaurantContactInfo);
            params.add(restaurantRating);

            String results = MysqlConn.dbQueryExec(query, restaurantName, restaurantAddress, restaurantContactInfo, restaurantRating).toString();
            System.out.println(results + "New restaurant added: " + params);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void updateResturantDBRecord(String columnName, Object newValue, Object id) throws ClassNotFoundException, SQLException {
        try {
            if (!VALID_COLUMNS.contains(columnName)) {
                throw new SQLException("Invalid column name: " + columnName);
            }
            String query = "UPDATE restaurants_info SET " + columnName + " = ? WHERE id = ?";
            String results = MysqlConn.dbQueryExec(query, newValue, id).toString();
            System.out.println("Restaurant with ID# " + id +  " Updated. " + results);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void delRestaurantFromDB(String query,Object id) throws ClassNotFoundException, SQLException {
        try {
            String results = MysqlConn.dbQueryExec(query, id).toString();
            System.out.println("Restaurant with ID# " + id +  " Deleted. " + results);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private static Set<String> getColumnsNamesFromDB() throws SQLException {
        Set<String> columnNames = new HashSet<>();
        List<Map<String, Object>> columns = MysqlConn.dbQueryExec(SQLQueries.GET_DB_COLUMNS);

        for (Map<String, Object> column : columns) {
            columnNames.addAll(column.keySet());
        }
        return columnNames;
    }
}