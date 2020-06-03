package warehouse;

import java.util.ArrayList;
import java.util.List;

public class Sequencer extends Person {
	Pallet fPallet;
	Pallet rPallet;

	/**
	 * create a Sequencer
	 * 
	 * @param name
	 * @param fPallet
	 * @param rPallet
	 */
	public Sequencer(String name) {
		super(name);
		this.fPallet = new Pallet();
		this.rPallet = new Pallet();
	}

	/**
	 * discard the fascias
	 */
	public void discard() {
		fPallet.fascia.clear();
		rPallet.fascia.clear();

	}

	/**
	 * this method is to let sequencer to sequence the pallet then return a
	 * signal to define if it should be discard
	 * 
	 * @param request
	 * @param fascias
	 * @return a boolean to define if it should be discard or not
	 */
	public boolean Sequence(Request request, List<Integer> fascias) {
		List<Integer> skus = request.getRequest();
		this.fPallet = new Pallet(new ArrayList<Integer>(), true, request.getId());
		this.rPallet = new Pallet(new ArrayList<Integer>(), false, request.getId());
		for (int i = 0; i < skus.size(); i++) {
			if (i % 2 == 0) {
				fPallet.addFascia(skus.get(i));
				//fPallet.setIsFront(fPallet.isFront);

			} else {
				rPallet.addFascia(skus.get(i));
			}

		}

		for (int j = 0; j < skus.size(); j++) {
			if (skus.get(j) != fascias.get(j)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * let sequencer to export the pallet to loader override the export method
	 */

	public List<Pallet> export() {
		List<Pallet> exportPallet = new ArrayList<Pallet>();
		exportPallet.add(fPallet);
		exportPallet.add(rPallet);
		this.fPallet = new Pallet();
		this.rPallet = new Pallet();
		this.setRequestId(0);
		return exportPallet;
	}

}
