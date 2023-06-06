package restaurant.controllers;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import restaurant.models.MenuItem;
import java.util.Arrays;


public class MenuManagement {
    //Add methods to manage menu items in menu.txt
    //Implement File I/O to load and save menu items
    private static List<String[]> menuData = new ArrayList<String[]>();
    private static File file = new File("../utils/menutestcsv.csv");


    public static List<String[]> fileReader(){
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
    public static void fileWriter() {
        try {
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile, '|', CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.DEFAULT_ESCAPE_CHARACTER,CSVWriter.DEFAULT_LINE_END);
            writer.writeAll(menuData);
            writer.close();
            } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
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

   }

}
