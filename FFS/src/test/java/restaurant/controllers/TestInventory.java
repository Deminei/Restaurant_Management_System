//Feature chosen to test: Test.addInventory()
package restaurant.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.models.InventoryItem;

import restaurant.controllers.InventoryManagement;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList; // Import ArrayList class from java.util
class InventoryTest {

    private InventoryManagement inventoryManagement;
    private List<InventoryItem> listOfIngredients;

    @BeforeEach
    void setUp() {
        inventoryManagement = new InventoryManagement();
        listOfIngredients = new ArrayList<>();
        inventoryManagement.listOfIngredients = listOfIngredients;
    }



    @Test
    void testAddInitialIngredient() {
        // Arrange
        InventoryManagement inventoryManagement = new InventoryManagement();
        String itemName = "Coffee";
        int quantity = 10;
        int threshold = 5;

        // Act
        inventoryManagement.addInitialIngredient(itemName, quantity, threshold);

        // Assert
        Assertions.assertEquals(1, inventoryManagement.listOfIngredients.size());
        Assertions.assertEquals(itemName, inventoryManagement.listOfIngredients.get(0).getItemName());
        Assertions.assertEquals(quantity, inventoryManagement.listOfIngredients.get(0).getQuantity());
        Assertions.assertEquals(threshold, inventoryManagement.listOfIngredients.get(0).getThreshold());
    }
    @Test
    void testAddNewIngredient() {
        // Arrange
        InventoryManagement inventoryManagement = new InventoryManagement();
        String itemName = "Milk";
        int quantity = 5;
        int threshold = 3;

        // Act
        inventoryManagement.addNewIngredient(itemName, quantity, threshold);

        // Assert
        Assertions.assertEquals(1, inventoryManagement.listOfIngredients.size());
        Assertions.assertEquals(itemName, inventoryManagement.listOfIngredients.get(0).getItemName());
        Assertions.assertEquals(quantity, inventoryManagement.listOfIngredients.get(0).getQuantity());
        Assertions.assertEquals(threshold, inventoryManagement.listOfIngredients.get(0).getThreshold());
    }

    @Test
    void testUseIngredient() {
        // Arrange
        InventoryManagement inventoryManagement = new InventoryManagement();
        inventoryManagement.listOfIngredients.add(new InventoryItem("Coffee", 10, 5));
        String itemName = "Coffee";

        // Act
        inventoryManagement.useIngredient(itemName);

        // Assert
        Assertions.assertEquals(9, inventoryManagement.listOfIngredients.get(0).getQuantity());
    }

}
