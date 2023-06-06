package restaurant.controllers;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import restaurant.models.MenuItem;
import java.util.Arrays;


public class MenuManagement {
    //Add methods to manage menu items in menu.txt
    //Implement File I/O to load and save menu items
    private static List<String[]> menuData = new ArrayList<String[]>();
    private static File file = new File("../utils/menutestcsv.csv");


    public List<String[]> fileReader(){
        try {
            FileReader outputReader = new FileReader(file);
            CSVReader reader = new CSVReader(outputReader);
            menuData = reader.readAll();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menuData;
    }
    public void fileWriter() {
        try {
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile, '|', CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.DEFAULT_ESCAPE_CHARACTER,CSVWriter.DEFAULT_LINE_END);
            writer.writeAll(menuData);
            writer.close();
            } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void main(String[] args){
        fileReader();
    }

    public void add(MenuItem item){
        menuData.add((new String[] {item.getName(), item.getDescription() , String.valueOf(item.getPreparationTime()),String.valueOf(item.getPrice()), item.getIngredients().toString() }));
        fileWriter();
    }
   public void delete(MenuItem item){
    menuData.remove((new String[] {item.getName(), item.getDescription() , String.valueOf(item.getPreparationTime()),String.valueOf(item.getPrice()), item.getIngredients().toString() }));
    fileWriter();
   }
   public void edit(MenuItem item){
    boolean running = true;
    while(running){
        int option = prompt("Pick from the list of options below", new String[]{
            "update the item's name",
            "update the item's description",
            "update the item's preparation time",
            "update the item's price",
            "Update the item's ingredients"
    });
 
    switch(option){
        case 1: 
        item.setName(prompt("What's the new name?"));
        break;
        case 2: item.setDescription(prompt("What would you like the new description to say?"));
        break;
        case 3: item.setPreparationTime(Integer.parseInt((prompt("What is the new preparation time?"))));
        break;
        case 4: item.setPrice(Double.parseDouble((prompt("What is the new price? EX:0.00"))));
        break;
        case 5: item.setIngredients(Arrays.asList(prompt("What are the new ingredients?").split("|")));
        break;
        default:
        System.out.println("Logging out. Goodbye!");
        running = false;
        break;
    }
    }
   }
public int prompt(String question, String[] options){
    System.out.println(question);
    for(int i = 0; i < options.length; i++){
        System.out.println(i + ": " + options[i]);
    }
    String input = prompt("");
    int parsedInput = Integer.parseInt(input);
    return parsedInput;
}
public String prompt(String question){
    System.out.println(question);
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    return input;
}
}
