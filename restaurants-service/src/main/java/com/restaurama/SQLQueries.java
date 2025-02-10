package com.restaurama;

public class SQLQueries {
    public static final String FETCH_RESTAURANTS_ALL = "SELECT * FROM restaurants_info";
    public static final String INSERT_NEW_RESTAURANT = "INSERT INTO restaurants_info (name, address, contact_info, rating) VALUES (?, ?, ?, ?)";
    public static final String UPDATE_RESTAURANT_BY_ID = "UPDATE restaurants_info SET ? = ? WHERE id = ?";
    public static final String DELETE_RESTAURANT_BY_ID= "DELETE FROM restaurants_info WHERE id = ?";
    public static final String GET_DB_COLUMNS = "SELECT * FROM restaurants_info LIMIT 1";
}