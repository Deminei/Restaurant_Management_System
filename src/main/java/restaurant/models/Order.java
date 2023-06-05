package restaurant.models;

import java.util.List;

public class Order {
    private int orderId;
    private List<MenuItem> items;
    private double totalPrice;
    private String status;

    public Order(int orderId, List<MenuItem> items, double totalPrice, String status) {
        this.orderId = orderId;
        this.items = items;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
