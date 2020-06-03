package warehouse;

import java.util.ArrayList;
import java.util.List;

public class Picker extends Person {
  private ArrayList<Integer> fascias;
  private List<String> pickOrder;
  private boolean ready;
  private boolean finish;
  private Request request;
  
  /**
   * creates a new picker to pick.
   * @param name used to identify the picker.
   */
  public Picker(String name){
    super(name);
    fascias = new ArrayList<Integer>();
    pickOrder = new ArrayList<String>();
  }
  
  public int pick(int num){
	  int sku;
    String[] locations = pickOrder.get(num - 1).split(",");
    sku = Integer.parseInt(locations[4]);
    System.out.println(sku);
    fascias.add(sku);
    if (num <= 7){
    	locations = pickOrder.get(num).split(",");
    	System.out.println(getName() + " go get fascia sku number " + locations[4] + " at Zone: " + locations[0] +
        		" aisle: " + locations[1] + " rack: " + locations[2] + " level: " + locations[3] + " Pick " + (num + 1));
    }
    return sku;
  }
  
  public void askPickOrder(){
    pickOrder = WarehousePicking.optimize(request.getRequest());
  }
  
  public void setReady(){
    ready = true;
  }
  
  public boolean getReady(){
    return ready;
  }
  
  public ArrayList<Integer> getFascias(){
    @SuppressWarnings("unchecked")
    ArrayList<Integer> fascias = (ArrayList<Integer>) this.fascias.clone();
    return fascias;
  }
  
  public ArrayList<Integer> removeFascias() {
    ArrayList<Integer> fascias = this.fascias;
    this.fascias = new ArrayList<Integer>();
    return fascias;
  }
  
  
  public ArrayList<Integer> export(){
    return fascias;
  }
  
  public Request getRequest(){
    return request;
  }
  
  public void setRequest(Request request){
    this.request = request;
  }
  
  public Request removeRequest(){
    Request temp = this.request;
    this.request = null;
    return temp;
  }
}

