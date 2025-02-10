package com.restaurama;

import java.sql.SQLException;
import java.util.Scanner;

public class RestaurantCLI {
    public static void startInteractiveMode() throws ClassNotFoundException, SQLException {
        Scanner userAction = new Scanner(System.in);
        String user_action = "";
        do {
            System.out.println(" ");
            System.out.println("=============================================");
            System.out.println(" ");
            System.out.println("Select an action from the following menu:");
            System.out.println("1) List all Restaurants");
            System.out.println("2) Add a New Restaurant");
            System.out.println("3) Update Restaurant");
            System.out.println("4) List Restaurants and Delete Selected");
            System.out.println("q) Exit the actions menu");
            System.out.print("Enter your choice (1-4), or q to quit: ");
            System.out.println(" ");
            user_action = userAction.nextLine();

            // Proceed based on user's choice:
            switch (user_action) {
                case "1":
                    System.out.println("Selection: List all Restaurants");
                    RestaurantDB.getRestaurantsList(SQLQueries.FETCH_RESTAURANTS_ALL);
                    break;
                case "2":
                    System.out.println("Selection: Add a New Restaurant");
                    RestaurantDB.addRestaurantToDB(SQLQueries.INSERT_NEW_RESTAURANT);
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
                        RestaurantDB.updateResturantDBRecord(columnName, newValue, restaurant_id_up);
                    }
                    break;

                case "4":
                    System.out.println("Selection: List Restaurants and Delete Selected");
                    RestaurantDB.getRestaurantsList(SQLQueries.FETCH_RESTAURANTS_ALL);
                    System.out.print("Enter restaurant id to delete, or enter q to quit: ");

                    String restaurant_id_del = userAction.nextLine();

                    if (!restaurant_id_del.equals("q")) {
                        RestaurantDB.delRestaurantFromDB(SQLQueries.DELETE_RESTAURANT_BY_ID, restaurant_id_del);
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
}