package warehouse;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoadingManager extends WorkShopManager<Loader> {

  // Initialize a counter for the request
  private int nextRequest = 1;

  private ArrayList<Truck> trucks = new ArrayList<Truck>();

  private Map<Integer, Pallet> toLoadF = new HashMap<Integer, Pallet>();

  private Map<Integer, Pallet> toLoadR = new HashMap<Integer, Pallet>();



  // Initialize a manager
  public LoadingManager() {
    this.addTruck();
  }

  // Sets which request should be processed next
  public void setNextRequest() {
    this.nextRequest++;
  }

  // Adding any new truck to the system
  public void addTruck() {
    Truck truck = new Truck();
    trucks.add(truck);
  }

  // Removes the truck that is full
  public void removeTruck() {
    // Need to remove the first truck and move onto second
    trucks.remove(0);
  }

  // export the work to the labeled worker
  public void export(String loadN) {
    this.addPerson(loadN);
    // Need to iterate through the list of freeWorkers to see if
    // The name does belong to a loader and is available.
    Person worker = getPerson(loadN);

    // If both are good assign the task to the worker
    if (worker instanceof Loader) {
      
      // Check if it has the nextRequest
      if (toLoadF.containsKey(nextRequest)) {
     // Will need to move the pallet over to the loader
        worker.setRequestId(nextRequest);
        ((Loader) worker).addFront(toLoadF.get(nextRequest));
        ((Loader) worker).addRear(toLoadR.get(nextRequest));

        ((Loader) worker).setTruck(getFirstTruck());
        ((Loader) worker).export(this.getFirstRequest());
        
        System.out.println("Loader " + loadN + " loads the request " + nextRequest);

        // Will also need to remove the request from the manager
        toLoadF.remove(nextRequest);
        toLoadR.remove(nextRequest);
        
        this.setNextRequest();
        removeFirstRequest();
      } else {
        System.out.println("The loader does not have the nextRequest and does not load");      } 
    }
  }

  // Access the first truck in the truck array for the loader to use
  public Truck getFirstTruck() {
    return trucks.get(0);
  }

  public void addPallets(Request request, Pallet fPallet, Pallet rPallet) {
    this.addRequest(request);
    toLoadF.put(request.getId(), fPallet);
    toLoadR.put(request.getId(), rPallet);
  }

  // Won't be used until we have a case of having more than 1 truck
  public int getTrucksAvailable() {
    return this.trucks.size();
  }

  // Adds a loader to the workmap
  public void addPerson(String name) {
    // TODO Auto-generated method stub
    if (!workMap.containsKey(name)) {
      Loader loader = new Loader(name);
      workMap.put(name, loader);
    }
  }
  
  public int getNextRequest(){
    return this.nextRequest;
  }

}
