package restaurant.controllers;
import java.util.ArrayList;
import java.util.List;

import restaurant.models.InventoryItem;

public class InventoryManagement {

    //Methods to maintain list of ingredients as well as
    // track ingredient usage, update quantities, and generate
    // alerts for low inventory
    List<InventoryItem> listOfIngredients = new ArrayList<>();

    public void addIngredient(InventoryItem item){
        listOfIngredients.add(item);
    }
    public void addIngredient(String itemName, int quantity, int threshold){
        InventoryItem item = new InventoryItem(itemName, quantity, threshold);
        listOfIngredients.add(item);
    }
    public void useIngredient(String ingredientName){
        listOfIngredients.stream().filter(ingredient -> ingredient.getItemName() == ingredientName).forEach(ingredient -> {
            ingredient.setQuantity(ingredient.getQuantity() - 1); 
            if (ingredient.getQuantity() <= ingredient.getThreshold()){
                System.out.println("Alert--- Only " + ingredient.getQuantity() +" left in inventory");
            }
        });

    }
    //check inventory to verify all items & a function to get a certain ingredient using the name to fi


    //    InventoryItem CA = new InventoryItem("Coffee Abomination", 25, 5);
    //    InventoryItem OML = new InventoryItem("Oat Milk Latte", 25,5);
   
}
