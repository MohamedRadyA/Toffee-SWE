package model;

import java.util.List;
import java.util.Map;

public class Order {
    private Map<CatalogItem, Integer> items;
    private double totalCost;
    private boolean isPaid;
    private boolean isDelivered;

    public Order(Map<CatalogItem, Integer> items, double totalCost) {
        this.items = items;
        this.totalCost = totalCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean isDelivered) {
        this.isDelivered = isDelivered;
    }
}