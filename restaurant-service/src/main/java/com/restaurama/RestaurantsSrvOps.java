package com.restaurama;

import java.sql.*;
import java.util.*;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import com.restaurama.db.mysqlConn;

public class RestaurantsSrvOps {
    // Common query constants
    static final String FETCH_RESTAURANTS_ALL = "SELECT * FROM restaurants_info";
    static final String INSERT_NEW_RESTAURANT = "INSERT INTO restaurants_info (name, address, contact_info, rating) VALUES (?, ?, ?, ?)";
    static final String DELETE_RESTAURANT_BY_ID= "DELETE FROM restaurants_info WHERE id = ?";
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner userAction = new Scanner(System.in);
        try {
            System.out.println("Select an option:");
            System.out.println("1) List all Restaurants");
            System.out.println("2) Add a New Restaurant");
            System.out.println("3) List Restaurants and Delete Selected");
            System.out.println("q) Exit the actions menu");
            System.out.print("Enter your choice (1, 2, or 3), q to quit: ");
            String user_action = userAction.nextLine();

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
                    System.out.println("Selection: List Restaurants and Delete Selected");
                    getRestaurantsList(FETCH_RESTAURANTS_ALL);
                    System.out.print("Enter restaurant id to delete, or enter q to quit: ");

                    String restaurant_id = userAction.nextLine();

                    if (!restaurant_id.equals("q")) {
                        delRestaurantFromDB(DELETE_RESTAURANT_BY_ID, restaurant_id);
                    }
                    break;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
     }
    public static List<Map<String, Object>> getRestaurantsList(String query) throws ClassNotFoundException, SQLException {
        // fetching all restaurants from restaurants db using dbQueryExec method mf mysqlConn common class
        try {
            List<Map<String, Object>> restaurants = mysqlConn.dbQueryExec(query);
            System.out.println("restaurants: " + restaurants);
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