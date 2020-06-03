package warehouse;

import java.util.ArrayList;
import java.util.List;

public class WareHouseManager {

  private LoadingManager loadM = new LoadingManager();
  private SequencingManager sequenceM = new SequencingManager();
  private PickingManager pickM = new PickingManager();
  private List<Request> requests = new ArrayList<Request>();
  private List<String> orders = new ArrayList<String>();
  private Warehouse warehouse;
  private TranslationTable translation;
  private int numOfRequest;

  public WareHouseManager(Warehouse warehouse, TranslationTable translation) {
    this.warehouse = warehouse;
    this.translation = translation;
  }

  // Take in a command and perform what is required
  public void command(String line) {
    // Will need to break it down first
    String[] brokenLine = line.split(" ");
    // brokenLine[0] = order, [1] = name, [2] = task, [3] = marshal/# pick
    if (brokenLine[0].equals("Order")) {
      String order = line.substring(6);
      addOrder(order);

    } else if (brokenLine[0].equals("Sequencer")) {
      if (brokenLine[2].equals("sequences"))
        sequence(brokenLine[1]);
//      else if (brokenLine[2].equals("discard")) {
//        discard(brokenLine[1]);
//      }

    } else if (brokenLine[0].equals("Picker")) {
      // Need to differentiate between Ready and picking
      if (brokenLine[2].equals("ready")) {
        pickM.addPerson(brokenLine[1]);
        // Send the request for pick Manager to deal with
        //sendRequest(pickM, requests.get(0));
        //requests.remove(0);

      } else if (brokenLine[2].equals("pick")) {
        pick(brokenLine[1], Integer.parseInt(brokenLine[3]));
        
        // When sent to marshalling
      } else if (brokenLine[2].equals("to")) {
        // Should tell the worker to give the fascias to the sequence
        // manager
        ArrayList<Integer> fascias = this.pickM.AssignExport(brokenLine[1]);
        int requestId = this.pickM.getFirstRequest().getId();
        this.getExportedRequest(this.pickM, this.sequenceM);
        this.sequenceM.addpackage(requestId, fascias);
      }
    } else if (brokenLine[0].equals("Loader")) {
//      loadM.addPerson(brokenLine[1]);
      load(brokenLine[1]);
    } else if (brokenLine[0].equals("Replenisher")) {
      warehouse.modifyAmount(
          brokenLine[3] + "," + brokenLine[4] + "," + brokenLine[5] + "," + brokenLine[6]);
    } else {
      System.out.println("False command: " + line + ", did nothing");
    }

  }

  public void sendRequest(WorkShopManager<?> manager, Request request) {
    manager.addRequest(request);
  }

  public void addRequest(int first, int last) {
    // Need to convert the 4 orders into a request
    List<Integer> sku = new ArrayList<Integer>();
    for (int i = first; i <= last; i++) {
      // Will need determine if we need to change to skus here
      // Will use orders.size() / 4 to determine the starting index and
      // end
      // Ex. request.add(order[i * (orders.size()/4))

      String order = orders.get(i);
      sku.add(translation.translate(order, true));
      sku.add(translation.translate(order, false));
    }
    Request req = new Request(++this.numOfRequest,sku);
    requests.add(req);
    this.sendRequest(this.pickM, req);
    System.out.println("Add one picking request");
    //sequenceM.addRequest(new Request(sku));
    //loadM.addRequest(new Request(sku));
  }

  public void addOrder(String command) {
    // Orders come in as a model and colour
    String order;
    String[] car = command.split(" ");
    order = car[1] + "," + car[0];
    System.out.println(order);
    orders.add(order);
    if ((orders.size() % 4) == 0) {
      // Add requests with the first index and last index
      addRequest(orders.size() - 4, orders.size() - 1);
//      System.out.println("//////////////////////////////////////////////////////////////////////////////");
    }
  }

  // Possible commands for that could be inputed.
  public void pick(String name, int num) {
    pickM.AssignPick(name, num, warehouse);

  }

  public void sequence(String name) {
    if (sequenceM.AssignSequence(name)) {
      // Need to also grab a the request currently missing
      Request request = sequenceM.getFirstRequest();
      List<Pallet> pallets = new ArrayList<Pallet>(sequenceM.AssignExport(name));
     loadM.addPallets(request, pallets.get(0), pallets.get(1));
    } else {
      this.discard(name);
    }
  }

  public void load(String name) {
//    loadM.setNextRequest(); // Needed for the first request
    loadM.export(name);
  }

  public void discard(String name) {
    sequenceM.AssignDisCard(name);
  }

  public List<String> finalStock() {
    // Returning the final stock of the day
    return warehouse.getStock();
  }
  
  public void getExportedRequest(WorkShopManager<?> manager1, WorkShopManager<?> manager2) {
    Request request = manager1.getFirstRequest();
    manager1.removeFirstRequest();
    manager2.addRequest(request);
  }

  public List<String> ordersOnTruck() {
    // CSV File wants the orders in the format of colour,model
//    int numberOfLoads = loadM.getFirstTruck().checkLoadSize();
//    List<String> toCSV = new ArrayList<String>();
//    for (int i = 0; i <= numberOfLoads; i++) {
//      String[] lineSplit = orders.get(i).split(" ");
//      toCSV.add(lineSplit[1] + "," + lineSplit[0]);
//    }
//    // Need to break down the sublist into the proper format for CSV
//    return toCSV;
//  }
    int num = this.loadM.getNextRequest() - 1;
    List<String> toCSV = orders.subList(0, 4 * num - 1);
    System.out.println("han");
    System.out.println(num);
    System.out.println(4 * num - 1);
    return toCSV;
  }
}
