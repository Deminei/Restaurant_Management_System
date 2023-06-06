package restaurant.controllers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TableManagement {
    //methods to maintain list of tables in memory
    // and methods to check table availability, assign customers
    // to table, and update table status
    //Implement File I/O to load/save table info from file
    private List<Table> tables;
    public TableManagement() {
        tables = new ArrayList<>();
    }
    public void addTable(Table table) {
        tables.add(table);
    }
    public void removeTable(Table table) {
        tables.remove(table);
    }
    public List<Table> getTables() {
        return tables;
    }
    public List<Table> getAvailableTables(int partySize) {
        List<Table> availableTables = new ArrayList<>();
        for (Table table : tables) {
            if (table.getStatus() == TableStatus.AVAILABLE && table.getTableSize() >= partySize) {
                availableTables.add(table);
            }
        }
        return availableTables;
    }
    public void assignTableToCustomer(Table table, Customer customer) {
        table.setStatus(TableStatus.OCCUPIED);
        table.setCustomer(customer);
        customer.setTable(table);
    }
    public void freeTable(Table table) {
        table.setStatus(TableStatus.AVAILABLE);
        table.getCustomer().setTable(null);
        table.setCustomer(null);
    }
    public void saveTableInfo(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Table table : tables) {
                writer.println(table.getTableId() + "," + table.getTableSize() + "," + table.getStatus());
            }
            System.out.println("Table information saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving table information: " + e.getMessage());
        }
    }
    public void loadTableInfo(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int tableId = Integer.parseInt(parts[0]);
                    int tableSize = Integer.parseInt(parts[1]);
                    TableStatus status = TableStatus.valueOf(parts[2]);
                    Table table = new Table(tableId, tableSize, status);
                    tables.add(table);
                }
            }
            System.out.println("Table information loaded from " + filename);
        } catch (IOException e) {
            System.out.println("Error loading table information: " + e.getMessage());
        }
    }
    public enum TableStatus {
        AVAILABLE,
        RESERVED,
        OCCUPIED
    }
    public static class Table {
        private int tableId;
        private int tableSize;
        private TableStatus status;
        private Customer customer;
        public Table(int tableId, int tableSize, TableStatus status) {
            this.tableId = tableId;
            this.tableSize = tableSize;
            this.status = status;
        }
        public int getTableId() {
            return tableId;
        }
        public int getTableSize() {
            return tableSize;
        }
        public TableStatus getStatus() {
            return status;
        }
        public void setStatus(TableStatus status) {
            this.status = status;
        }
        public Customer getCustomer() {
            return customer;
        }
        public void setCustomer(Customer customer) {
            this.customer = customer;
        }
    }
    public static class Customer {
        private String name;
        private Table table;
        public Customer(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
        public Table getTable() {
            return table;
        }
        public void setTable(Table table) {
            this.table = table;
        }
    }
}
