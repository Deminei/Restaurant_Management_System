package restaurant.controllers;

import restaurant.models.MenuItem;
import restaurant.models.Order;
import restaurant.models.User;
import restaurant.utils.PasswordHasher;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static restaurant.models.User.Role.*;
import static restaurant.utils.PasswordHasher.hashPassword;

public class UserLogin {
    //Add methods and hashing algorithms

//  Creates method that reads users.txt file checking for username and password matches.
    public static void findUser(){
        System.out.println("Enter username: ");
        Scanner scanner = new Scanner(System.in);
        String employeeUserName = scanner.nextLine();

        System.out.println("Enter password: ");
        String employeePassword = scanner.nextLine();

        String filepath = "src/main/java/restaurant/utils/users.txt";

        try(BufferedReader reader = new BufferedReader(new FileReader(filepath))){
            String line;
            while((line = reader.readLine()) != null){
                if(line.contains(employeeUserName)){
                    String [] userData = line.split(";");
                    if (userData[0].equals(employeeUserName) && userData[1].equals(employeePassword) && userData[2].equals("STAFF")){
                        staffOptions();
                    }else if(userData[0].equals(employeeUserName) && userData[1].equals(employeePassword) && userData[2].equals("MANAGER")){
                        managerOptions();
                    }else{
                        System.out.println("User not found. Please try again.");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//  Creates method that writes username and passwords to uses.txt
    public static void storeUserData(){
        String filepath = "src/main/java/restaurant/utils/users.txt";
        List<User> users = new ArrayList<>();
        User Cierra = new User("awesomecierra", hashPassword("awesomeSAUCE"), STAFF);
        User Mikhal = new User("Deminei",hashPassword("JAVAg0d"),STAFF);
        User Elizabeth = new User("Ekdrobinski",hashPassword("elizaB3st"), MANAGER);


        users.add(Cierra);
        users.add(Mikhal);
        users.add(Elizabeth);

        try{
            File outputFile = new File(filepath);
            if (outputFile.createNewFile()){
                System.out.println("File created: " + outputFile.getName());
            } else {
                System.out.println("File already exists. File will be updated.");
            }

            BufferedWriter writeUserData = new BufferedWriter(new FileWriter(filepath));
            for( User user : users){
                writeUserData.write(user.toString());
                writeUserData.newLine();
            }

            writeUserData.close();
            System.out.println("File has been updated.");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void staffOptions(){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        TableManagement tableManagement = new TableManagement();
        OrderProcessing orderProcessing = new OrderProcessing();
        InventoryManagement inventoryManagement = new InventoryManagement();


        while(running) {
            System.out.println("Enter 1 to assign guest to a table.");
            System.out.println("Enter 2 to access to place guest order.");
            System.out.println("Press 3 to edit restaurant inventory.");
            System.out.println("Enter 0 to exit.");

            int optionSelected = Integer.valueOf(scanner.nextLine());

            switch(optionSelected){
                case 1:
//                  Call table manager function
                    break;
                case 2:
                    // Call Order function
                    placeGuestOrder(orderProcessing);
                    break;
                case 3:
                    // Restaurant inventory function?
                    manageInventory(inventoryManagement);

                    break;
                default:
                    System.out.println("Logging out. Goodbye.");
                    running = false;
                    break;
            }
        }

    }

    public static void managerOptions(){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Instantiate the TableManager class
        TableManagement tableManagement = new TableManagement();
        OrderProcessing orderProcessing = new OrderProcessing();
        InventoryManagement inventoryManagement = new InventoryManagement();
        MenuManagement menuManagement = new MenuManagement();


        while(running) {
            System.out.println("Enter 1 to assign guest to a table.");
            System.out.println("Enter 2 to access to place guest order.");
            System.out.println("Press 3 to edit restaurant inventory.");
            System.out.println("Press 4 to edit the menu.");
            System.out.println("Press 5 to get a sales report.");
            System.out.println("Enter 0 to exit.");

            int optionSelected = Integer.valueOf(scanner.nextLine());

            switch(optionSelected){
                case 1:
//                  Call table manager function
                    break;
                case 2:

                    // Call Order function
                    placeGuestOrder(orderProcessing);
                    break;
                case 3:
                    // Restaurant inventory function?
                    manageInventory(inventoryManagement);
                    break;
                case 4:
                    // Edit menu function
                    manageMenu(menuManagement);
                    break;
                case 5:
                    // Generate sales report

                    break;

                default:
                    System.out.println("Logging out. Goodbye.");
                    running = false;
                    break;
            }
        }

    }
    public static void main(String[] args){
        storeUserData();
        findUser();

    }

    public static void placeGuestOrder(OrderProcessing orderProcessing){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter order number: ");
        int orderID =  Integer.parseInt(scanner.nextLine());

        System.out.println("Enter items being ordered: ");

    }

    public static void manageInventory(InventoryManagement inventoryManagement){

    }

    public static void manageMenu(MenuManagement menuManagement){

        menuManagement.fileReader();

        boolean menuAction = true;
        while(menuAction){
            Scanner scanner = new Scanner(System.in);
            System.out.println("What would you like to do with the menu?");
            System.out.println("Enter 1 to add item.");
            System.out.println("Enter 2 to delete item.");
            System.out.println("Enter 3 to edit item.");
            System.out.println("Enter 0 to exit.");

            int doToMenu = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter name of the item: ");
            String itemName = scanner.nextLine();

            System.out.println("Enter description of the item: ");
            String itemDescription = scanner.nextLine();

            System.out.println("Enter prep time of the item: ");
            int itemPrepTime = Integer.valueOf(scanner.nextLine());

            System.out.println("Enter price of the item: ");
            Double itemPrice = Double.valueOf(scanner.nextLine());

            System.out.println("Enter ingredients of the item: ");
            String itemIngredients = scanner.nextLine();

            MenuItem menuItem = new MenuItem(itemName, itemDescription, itemPrepTime, itemPrice, itemIngredients);

            switch (doToMenu){
                case 1:
                    menuManagement.add(menuItem);
                    break;
                case 2:
                    menuManagement.delete(menuItem);
                    break;
                case 3:
                    menuManagement.edit(menuItem);
                    break;
                default:
                    System.out.println("Exiting menu management. Goodbye.");
                    menuAction = false;
                    break;

            }

        }

    }

}
