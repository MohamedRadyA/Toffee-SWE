package controllers;

import model.CatalogItem;

import java.util.List;
import java.util.Scanner;

public class CatalogController {

    private CartController cartController;

    public CatalogController(CartController cartController) {
        this.cartController = cartController;
    }

    public List<CatalogItem> getCatalog() {
        return Database.getCatalogItems();
    }

    public void displayCatalogItems() {
        int index = 1;
        for (CatalogItem item : Database.getCatalogItems()) {
            System.out.println(index++ + ". " + item.getName() + " - " + item.getPrice() + "\n");
        }
    }

    public void addToCart() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the item's index in the catalog:\n");
        int ind = sc.nextInt();
        if (ind > Database.getCatalogItems().size() || ind < 1) {
            System.out.println("That item does not exist.\n");
            return;
        }
        System.out.println("Enter the quantity you would like to add:\n");
        int quantity = sc.nextInt();
        cartController.addItemToCart(Database.getCatalogItems().get(ind - 1), quantity);
    }

    public void viewCatalog() {
        displayCatalogItems();
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Add item to cart\n2. Back to main menu\n3. Exit\n");
        int option = sc.nextInt();
        switch (option) {
            case 1: addToCart(); break;
            case 2: break;
            default:
                System.exit(0);
        }
    }


}