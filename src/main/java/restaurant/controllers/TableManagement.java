package restaurant.controllers;

import restaurant.models.Table;

import java.util.ArrayList;
import java.util.List;

public class TableManagement {
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
            if (table.getStatus() == Table.Status.AVAILABLE && table.getTableSize() >= partySize) {
                availableTables.add(table);
            }
        }
        return availableTables;
    }

    public void assignTableToCustomer(Table table, Table.Status status) {
        table.setStatus(String.valueOf(status));
    }

    public void freeTable(Table table) {
        table.setStatus(String.valueOf(Table.Status.AVAILABLE));
    }

    public static void main(String[] args) {
        // Example usage
        TableManagement tableManagement = new TableManagement();

        // Create tables
        Table table1 = new Table(1, 4, Table.Status.AVAILABLE);
        Table table2 = new Table(2, 6, Table.Status.RESERVED);
        Table table3 = new Table(3, 2, Table.Status.OCCUPIED);

        // Add tables to management
        tableManagement.addTable(table1);
        tableManagement.addTable(table2);
        tableManagement.addTable(table3);

        // Get available tables for a party size
        List<Table> availableTables = tableManagement.getAvailableTables(4);
        System.out.println("Available tables for party size 4: " + availableTables);

        // Free a table
        tableManagement.freeTable(table1);

        // Get all tables
        List<Table> allTables = tableManagement.getTables();
        System.out.println("All tables: " + allTables);
    }
}
