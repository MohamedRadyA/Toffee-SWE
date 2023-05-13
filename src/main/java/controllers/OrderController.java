package controllers;

import model.CatalogItem;
import model.Order;

import java.util.List;
import java.util.Map;

public class OrderController {
    private Order currentOrder;

    public void placeOrder(Map<CatalogItem, Integer> cartItems, double cartTotal) {
        currentOrder = new Order(cartItems, cartTotal);
    }

    public boolean isOrderPaid() {
        return currentOrder.isPaid();
    }

    public void setOrderPaid(boolean isPaid) {
        currentOrder.setPaid(isPaid);
    }

    public boolean isOrderDelivered() {
        return currentOrder.isDelivered();
    }

    public void setOrderDelivered(boolean isDelivered) {
        currentOrder.setDelivered(isDelivered);
    }
    public void closeOrder() {
        if (currentOrder != null && currentOrder.isPaid()) {
            currentOrder.setDelivered(true);
            System.out.println("Order closed.");
        } else {
            System.out.println("Order cannot be closed.");
        }
    }
}