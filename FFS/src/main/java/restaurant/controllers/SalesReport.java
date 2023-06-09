package restaurant.controllers;
import restaurant.models.*;

import java.io.FileWriter;
import java.io.IOException;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Comparator;


public class SalesReport{
    private double dailyRevenue;

    public SalesReport() {
        this.dailyRevenue = 0.0;
    }

    public void generateSalesReport( OrderProcessing orderProcessing, MenuManagement menu) {
        // Calculate total revenue
        double totalRevenue = 0;
        for (Order order : orderProcessing.getCompletedOrders()) {
            if (order.getStatus().equals("COMPLETE")) {
                totalRevenue += order.getTotalPrice();
            }
        }

        // Calculate the most popular items
        Map<String, Integer> itemQuantityMap = new HashMap<>();
        for (Order order : orderProcessing.getCompletedOrders()) {
            if (order.getStatus().equals("COMPLETE")) {
                for (MenuItem item : order.getItems()) {
                    String itemName = item.getName();
                    itemQuantityMap.put(itemName, itemQuantityMap.getOrDefault(itemName, 0) + 1);
                }
            }
        }
        List<Map.Entry<String, Integer>> popularItems = new ArrayList<>(itemQuantityMap.entrySet());
        popularItems.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        List<String> mostPopularItems = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : popularItems) {
            mostPopularItems.add(entry.getKey());
        }

        // Get tables with the most orders
        Map<Integer, Integer> tableOrderCountMap = new HashMap<>();
        for (Order order : orderProcessing.getCompletedOrders()) {
            if (order.getStatus().equals("COMPLETE")) {
                int tableId = order.getTable();
                tableOrderCountMap.put(tableId, tableOrderCountMap.getOrDefault(tableId, 0) + 1);
            }
        }
        List<Map.Entry<Integer, Integer>> orderedTables = new ArrayList<>(tableOrderCountMap.entrySet());
        orderedTables.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        List<Integer> tablesWithMostOrders = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : orderedTables) {
            tablesWithMostOrders.add(entry.getKey());
        }

        // Create the sales report string
        StringBuilder report = new StringBuilder();
        report.append("Sales Report - Date: ").append(getCurrentDate()).append("\n");
        report.append("Total Revenue: $").append(totalRevenue).append("\n");
        report.append("Most Popular Items: ").append(mostPopularItems).append("\n");
        report.append("Tables with Most Orders: ").append(tablesWithMostOrders).append("\n");

        // Export the report as a text file
        String filename = "sales_report_" + getCurrentDate() + ".txt";
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(report.toString());
            System.out.println("Sales report exported successfully as " + filename);
        } catch (IOException e) {
            System.out.println("Failed to export sales report: " + e.getMessage());
        }
    }

    private String getCurrentDate() {
        // Replace this with the logic to get the current date in the desired format
        return "2023-06-09";
    }
}
//// I'm so tired rn ಥ‿ಥ