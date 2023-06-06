package restaurant.controllers;
import java.util.ArrayList;
import java.util.List;

import restaurant.models.InventoryItem;

public class InventoryManagement {

    //Methods to maintain list of ingredients as well as
    // track ingredient usage, update quantities, and generate
    // alerts for low inventory
    //Also implements File I/O to load/save ingredient data from file
    List<InventoryItem> listOfIngredients = new ArrayList<>();

    public void addIngredient(InventoryItem item){
        listOfIngredients.add(item);
    }

    public void addIngredient(String itemName, int quantity, int threshold){
        InventoryItem item = new InventoryItem(itemName, quantity, threshold);
        listOfIngredients.add(item);
    }
    // public void useIngredient(InventoryItem item){
    //     listOfIngredients.stream().filter(item -> item.equals(item)).forEach(System.out::println);

    // }

    //    public void ingredientsList(){
    //    InventoryItem CA = new InventoryItem("Coffee Abomination", 25, 50);
    //    InventoryItem OML = new InventoryItem("Oat Milk Latte", 25,50);

    //    if (InventoryItem.getThreshold >= 2){
    //        System.out.println("Alert--- Only 2 left in inventory");
    //    }
//    }
}
