package restaurant;

import java.util.*;
import java.util.Scanner;

import restaurant.utils.ConsoleColors;//This file is located in utils

public class App {

//STRING
    public static String prompt(String question){
        System.out.println(question);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }
//INTEGERS
    public static int promptInt(String question){
        String input = prompt(question);
        int parsedInput = Integer.parseInt(input);
        return parsedInput;
    }
    
    //We can handle the 'view' here (◑‿◐)
    public static void main(String[] args) {
        System.out.println(ConsoleColors.RED + "RED COLORED" +
                ConsoleColors.RESET + " NORMAL");
        //This is just an example of how we can now add colored text

        //The added writer details for menuManagement 
        List<String[]> menuData = new ArrayList<String[]>();
            menuData.add(new String[] { "Name", "Description", "Preparation Time", "Item Price", "Ingredients" });
            menuData.add(new String[] {"Coffee Abomination", "Delicious tonic water, coffee beverage with two shots of espresso", "3 minutes", "$8.00", "1 can of tonic water", "Cherry syrup", "2(oz) shots of espresso"});
            menuData.add(new String[] {"Oat Milk Latte", "Two shots of espresso served over Ice or Hot with oat milk", "2 minutes", "$5.00", "2(oz) shots of espresso, Oat milk, possibly Iced"});
            menuData.add(new String[] {"Almond Milk Latte", "Two shots of espresso served over Ice or Hot with almond milk", "2 minutes", "$5.00", "2(oz) shots of espresso, Almond milk, possibly Iced"});
    }

}
