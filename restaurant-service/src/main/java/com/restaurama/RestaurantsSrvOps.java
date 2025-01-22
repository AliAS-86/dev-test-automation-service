package com.restaurama;

import java.sql.*;
import java.util.*;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import com.restaurama.db.mysqlConn;
import com.restaurama.formatter.beautify_output;

public class RestaurantsSrvOps {
    // placeholders
    //    static String columnName = "";
    // Common query constants
    static final String FETCH_RESTAURANTS_ALL = "SELECT * FROM restaurants_info";
    static final String INSERT_NEW_RESTAURANT = "INSERT INTO restaurants_info (name, address, contact_info, rating) VALUES (?, ?, ?, ?)";
    static final String DELETE_RESTAURANT_BY_ID= "DELETE FROM restaurants_info WHERE id = ?";
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner userAction = new Scanner(System.in);
        String user_action = "";
        do {
            System.out.println("Select an option:");
            System.out.println("1) List all Restaurants");
            System.out.println("2) Add a New Restaurant");
            System.out.println("3) Update Restaurant");
            System.out.println("4) List Restaurants and Delete Selected");
            System.out.println("q) Exit the actions menu");
            System.out.print("Enter your choice (1-4), or q to quit: ");
            user_action = userAction.nextLine();

            // Proceed based on user's choice:
            switch (user_action) {
                case "1":
                    System.out.println("Selection: List all Restaurants");
                    getRestaurantsList(FETCH_RESTAURANTS_ALL);
                    break;
                case "2":
                    System.out.println("Selection: Add a New Restaurant");
                    addRestaurantToDB(INSERT_NEW_RESTAURANT);
                    break;
                case "3":
                    System.out.println("Selection: Update Restaurant");
                    System.out.println("Enter restaurant id to update, or enter q to quit:");
                    String restaurant_id_up = userAction.nextLine();
                    System.out.println("Enter column name to update, or enter q to quit:");
                    String columnName = userAction.nextLine();
                    System.out.println("Enter new value to update, or enter q to quit:");
                    String newValue = userAction.nextLine();

                    if (!restaurant_id_up.equals("q")) {
                        String UPDATE_RESTAURANT_BY_ID= "UPDATE restaurants_info SET " + columnName + " = ? WHERE id = ?";
                        System.out.println("Update Query: " + UPDATE_RESTAURANT_BY_ID);
                        updateResturantDBRecord(UPDATE_RESTAURANT_BY_ID, newValue, restaurant_id_up);
                    }
                    break;

                case "4":
                    System.out.println("Selection: List Restaurants and Delete Selected");
                    getRestaurantsList(FETCH_RESTAURANTS_ALL);
                    System.out.print("Enter restaurant id to delete, or enter q to quit: ");

                    String restaurant_id_del = userAction.nextLine();

                    if (!restaurant_id_del.equals("q")) {
                        delRestaurantFromDB(DELETE_RESTAURANT_BY_ID, restaurant_id_del);
                    }
                case "q":
                    System.out.println("Exiting menu, Bye");
                    break;
                default:
                    System.out.println("Input is invalid, please enter 1-4 to perform a specific action or q to quit");
            }

        }
        while (!user_action.equals("q"));
        userAction.close();
    }
    public static List<Map<String, Object>> getRestaurantsList(String query) throws ClassNotFoundException, SQLException {
        // fetching all restaurants from restaurants db using dbQueryExec method mf mysqlConn common class
        try {
            List<Map<String, Object>> restaurants = mysqlConn.dbQueryExec(query);
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

            String results = mysqlConn.dbQueryExec(query, restaurantName, restaurantAddress, restaurantContactInfo, restaurantRating).toString();
            System.out.println(results + "New restaurant added: " + params);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void updateResturantDBRecord(String query, Object new_value, Object id) throws ClassNotFoundException, SQLException {
        try {
            String results = mysqlConn.dbQueryExec(query, new_value, id).toString();
            System.out.println("Restaurant with ID# " + id +  " Updated. " + results);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void delRestaurantFromDB(String query,Object id) throws ClassNotFoundException, SQLException {
        try {
            String results = mysqlConn.dbQueryExec(query, id).toString();
            System.out.println("Restaurant with ID# " + id +  " Deleted. " + results);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}