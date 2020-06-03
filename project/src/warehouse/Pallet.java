package warehouse;

import java.util.ArrayList;
import java.util.Comparator;

public class Pallet implements Comparator<Pallet>, Comparable<Pallet> {

	protected ArrayList<Integer> fascia;
	private int requestId;
	boolean isFront;

	/**
	 * create a new pallet
	 * 
	 * @param fascia
	 * @param isFront
	 */
	public Pallet(ArrayList<Integer> fascia, boolean isFront, int requestId) {
		this.isFront = isFront;
		this.requestId = requestId;
		this.fascia = new ArrayList<Integer>();

	}

	/**
	 * another construct of Pallet
	 */
	public Pallet() {
		this.isFront = false;
		this.requestId = 0;
		this.fascia = new ArrayList<Integer>();

	}

	/**
	 * add fasica to pallet
	 * 
	 * @param Fascia
	 */
	public void addFascia(int Fascia) {
		fascia.add(Fascia);
	}

	/**
	 * to define if this pallet is all of the front fascias
	 * 
	 * @param isFront
	 */
	public void setIsFront(boolean isFront) {
		isFront = true;
	}

	/**
	 * to see whether the pallet is front fascias
	 * 
	 * @return
	 */
	public boolean getIsFront() {
		return this.isFront == true;
	}

	/**
	 * return the fascias
	 * 
	 * @return the ArrayList<Integer>
	 */
	public ArrayList<Integer> getFascia() {
		return this.fascia;
	}

	/**
	 * return the request id
	 * 
	 * @return integer requestId
	 */
	public int getRequestId() {
		return requestId;
	}

	@Override
	/**
	 * compare two Pallet id and return the int
	 */
	public int compare(Pallet pallet, Pallet pallet1) {
		return pallet.getRequestId() - pallet1.getRequestId();
	}

	/**
	 * compare the tow pallet
	 * 
	 * @param pallet
	 * @return integer
	 */
	public int compareTo(Pallet pallet) {
		return this.requestId - pallet.requestId;

	}

	/**
	 * check if two Pallet is equals to each other
	 * 
	 * @param pallet
	 * @return boolean
	 */
	public boolean equals(Pallet pallet) {
		return this.requestId == pallet.requestId;
	}

   /**
	 * return the pallet's fascia's size
	 * 
	 * @return integer
	 */
	public int size() {
		return this.fascia.size();
	}

	/**
	 * return the fascias skus in that index
	 * 
	 * @param index
	 * @return integer
	 */
	public int get(int index) {
		return this.fascia.get(index);
	}

}
