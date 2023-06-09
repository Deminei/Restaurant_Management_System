package restaurant.controllers;
import restaurant.models.*;
import restaurant.utils.ConsoleColors;
import java.util.Scanner;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class OrderProcessing {
    private List<Order> totalOrders;
    private List<Order> ordersInWaiting;
    private List<Order> ordersInPrep;
    private List<Order> ordersComplete;
    private Map<Integer, String> orderStatus;
    private ExecutorService executorService;

    public OrderProcessing() {
        totalOrders = new ArrayList<>();
        ordersInWaiting = new ArrayList<>();
        ordersInPrep = new ArrayList<>();
        ordersComplete = new ArrayList<>();
        orderStatus = new HashMap<>();
        executorService = Executors.newFixedThreadPool(5);
    }

    public void placingOrder(TableManagement tableManagement, MenuManagement menu, InventoryManagement inventory) {

        Scanner scanner = new Scanner(System.in);
        int orderID = totalOrders.size();


        Order newOrder;

        // Select an occupied table
        tableManagement.checkTables();
        System.out.println("Enter the ID of the table where this order will be going: ");
        int tableID = Integer.parseInt(scanner.nextLine());


        for (Table table : tableManagement.tables) {
            if (table.getTableId() == tableID) {
                boolean running = true;
                while (running) {
                    // Display the menu
                    System.out.println("Menu:");
                    menu.displayMenuItems();

                    //Prompt the guest to select an item
                    System.out.println("Enter the number corresponding to the item you want to order (or 0 to finish ordering):");
                    int itemNumber = Integer.parseInt(scanner.nextLine());


                    if (itemNumber == 0) {
                        running = false;
                    } else if (itemNumber < 0 || itemNumber > menu.getMenuItems().size()) {
                        System.out.println("Invalid item number. Please try again.");
                    } else {
                        MenuItem menuItemData = menu.getMenuItems().get(itemNumber - 1);
                        String itemName = menuItemData.getName();
                        String description = menuItemData.getDescription();
                        double price = menuItemData.getPrice();

                        // Prompt the guest to enter the quantity
                        System.out.println("Enter the quantity:");
                        int quantity = Integer.parseInt(scanner.nextLine());

                        ArrayList<MenuItem> itemsToAdd = new ArrayList<>();
                        //Create the list of items to order
                        for (MenuItem item : menu.getMenuItems()) {
                            if (item.getName().equals(itemName)) {
                                itemsToAdd.add(item);
                                System.out.println("Item added to the order.");
                            }
                        }
                        System.out.println("Order" + orderID + ": " + itemsToAdd);


                        double totalPrice = 0;

                        for (MenuItem item : itemsToAdd) {
                            totalPrice += item.getPrice() * quantity;
                        }

                        newOrder = new Order(orderID, itemsToAdd, totalPrice, "WAITING", tableID);
                        totalOrders.add(newOrder);
                        System.out.println("Order total?: " + newOrder.getItems() + " \nTotal Price:" + newOrder.getTotalPrice());
                    }
                }
            }
        }
        scanner = new Scanner(System.in);
        System.out.println("What is the order number of the order to process?");

        int orderNumber = Integer.valueOf(scanner.nextLine());// ಥ‿ಥ

        for (Order order : totalOrders) {
            if (order.getOrderId() == orderNumber) {
                executorService.submit(() -> processOrder(order, inventory, menu));
            }
        }

    }

    private void processOrder(Order order, InventoryManagement inventory, MenuManagement menu) {

        try {
            Thread.sleep(2000); // Sleep for 2 seconds to simulate processing time
            System.out.println(ConsoleColors.PURPLE_BOLD + "Sent order #"+order.getOrderId()+" to the kitchen" + ConsoleColors.RESET);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Order processing interrupted.");
            return;
        }
        // Update order status
        order.setStatus("PREPARING");
        System.out.println(ConsoleColors.BLUE_BACKGROUND_BRIGHT + "Order #"+order.getOrderId()+" is being prepared..." + ConsoleColors.RESET);

        try {
            Thread.sleep(5000);// Sleep for 5 seconds to simulate processing time.
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Kitchen is having a strike!");
            return;
        }
        // Update order status to "Ready" when processing is complete
        order.setStatus("COMPLETE");// (っ˘ڡ˘ς)
        System.out.println(ConsoleColors.GREEN_BACKGROUND + "Order #"+order.getOrderId()+" is ready" + ConsoleColors.RESET);

        for (MenuItem item : order.getItems()) {
            for (int i = 0; i < item.getIngredients().size(); i++) {
                String itemName = item.getIngredients().get(i);
                List<String> ingredients = new ArrayList<>();
                ingredients.add(itemName);
                        System.out.println(itemName);
                inventory.useIngredient(itemName);

                if((menu.getMenuItems().equals(itemName))){

                    for(String ingredient : item.getIngredients())
                        inventory.useIngredient(ingredient);
                    }
                }
            }

        }

    public List<Order> getCompletedOrders() {
        List<Order> completedOrders = totalOrders;
        for (Order order : completedOrders) {
            if (orderStatus.getOrDefault(order.getOrderId(), "").equals("Order is ready!")) {
                completedOrders.add(order);
            }
        }
        return completedOrders;
    }

}

// going to bed now ᇂ_ᇂ