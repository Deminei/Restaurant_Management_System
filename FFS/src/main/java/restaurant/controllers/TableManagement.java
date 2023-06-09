package restaurant.controllers;

import restaurant.models.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TableManagement{
    public List<Table> tables;

    public TableManagement(){
        tables = new ArrayList<>();
    }

    public void addTableToRestaurant(int tableID, int tableSize, Table.Status tableStatus ){
        tables.add(new Table( tableID,  tableSize, tableStatus));
    }

    public void checkTables() {
        for (Table table : tables) {
            System.out.println("Table " + table.getTableId() + ": " + table.getTableSize() + "seats " + "Status: " + table.getStatus());
//            System.out.println(tables);

        }
    }

    public void editTable() {
        Scanner scanner = new Scanner(System.in);
        boolean editing = true;
        while (editing) {

            System.out.println("Press 1 to edit table status.");
            System.out.println("Press 0 to exit.");

            int userselected = Integer.parseInt(scanner.nextLine());

            switch (userselected) {
                case 1:

                            boolean running = true;

                            while (running) {
                                System.out.println("What is the ID of the table you would like to edit?");
                                System.out.println("Press 0 to exit");

                                int tableIDNumber = Integer.parseInt(scanner.nextLine());


                                if (tableIDNumber == 0){
                                    break;
                                }

                                for (Table table : tables) {

                                    if (table.getTableId() == tableIDNumber) {
                                System.out.println("Enter 1 change table status.");
                                System.out.println("Enter 0 to exit.");

                                int optionSelected = Integer.parseInt(scanner.nextLine());

                                switch (optionSelected) {
                                    case 1:
                                        System.out.println("Enter the new status of the table. ");
                                        String newStatus = scanner.nextLine();
                                        table.setStatus(newStatus);
                                        break;
                                    default:
                                        System.out.println("Logging out. Goodbye.");
                                        running = false;
                                        break;
                                }
                            }
                        }
                    }default:
                    System.out.println("Logging out. Goodbye.");
                    editing = false;
                    break;

            }
        }
    }

    public void assignGuestToTable() {
        // Find the table with the given table ID and set its status to OCCUPIED
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the party size: ");
        int partySize = Integer.parseInt(scanner.nextLine());

        List<Table> guestWillFit = new ArrayList<>();

        for (Table table : tables) {
            if (table.getTableSize() >= partySize) {
                guestWillFit.add(table);
            }
        }

        for (Table table : guestWillFit) {
            System.out.println(table.getTableId() + ": " + table.getTableSize() + " " + table.getStatus());
            System.out.println(tables);
        }

        editTable();
    }
}