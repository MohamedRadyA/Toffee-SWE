package controllers;

import model.Cart;
import model.CatalogItem;

import java.util.Map;
import java.util.Scanner;

public class CartController {
    private Cart cart;

    private final int LOOSE_MAX = 10, PACKAGE_MAX = 15;
    private OrderController orderController;

    public CartController() {
        cart = new Cart();
        orderController = new OrderController();
    }

    public void addItemToCart(CatalogItem item, int quantity) {
        int oldQuantity = 0, maxQuantity = (item.getType() == "Loose" ? LOOSE_MAX : PACKAGE_MAX);
        if (cart.getItems().containsKey(item))
            oldQuantity = cart.getItems().get(item);

        if (quantity < 0) {
            System.out.println("Invalid quantity, please enter a positive quantity.\n");
            return;
        }

        if (quantity + oldQuantity > maxQuantity) {
            cart.putItem(item, maxQuantity);
            cart.setCartTotal(cart.getCartTotal() + item.getPrice() * (maxQuantity - oldQuantity));
            System.out.println("The maximum quantity allowed for this product is: " + maxQuantity
                    + ", your cart contains the amount now.\n");
            return;
        }

        cart.putItem(item, quantity);
        cart.setCartTotal(cart.getCartTotal() + item.getPrice() * quantity);
        System.out.println("Added " + quantity + "x " + item.getName() + " to cart.\n");
        displayCart();
    }

    public void removeItem(CatalogItem item, int quantity) {
        int oldQuantity = cart.getItems().get(item);

        if (quantity < 0) {
            System.out.println("Invalid quantity, please enter a positive quantity.\n");
            return;
        }

        if (oldQuantity - quantity < 0) {
            cart.getItems().remove(item);
            cart.setCartTotal(cart.getCartTotal() -  item.getPrice() * oldQuantity);
            System.out.println("The whole product has been removed from your cart.\n");
            return;
        }

        cart.getItems().remove(item);
        cart.putItem(item, oldQuantity - quantity);
        cart.setCartTotal(cart.getCartTotal() - item.getPrice() * quantity);
        System.out.println("Removed " + quantity + "x " + item.getName() + " from your cart.\n");
        displayCart();
    }

    public void removeFromCart() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the item's index in the shopping cart:\n");
        int ind = sc.nextInt();
        if (ind > cart.getItems().size() || ind < 1) {
            System.out.println("That item does not exist.\n");
            return;
        }
        int index = 1;
        CatalogItem itemToRemove = null;
        for (Map.Entry item : getCartItems().entrySet()) {
            if (index++ != ind)continue;
            itemToRemove = (CatalogItem) item.getKey();
        }
        System.out.println("Enter the quantity you would like to remove:\n");
        int quantity = sc.nextInt();
        removeItem(itemToRemove, quantity);
    }
    public void viewCart() {
        displayCart();
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Remove item from cart\n2. Place order\n3. Back to main menu\n4. Exit\n");
        int option = sc.nextInt();
        switch (option) {
            case 1: removeFromCart(); break;
            case 2: placeOrder(); break;
            case 3: break;
            default:
                System.exit(0);
        }
    }

    public Map<CatalogItem, Integer> getCartItems() {
        return cart.getItems();
    }

    public void displayCart() {
        System.out.println("Index - Name - Quantity - Price\n");
        int index = 1;
        for (Map.Entry item : getCartItems().entrySet()) {
            String name = ((CatalogItem) item.getKey()).getName();
            int quantity = ((Integer) item.getValue());
            double price = ((CatalogItem) item.getKey()).getPrice();
            System.out.println(index++ + " - " + name + " - " + quantity + " - " + price * quantity + "\n");
        }

        System.out.println("Total: " + cart.getCartTotal());
    }

    public void placeOrder() {
        orderController.placeOrder(cart.getItems(), cart.getCartTotal());
        System.out.println("Order placed.");
        cart = new Cart();
    }

    public boolean isOrderPaid() {
        return orderController.isOrderPaid();
    }

    public void setOrderPaid(boolean isPaid) {
        orderController.setOrderPaid(isPaid);
    }

    public boolean isOrderDelivered() {
        return orderController.isOrderDelivered();
    }

    public void setOrderDelivered(boolean isDelivered) {
        orderController.setOrderDelivered(isDelivered);
    }
}