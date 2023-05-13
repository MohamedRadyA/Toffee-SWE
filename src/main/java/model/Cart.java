package model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<CatalogItem, Integer> items = new HashMap<>();
    private double cartTotal = 0;

    public Map<CatalogItem, Integer> getItems() {
        return items;
    }

    public void putItem(CatalogItem item, int quantity) {
        items.put(item, quantity);
    }

    public double getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(double cartTotal) {
        this.cartTotal = cartTotal;
    }

}
