package restaurant.controllers;

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
//                  Call Order function
                    break;
                case 3:
//                  Restaurant inventory function?
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
//                  Call Order function
                    break;
                case 3:
//                  Restaurant inventory function?
                    break;
                case 4:
//                  edit menu function
                    break;
                case 5:
//                generate sales report
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
}
