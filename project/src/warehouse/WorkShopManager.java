package warehouse;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;

public abstract class WorkShopManager<P extends Person> {
	protected Set<Request> request;
	protected ArrayList<P> workingWorker;
	protected ArrayList<P> freeWorker;
	protected Map<String, P> workMap;

/**
 * create a WorkShopManager
	 * 
	 *  Set<Request>
	 *            request
	 *  ArrayList
	 *            <P>
	 *            WorkingWorker
	 *  Map<String,P>
	 *            workMap
	 */
	public WorkShopManager() {
		this.request = new TreeSet<Request>();
		this.workingWorker = new ArrayList<P>();
		this.freeWorker = new ArrayList<P>();
		this.workMap = new HashMap<String, P>();
	}

	/**
	 * add a request to the set
	 * 
	 * @param requestFirst
	 */
	public void addRequest(Request requestFirst) {
		request.add(requestFirst);
	}

	/**
	 * return the first request
	 * 
	 * @return Request
	 * @throws NullPointerException
	 */
	public Request getFirstRequest() throws NullPointerException {
		return request.iterator().next();
	}

	/**
	 * remove the first request
	 */
	public void removeFirstRequest() {
		this.request.remove(this.getFirstRequest());
	}

	/**
	 * add a person to the WorkMap
	 * 
	 * @param name
	 */
	public abstract void addPerson(String name);

	/**
	 * return the person whose name is the parameter name
	 * 
	 * @param name
	 * @return Person
	 */
	public Person getPerson(String name) {
		return workMap.get(name);


	}

		

}
