package warehouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SequencingManager extends WorkShopManager<Sequencer> {
	Map<Integer, ArrayList<Integer>> packages;

	/**
	 * create a SequencerManager
	 * 
	 * @param packages
	 */
	public SequencingManager() {
		this.packages = new HashMap<Integer, ArrayList<Integer>>();

	}

	/**
	 * Assign to do the Sequence action and return a boolean to WarehouseManager
	 * 
	 * @param name
	 * @return a boolean
	 */
	public boolean AssignSequence(String name) {
		boolean check = true;
		Request requestFirst = getFirstRequest();
		if (workMap.keySet().contains(name)) {
			int b = requestFirst.getId();
			ArrayList<Integer> fascias = packages.get(b);
			check = workMap.get(name).Sequence(requestFirst, fascias);
		} else {
			addPerson(name);
			int b = requestFirst.getId();
			ArrayList<Integer> fascias = packages.get(b);
			check = workMap.get(name).Sequence(getFirstRequest(), fascias);
		}
		if (check) {
			System.out.println("this request " + requestFirst.getId() + " is good");
		} else {
			System.out.println("this request " + requestFirst.getId() + " is bad");
		}
		return check;

	}

	/**
	 * add request Id and list of fascias to the map
	 * 
	 * @param requestId
	 * @param fascia
	 */
	public void addpackage(int requestId, ArrayList<Integer> fascia) {
		packages.put(requestId, fascia);
	}

	/**
	 * Assign the Sequencer to discard the fasica then report the request to
	 * warehouse manager then remove the request from the request set
	 * 
	 * @param name
	 * @return
	 */
	public Request AssignDisCard(String name) {
		workMap.get(name).discard();
		Request request = getFirstRequest();
		System.out.println(name + " has discarded the fascias for request " + request.getId());
		removeFirstRequest();
		return request;

	}

	/**
	 * override the add Person method and add a sequencer to the workMap
	 */
	@Override
	public void addPerson(String name) {
		Sequencer sequencer = new Sequencer(name);
		workMap.put(name, sequencer);
	}

	/**
	 * assign the Sequencer to export the pallet to warehousemanager
	 * 
	 * @param name
	 * @return List<Pallet>
	 */
	public List<Pallet> AssignExport(String name) {
		List<Pallet> export = workMap.get(name).export();
		removeFirstRequest();
		return export;
	}

	/**
	 * remove the first request from the request set
	 */
	@Override
	public void removeFirstRequest() {
		Request requestFirst = getFirstRequest();
		int id = requestFirst.getId();
		request.remove(requestFirst);
		packages.remove(id);
		
		
		
	}

}
