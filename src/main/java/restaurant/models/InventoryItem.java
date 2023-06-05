package restaurant.models;

public class InventoryItem {
    private String name;
    private int quantity;
    private int threshold;

    public InventoryItem(String name, int quantity, int threshold) {
        this.name = name;
        this.quantity = quantity;
        this.threshold = threshold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
