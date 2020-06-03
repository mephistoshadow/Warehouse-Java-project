package warehouse;

import java.util.ArrayList;

public class Truck {

  // Will need to add that a pallet is an item
  // private List<Pallet> load = new List();
  private ArrayList<Pallet> load = new ArrayList<Pallet>();

  // Maximum amount of products allowed in the Truck
  // In this initial case we hold 40 Pallets
  private int maxLoad = 40;

  // Initialize a truck object
  public Truck() {}

  // Adding a pallet of fascia into the truck
  public void add(Pallet item) {
    // Should check if the truck is full before adding more
    if (load.size() < maxLoad) {
      load.add(item);
      System.out.println("Truck adds " + item.getRequestId());
    } else {
      // TODO Make the truck add to the next available truck
      System.out.println("Truck is full");
    }

  }
  
  protected ArrayList<Pallet> checkLoad() {
    return this.load;
  }

  // Checks how many products are currently in the truck
  protected int checkLoadSize() {
    return load.size();
  }
  
  // Checking if two trucks are the same
  public boolean equals(Truck truck2) {
    if (this.load.size() == truck2.checkLoadSize()) {
      for (Pallet pallet: load) {
        if (truck2.checkLoad().contains(pallet)) {
        } else {
          return false;
        }
      }
      return true;
    }
      return false;
    
  }

}
