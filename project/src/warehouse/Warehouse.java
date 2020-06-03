package warehouse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class Warehouse {
  @SuppressWarnings("unused")
  private final Class<?> OBJECTTYPE;
  private Map<Integer, String> inventory;
  private Map<String, Integer> reverseInventory;
  private Set<String> replenishRequest;
  private Set<String> zones = new TreeSet<String>();
  private int aisle = 1;
  private int rack = 2;
  private int level = 3;
  private int size = 30;
  
  /**
   * Creates a new warehouse.
   * @param type The class which a warehouse stores.
   */
  public Warehouse(Class<?> type) {
    OBJECTTYPE = type;
    this.inventory = new HashMap<Integer, String>();
    this.reverseInventory = new HashMap<String, Integer>();
    this.replenishRequest = new TreeSet<String>();
    this.zones.add("A");
    this.zones.add("B");
  }
  
  /**
   * Generates the traversal table.
   * @param table The traversal table.
   */
  protected void genrateTraversal(List<String> table) {
    for(String tableLine : table){
      String[] line = tableLine.split(",");
      String location = line[0];
      for(int i = 1; i < 4; i++){
        location += "," + line[i];
      }
      String amount = new String("30");
      int sku = Integer.valueOf(line[4]);
      this.inventory.put(sku, location + "," + amount);
      this.reverseInventory.put(location, sku);
    }
  }
  
  /**
   * Generates the inventory from the initial file.
   * @param initial The initial inventory.
   */
  protected void generateInventory(List<String> initial) {
    for(String initialLine : initial){
      String[] line = initialLine.split(",");
      boolean wrongInput = false;
      try{
        wrongInput = true;
        @SuppressWarnings("unused")
        String str = line[5]; 
      } catch(ArrayIndexOutOfBoundsException ex){
        wrongInput = false;
      }
      try{
        @SuppressWarnings("unused")
        String str = line[4];
      } catch(ArrayIndexOutOfBoundsException ex){
        wrongInput = true;
      }
      if(!wrongInput){
        wrongInput = true;
        for(String zone : this.zones){
          if(zone.equals(line[0])){
            wrongInput = false;
            break;
          }
        }
      }
      int aisle = Integer.parseInt(line[1]);
      int rack = Integer.parseInt(line[2]);
      int level = Integer.parseInt(line[3]);
      int amount = Integer.parseInt(line[4]);
      if(!wrongInput) {
        if(aisle < 0 || rack < 0 || level < 0){
          wrongInput = true;
        } else if(aisle > this.aisle || rack > this.rack || level > this.level) {
          wrongInput = true;
        } else if(amount < 0 || amount > this.size){
          wrongInput = true;
        }
      }
      if(wrongInput){
       System.out.println("Wrong Input " + initialLine);
       continue;
      }
      try{
        String location = line[0];
        for(int i = 1; i < 4; i++){
          location += "," + line[i];
        }
        String stramount = line[4];
        int sku = this.reverseInventory.get(location);
        this.inventory.remove(sku);
        this.inventory.put(sku, location + "," + stramount);
        if(amount <= 5){
          this.requestReplenish(location);
        }
      } catch(ArrayIndexOutOfBoundsException ex){
        System.out.println("Wrong Input" + " Array out of bounds " + initialLine);
      }
    }
  }
  
  /**
   * Replenish a certain kind of fascia. 
   * @param location The location to replenish to.
   */
  public void modifyAmount(String location) {
    if(this.replenishRequest.contains(location)){
      this.replenishRequest.remove(location);
    }
    int sku = this.reverseInventory.get(location);
    String amount = String.valueOf(this.size);
    amount = location + "," + amount;
    this.inventory.remove(sku);
    this.inventory.put(sku, amount);
  }
  
  /**
   * Pick one fascia from this warehouse by sku.
   * @param sku The sku of fascia.
   */
  public void pickOne(int sku){
    String loc = this.inventory.get(sku);
    String[] splloc = loc.split(",");
    String location = splloc[0];
    for(int i = 1; i < 4; i++){
      location += "," + splloc[i];
    }
    int amount = Integer.parseInt(splloc[4]);
    amount--;
    this.inventory.remove(sku);
    this.inventory.put(sku, location + "," + Integer.toString(amount));
    if(amount <= 5){
      this.replenishRequest.add(location);
    }
    System.out.println("Pick one: " + Integer.toString(sku) + " " + inventory.get(sku));
  }
  
  /**
   * Pick one fascia from this warehouse by location.
   * @param location The location where a picker picks a fascia.
   */
  public void pickOne(String location) {
    int sku = this.reverseInventory.get(location);
    String[] splloc = this.inventory.get(sku).split(",");
    int amount = Integer.parseInt(splloc[4]);
    amount--;
    this.inventory.remove(sku);
    this.inventory.put(sku, location + "," + String.valueOf(amount));
    if(amount <= 5){
      this.replenishRequest.add(location);
    }
    System.out.println("Pick one: " + Integer.toString(sku) + " " + inventory.get(sku));
  }
  
  /**
   * Add a replenish request to a certain location.
   * @param location The location to replenish to.
   */
  public void requestReplenish(String location) {
    if(!this.replenishRequest.contains(location)){
      this.replenishRequest.add(location);
    }
  }
  
  public List<String> getStock(){
    Collection<String> values = this.inventory.values();
    List<String> finalStock = new ArrayList<String>(); 
    for(String line : values){
      String[] str = line.split(",");
      int amount = Integer.parseInt(str[4]);
      if(amount < this.size){
        finalStock.add(line);
      }
    }
    return finalStock;
  }
  
  public Map<Integer, String> getTraversal() {
    Map<Integer, String> traversal = new HashMap<Integer, String>();
    Set<String> locations = this.reverseInventory.keySet();
    for(String location : locations){
      int sku = this.reverseInventory.get(location);
      String strsku = String.valueOf(sku);
      traversal.put(sku, location + "," + strsku);
    }
    return traversal;
  }
}
