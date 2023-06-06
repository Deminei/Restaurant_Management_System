package restaurant;

import java.util.*;
import java.util.Scanner;

import restaurant.controllers.MenuManagement;
import restaurant.models.MenuItem;
import restaurant.utils.ConsoleColors;//This file is located in utils

import static restaurant.controllers.MenuManagement.*;


public class App {

    //We can handle the 'view' here (◑‿◐)
    public static void main(String[] args) {
        System.out.println(ConsoleColors.RED + "RED COLORED" +
                ConsoleColors.RESET + " NORMAL");
        //This is just an example of how we can now add colored text

        //The added writer details for menuManagement 
//        List<String[]> menuData = new ArrayList<String[]>();
        MenuManagement menuData = new MenuManagement();
            menuData.add(new MenuItem("Coffee Abomination", "Delicious tonic water, coffee beverage with two shots of espresso",3, 8.00, Arrays.asList("1 can of tonic water", "Cherry syrup", "2(oz) shots of espresso")));
            menuData.add(new MenuItem("Oat Milk Latte", "Two shots of espresso served over Ice or Hot with oat milk", 2, 5.00, Arrays.asList("2(oz) shots of espresso, Oat milk, possibly Iced")));
            menuData.add(new MenuItem("Almond Milk Latte", "Two shots of espresso served over Ice or Hot with almond milk",2, 5.00, Arrays.asList("2(oz) shots of espresso, Almond milk, possibly Iced")));

        fileReader();
    }

}
