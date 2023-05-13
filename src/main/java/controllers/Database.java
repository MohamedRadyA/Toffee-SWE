package controllers;

import model.CatalogItem;
import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Database {
    private static List<User> users;
    private static List<CatalogItem> catalogItems;

    private static final String USERS_FILE = "src/users.txt";
    private static final String CATALOG_FILE = "src/catalog.txt";

    public static void init() {
        users = new ArrayList<>();
        catalogItems = new ArrayList<>();
        loadUsersFromFile(USERS_FILE);
        loadCatalogItemsFromFile(CATALOG_FILE);
    }


    public static List<User> getUsers() {
        return users;
    }

    public static void addUser(User user) {
        users.add(user);
        saveUsersToFile(USERS_FILE);
    }

    private static void loadUsersFromFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length == 3) {
                    String username = parts[0].trim();
                    String password = parts[1].trim();
                    String email = parts[2].trim();

                    User user = new User(username, password, email);
                    users.add(user);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }
    }

    private static void saveUsersToFile(String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

            for (User user : users) {
                writer.write(user.getUsername() + ", " + user.getPassword() + ", " + user.getEmail());
                writer.newLine();
            }

            writer.close();
            System.out.println("User data saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving user data to file: " + filename);
        }
    }
    private static void loadCatalogItemsFromFile(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length == 5) {
                    String name = parts[0].trim();
                    double price = Double.parseDouble(parts[1].trim());
                    String description = parts[2].trim();
                    String category = parts[3].trim();
                    String type = parts[4].trim();

                    CatalogItem item = new CatalogItem(name, price, description, category, type);
                    catalogItems.add(item);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    private static void saveCatalogItemsToFile(String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

            for (CatalogItem item : catalogItems) {
                writer.write(item.getName() + ", " + item.getPrice() + ", " + item.getDescription() +
                        ", " + item.getCategory() + ", " + item.getType());
                writer.newLine();
            }

            writer.close();
            System.out.println("Catalog item data saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving catalog item data to file: " + filename);
        }
    }

    public static List<CatalogItem> getCatalogItems() {
        return catalogItems;
    }

    public static void addItemToCatalog(CatalogItem item) {
        catalogItems.add(item);
        saveCatalogItemsToFile(CATALOG_FILE);
    }
}
