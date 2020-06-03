package warehouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PickingManager extends WorkShopManager<Picker> {
	// contains all the workers currently working.
	private Map<String, Picker> workingWorker;
	// contains all the workers who are able to work.
	private Map<String, Picker> freeWorker;

	/**
	 * Creates a manager of Pickers.
	 */
	public PickingManager(){
		workingWorker = new HashMap<String, Picker>();
		freeWorker = new HashMap<String, Picker>();
	}
	
	/**
	 * Used to assign each picker to pick the next fascia in the order.
	 * @param picker the name of the picker to pick a fascia.
	 * @param num the next fascia in the order to have picker pick.
	 * @throws NullPointerException 
	 */
	public void AssignPick(String picker, int num, Warehouse warehouse) throws NullPointerException{
		if (workingWorker.containsKey(picker)){
			//workingWorker.put(picker, freeWorker.remove(picker));
			//workingWorker.get(picker).askPickOrder(getFirstRequest());
		  warehouse.pickOne(workingWorker.get(picker).pick(num));
		} else{
		  System.out.println("Picker " + picker + " not available");
		}
		
	}
	
	/**
	 * Tells the <picker> to send the fascia's to marshalling.
	 * @param name the picker who is called to marshalling.
	 * @return the list of fascia's to be sent to marshalling.
	 */
	public ArrayList<Integer> AssignExport(String name) throws NullPointerException{
		Picker picker = workingWorker.get(name);
	    ArrayList<Integer> fascias = picker.removeFascias();
		freeWorker.put(name, workingWorker.remove(name));
		this.addRequest(picker.removeRequest());
		System.out.println(name + " go to marshalling area for sequencing.");
		return fascias;
	}
	
	public void addPerson(String name){
		// First time picker is working.
		if (!workMap.containsKey(name)){
			workMap.put(name, new Picker(name));
			//freeWorker.put(name, workMap.get(name));
			workingWorker.put(name, workMap.get(name));
			
		}
		else if (freeWorker.containsKey(name)){ // when worker is finished and is ready for a new request.
			workingWorker.put(name, freeWorker.remove(name));
		}
		workingWorker.get(name).setRequest(this.getFirstRequest());
		this.removeFirstRequest();
		workingWorker.get(name).askPickOrder();
	}
}
