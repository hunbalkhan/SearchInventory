package com.pluralsight;

import java.util.ArrayList;
import java.io.*;


public class Main {

    // This gets the inventory.
    public static ArrayList<Product> inventory = getInventory();


    public static void main(String[] args) {

        while (true) {  // Move the while loop to wrap everything

            System.out.println("\n---------Search inventory---------");

            // NEW DISPLAY MENU
            System.out.println("""
                What do you want to do?
                 1- List all products
                 2- Lookup a product by its id
                 3- Find all products within a price range
                 4- Add a new product
                 5- Quit the application""");
            int choice = ConsoleHelper.promptForInt("Enter command");

            switch (choice) {
                case 1:
                    listAllProducts();
                    break;
                case 2:
                    lookupProductById();
                    break;
                case 3:
                    findProductByPriceRange();
                    break;
                case 4:
                    addNewProduct();
                    break;
                case 5:
                    System.out.println("----- You have exited the Search Inventory application -----");
                    return;  // Exit the program
                default:
                    System.out.println("Invalid choice! Please enter 1-5.");
                    break;
            }
        }  // End of while loop
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

        } catch (IOException e) {
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

    public static void listAllProducts() {
        System.out.println("\n--- All Products ---");  // Add title
        for (Product product : inventory) {
                System.out.println(product);  // Automatically calls toString()!
            }
    }

    public static void lookupProductById() {
        int searchId = ConsoleHelper.promptForInt("Enter product ID");  // Remove duplicate prompt
        System.out.println("\n ----- SEARCH RESULTS -----");

        boolean found = false;

        for (Product product : inventory) {
            if (product.getId() == searchId) {  // FIXED: lowercase 'product'
                System.out.println("\n--- Product Found ---");
                System.out.println(product);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Sorry, no product found.");
        }
        System.out.println("-----------------------------\n");
    }

    public static void findProductByPriceRange() {
        double minPrice = ConsoleHelper.promptForDouble("Enter minimum price");
        double maxPrice = ConsoleHelper.promptForDouble("Enter maximum price");

        System.out.println("\n--- Products in Price Range $" + minPrice + " - $" + maxPrice + " ---"); // title

        boolean found = false;

        for (Product product : inventory) {
            double price = product.getPrice();

            if (price >= minPrice && price <= maxPrice) {
                System.out.println("ID: " + product.getId() + " | Name: " + product.getName() + " | Price: $" + price);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No products found in this price range!");
        }
    }

    public static void addNewProduct() {
        int id = ConsoleHelper.promptForInt("Enter product ID: ");
        String name = ConsoleHelper.promptForString("Enter product name: ");
        double price = ConsoleHelper.promptForDouble("Enter product price: ");

        Product newProduct = new Product(id, name, price);
        inventory.add(newProduct);

        try {
            FileWriter fileWriter = new FileWriter("inventory.csv", true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.newLine();
            bw.write(newProduct.getId() + "|" + newProduct.getName() + "|" + newProduct.getPrice());

        } catch (IOException e) {
            System.out.println("Failed to close file: " + e.getMessage());
        }
    }
}