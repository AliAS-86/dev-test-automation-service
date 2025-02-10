package com.restaurama;

public class Restaurama {
    public static void main(String[] args) {
        try {
            Restaurant restaurant = new Restaurant();
            if (args.length == 0) {
                restaurant.listAllRestaurants();
            } else if (args.length == 1 && args[0].equalsIgnoreCase("interactive")) {
                restaurant.interactiveMode();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}