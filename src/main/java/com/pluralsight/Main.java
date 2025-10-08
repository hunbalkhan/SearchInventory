package com.pluralsight;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // This gets the inventory.
        ArrayList<Product> inventory = getInventory();

        // this displays all the prodyucts
        System.out.println("Search inventory");
        for (Product product : inventory) {
            System.out.println("ID: " + product.getId() + " | Name: " + product.getName() + " | Price: $" + product.getPrice());
        }
    }

    public static ArrayList<Product> getInventory() {
        ArrayList<Product> products = new ArrayList<>();

        // Manually adding products into the array list.
        products.add(new Product( 1, "Apple                     ", 0.99));
        products.add(new Product( 2, "Milk                      ", 2.59));
        products.add(new Product( 3, "Eggs                      ", 2.89));
        products.add(new Product( 4, "Chocolate Milk            ", 7.99));
        products.add(new Product( 5, "Gold Standard Whey Protein", 59.99));

        return products;
    }
}