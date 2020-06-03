package warehouse;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

public class warehouseTest {

	@Test
	public void testSequencer() {
		fail("Not yet implemented");
	}

	@Test
	public void testDiscard() {
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(2);
		a.add(32);
		Pallet fPallet = new Pallet(a,true, 0);
		assertTrue(fPallet.size()==0);
	}

	@Test
	public void testSequence() {
		Sequencer b = new Sequencer("c");
		List<Integer> skus = new ArrayList<Integer>();
		skus.add(3);
		skus.add(4);
		skus.add(3);
		skus.add(3);
		skus.add(3);
		skus.add(3);
		skus.add(3);
		skus.add(3);
		Request request = new Request(1, skus);
		List<Integer> fascia = new ArrayList<Integer>();
		fascia.add(4);
		fascia.add(3);
		fascia.add(3);
		fascia.add(3);
		fascia.add(3);
		fascia.add(3);
		fascia.add(3);
		fascia.add(3);
		fascia.add(3);
		
		assertEquals(b.Sequence(request,fascia),true);
	}

	@Test
	public void testExport() {
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(2);
		a.add(32);
		Pallet fPallet = new Pallet();
		ArrayList<Integer> b = new ArrayList<Integer>();
		b.add(2);
		b.add(32);
		Pallet rPallet = new Pallet();
		List<Pallet> exportPallet = new ArrayList<Pallet>(); 
		exportPallet.add(fPallet);
		exportPallet.add(rPallet);
		int size = exportPallet.size();
		assertEquals(size,2);
		
	}

}
