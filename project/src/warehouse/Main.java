package warehouse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

  protected static Map<Integer, String> traversalTable = new HashMap<Integer, String>();
  /**
   * Main method
   * @param args Input
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    File translation = new File("translation.csv");
    String transPath = translation.getAbsolutePath();
    File traversal = new File("traversal_table.csv");
    String travePath = traversal.getAbsolutePath();
    List<String> transTable = readFromCSVFile(transPath);
    List<String> traveTable = readFromCSVFile(travePath);
    List<String> initialTable;
    List<String> finalTable;
    List<String> orders;
    List<String> commands;
    Scanner scanner = new Scanner(System.in);
    File initialFile = new File("initial.csv");
    initialFile = initialFile.getAbsoluteFile();
    if(!initialFile.exists()){
      writeCSVFile("initial.csv");
    }
    String commandFileName;
    String initialPath = initialFile.getAbsolutePath();
    File commandFile = null;
    boolean hasFile = false;
    while(!hasFile){
      System.out.print("Enter the name of command file: ");
      commandFileName = scanner.next();
      commandFile = new File(commandFileName);
      commandFile = commandFile.getAbsoluteFile();
      if(commandFile.exists()) {
        hasFile = true;
        System.out.println("Valid input file " + commandFile.getAbsolutePath());
      } else{
        System.out.println("Invalid input file");
      }
    }
    scanner.close();
    initialTable = readFromCSVFile(initialPath);
    commands = readFile(commandFile.getAbsolutePath());
    Warehouse warehouse = new Warehouse(Integer.TYPE);
    TranslationTable translationtable = new TranslationTable(transTable);
    warehouse.genrateTraversal(traveTable);
    warehouse.generateInventory(initialTable);
    Main.traversalTable = warehouse.getTraversal();
    WareHouseManager wareHouseManager = new WareHouseManager(warehouse, translationtable);
    for(String command : commands){
      System.out.println(command);
      wareHouseManager.command(command);
    }
    finalTable = wareHouseManager.finalStock();
    writeCSVFile("final.csv", finalTable, false);
    writeCSVFile("initial.csv", finalTable, false);
    orders = wareHouseManager.ordersOnTruck();
    writeCSVFile("orders.csv", orders, false);
  }
  
  /**
   * Reads from a .csv file
   * @param filePath The path of this file.
   * @return A list of all lines.
   * @throws FileNotFoundException
   */
  public static List<String> readFromCSVFile(String filePath) throws FileNotFoundException {
    Scanner scanner = new Scanner(new FileInputStream(filePath));
    ArrayList<String> record = new ArrayList<String>();
    while(scanner.hasNextLine()){
      record.add(scanner.nextLine());
      if(record.size() == 1){
        String[] line = record.get(0).split(",");
        try{
          @SuppressWarnings("unused")
          int num = Integer.parseInt(line[2]);
          num = Integer.parseInt(line[3]);
        } catch(NumberFormatException ex){
          record.remove(0);
        } catch(IndexOutOfBoundsException ex) {
          record.remove(0);
        }
      }
    }
    scanner.close();
    return record;
  }
  
  /**
   * Creates or Writes an empty .csv file
   * @param filePath The path of this file.
   * @throws IOException
   */
  public static void writeCSVFile(String filePath) throws IOException {
    FileWriter writer = new FileWriter(filePath, false);
    writer.flush();
    writer.close();
  }
  
  /**
   * Creates or Writes a .csv file.
   * @param filePath The path of this file.
   * @param contents The contents to be written.
   * @param appends To append the file or not.
   * @throws IOException
   */
  public static void writeCSVFile(String filePath, List<String> contents, boolean appends) throws IOException {
     FileWriter writer = new FileWriter(filePath, false);
     for(String line : contents){
       writer.append(line);
       writer.append("\n");
     }
     writer.flush();
     writer.close();
    LoadingManager load = new LoadingManager();
    load.addTruck();
    System.out.print(load.getTrucksAvailable());
    load.addTruck();
    System.out.print(load.getTrucksAvailable());
  }
  
  /**
   * Reads a .txt file.
   * @param filePath The path of this file.
   * @return A list of all lines.
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static List<String> readFile(String filePath) throws FileNotFoundException, IOException {
    FileReader fileReader = new FileReader(filePath);
    BufferedReader buffer = new BufferedReader(fileReader);
    String line;
    List<String> file = new ArrayList<String>();
    while((line = buffer.readLine()) != null) {
      file.add(line);
    }
    buffer.close();
    return file;
  }
  
  /**
   * Creates or writes a .txt file.
   * @param filePath The path of this file.
   * @param contents The contents to be written.
   * @param appends To append the file or not.
   * @throws IOException
   */
  public static void writeFile(String filePath, List<String> contents, boolean appends) throws IOException {
    FileWriter fileWriter = new FileWriter(filePath, appends);
    BufferedWriter buffer = new BufferedWriter(fileWriter);
    for(String line : contents){
      buffer.write(line);
      buffer.newLine();
    }
    buffer.close();
  }
  
}
