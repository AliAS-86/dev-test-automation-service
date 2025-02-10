package com.restaurama;

import java.sql.*;
import java.util.*;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import com.restaurama.db.MysqlConn;
import com.restaurama.formatter.beautify_output;

public class Restaurant {
    public void listAllRestaurants() throws SQLException {
       try {
           RestaurantDB.getRestaurantsList(SQLQueries.FETCH_RESTAURANTS_ALL);
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }
    }
    public void interactiveMode() throws SQLException, ClassNotFoundException {
        try {
            RestaurantCLI.startInteractiveMode();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}