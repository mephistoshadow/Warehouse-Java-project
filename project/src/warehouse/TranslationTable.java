package warehouse;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class TranslationTable {
  private Map<String, Integer> frontFascia;
  private Map<String, Integer> rearFascia;
  
  /**
   * Creates an empty translation table.
   */
  public TranslationTable() {
    this.frontFascia = new HashMap<String, Integer>();
    this.rearFascia = new HashMap<String, Integer>();
  }
  
  /**
   * Creates a translation table with mapping.
   * @param table The translation table.
   */
  public TranslationTable(List<String> table) {
    this.frontFascia = new HashMap<String, Integer>();
    this.rearFascia = new HashMap<String, Integer>();
    this.generateTable(table);
  }
  
  /**
   * Generates the translation table.
   * @param table The translation table.
   */
  public void generateTable(List<String> table) {
    for(String tableLine : table) {
      String[] line = tableLine.split(",");
      String car = line[0] + "," + line[1];
      try{
        Integer front = Integer.parseInt(line[2]);
        Integer rear = Integer.parseInt(line[3]);
        this.frontFascia.put(car, front);
        this.rearFascia.put(car, rear);
      } catch(NumberFormatException ex){
        System.out.println("Wrong Input" + tableLine);
      }
    }
  }
  
  /**
   * Translates the color and model of a car to the sku of fascia.
   * @param car The color and model of a minivan, in the form of "Colour,Model".
   * @param isFront Indicates if it is asking for the front fascia.
   * @return The sku of the corresponding fascia. 
   */
  public int translate(String car, boolean isFront) {
    if(isFront){
      return this.frontFascia.get(car);
    }
    else{
      return this.rearFascia.get(car);
    }
  }
}
