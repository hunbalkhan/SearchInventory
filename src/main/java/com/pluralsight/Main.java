package com.pluralsight;

import java.util.ArrayList;
import java.io.*;

public class Main {

    // This gets the inventory.
    public static ArrayList<Product> inventory = getInventory();






    public static void main(String[] args) {

        System.out.println("---------Search inventory---------");

        // this displays all the products
        for (Product product : inventory) {
            System.out.print("ID: " + product.getId() + " | Name: " + product.getName() + " | Price: $" + product.getPrice());
        }
    }







    public static ArrayList<Product> getInventory() {
        // Will give us back a list of 'productys'
        ArrayList<Product> products = new ArrayList<>(); // empty arrayaList box

        try {
            FileReader fileReader = new FileReader("inventory.csv");        // This helper opens the chosen file so we can look into it
            BufferedReader bufferedReader = new BufferedReader(fileReader); // This helper reads the file one line at a time

            String line; // This is a helper that holds each line of text we read

            while ((line = bufferedReader.readLine()) != null) { // means to keep reading lines until no more lines left to read, each time it loops to read the next line of txt
                String[] parts = line.split("\\|");              // Split the line into pieces where you see the | pipe symbol

                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    double price = Double.parseDouble(parts[2].trim());
                    products.add(new Product(id, name, price));         // This all is basically taking the
                }
            }

        }
        catch (IOException e){
            System.out.println("File reading failed. Loading hardcoded products.");
            // Add to 'products'
            products.add(new Product(1, "Apple", 0.99));
            products.add(new Product(2, "Milk", 2.59));
            products.add(new Product(3, "Eggs", 2.89));
            products.add(new Product(4, "Chocolate Milk", 7.99));
            products.add(new Product(5, "Gold Standard Whey Protein", 59.99));
        }

        return products;
    }






}