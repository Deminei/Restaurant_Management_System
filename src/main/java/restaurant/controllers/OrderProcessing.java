package restaurant.controllers;
import restaurant.models.MenuItem;
import restaurant.models.Order;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderProcessing {
    private final List<Order> activeOrders;
    private Map<Integer, String> orderStatus;
    private ExecutorService executorService;

    public OrderProcessing() {
        activeOrders = new ArrayList<>();
        orderStatus = new HashMap<>();
        executorService = Executors.newFixedThreadPool(5); // Set the number of threads as desired
    }
    public void createOrder(Order order) {
        activeOrders.add(order);
        orderStatus.put(order.getOrderId(), "Waiting");

        // Process the order asynchronously
        executorService.submit(() -> processOrder(order));
    }
    private void processOrder(Order order) {
        // Simulate order processing time
        try {
            Thread.sleep(2000); // Sleep for 2 seconds to simulate processing time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Order processing interrupted.");
            return;
        }
        // Update order status
        updateOrderStatus(order.getOrderId(), "Preparing order.");

        // Calculate total price
        double totalPrice = calculateTotalPrice(order.getOrderId());

        // Perform other order processing tasks
        // such as updating inventory, notifying staff, generating reports
        try {
            Thread.sleep(5000);// Sleep for 5 seconds to simulate processing time.
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Kitchen is having a strike!");
            return;
        }
        // Update order status to "Ready" when processing is complete
        updateOrderStatus(order.getOrderId(), "Order is ready!");

        // ?? additional tasks after order processing is complete
    }
    public void updateOrderStatus(int orderId, String newStatus) {
        for (Order order : activeOrders) {
            if (order.getOrderId() == orderId) {
                order.setStatus(newStatus);
                break;
            }
        }
    }
    public double calculateTotalPrice(int orderId) {
        for (Order order : activeOrders) {
            if (order.getOrderId() == orderId) {
                double totalPrice = 0.0;
                for (MenuItem item : order.getItems()) {
                    totalPrice += item.getPrice();
                }
                return totalPrice;
            }
        }
        return 0.0; // Order not found
    }
    public List<Order> getOrdersByStatus(String status) {
        List<Order> ordersByStatus = new ArrayList<>();
        for (Order order : activeOrders) {
            if (order.getStatus().equals(status)) {
                ordersByStatus.add(order);
            }
        }
        return ordersByStatus;
    }
    public Order getOrderById(int orderId) {
        for (Order order : activeOrders) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        return null; // Order not found
    }
    public void removeOrder(int orderId) {
        Order orderToRemove = null;
        for (Order order : activeOrders) {
            if (order.getOrderId() == orderId) {
                orderToRemove = order;
                break;
            }
        }
        if (orderToRemove != null) {
            activeOrders.remove(orderToRemove);
        }
    }
    public void clearAllOrders() {
        activeOrders.clear();
    }
    public int getActiveOrderCount() {
        return activeOrders.size();
    }
    //??Additional methods?

    // Getter for activeOrders
    public List<Order> getActiveOrders() {
        return activeOrders;
    }
}
// going to bed now ᇂ_ᇂ