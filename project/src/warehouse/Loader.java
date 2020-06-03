package warehouse;

import java.util.ArrayList;

public class Loader extends Person {

  public Loader(String name) {
    super(name);
  }

  protected Pallet loadFront = new Pallet();

  protected Pallet loadRear = new Pallet();

  protected Truck truck;

  public void export(Request request) {
    // Need to do a final check before sending in all the pallets
    if (checkOrder(request, loadFront, loadRear)) {
      truck.add(loadFront);
      truck.add(loadRear);
    }
    setRequestId(0);
  }

  // Check if the load is in the correct order
  public boolean checkOrder(Request request, Pallet fPallet, Pallet rPallet) {
    int requestId = request.getId();
    System.out.println("Front Pallet SKU:" + fPallet.getRequestId());
    System.out.println("Rear Pallet SKU:" + rPallet.getRequestId());
    System.out.println("RequestID: " + requestId);
    // Both the request need to be correct
    if (fPallet.getRequestId() == requestId && rPallet.getRequestId() == requestId) {
    	// Might want to add in a checker for all the inside fascia
      return true;
    } else {
      return false;
    }
  }

  // When the loading manager wants the loader to load a pallet
  public void addFront(Pallet p) {
    loadFront = p;
  }

  // Adding the rear pieces
  public void addRear(Pallet p) {
    loadRear = p;
  }

  // Need to set which truck the loader is loading to
  public void setTruck(Truck t) {
    this.truck = t;
  }
  
  public Truck getTruck() {
    return this.truck;
  }

}
