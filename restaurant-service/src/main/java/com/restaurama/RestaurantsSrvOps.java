package com.restaurama;

import java.sql.SQLException;
import java.util.*;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import com.restaurama.db.mysqlConn;

public class RestaurantsSrvOps {
    // Common query constants
    static final String FETCH_RESTAURANTS_ALL = "SELECT * FROM restaurants_info";
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        getRestaurantsList(FETCH_RESTAURANTS_ALL);
    }
    public static void getRestaurantsList(String query) throws ClassNotFoundException, SQLException {
        // fetching all restaurants from restaurants db using dbQueryExec method mf mysqlConn common class
        try {
            List<Map<String, Object>> restaurants = mysqlConn.dbQueryExec(query);
            System.out.println("restaurants: " + restaurants);
        }
        finally {
            AbandonedConnectionCleanupThread.checkedShutdown();
        }
    }
}