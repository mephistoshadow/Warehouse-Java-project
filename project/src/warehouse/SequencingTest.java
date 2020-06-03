package warehouse;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class SequencingTest {

	@Test
	public void testRemoveFirstRequest() {
		SequencingManager a = new SequencingManager();
		ArrayList<Integer> c = new ArrayList<Integer>();
		c.add(3);
		c.add(4);
		Request e = new Request(1, c);
		ArrayList<Integer> fascia = new ArrayList<Integer>();
		fascia.add(3);
		fascia.add(4);
		a.addpackage(1, fascia);
		a.request.add(e);
		a.removeFirstRequest();
 		System.out.println(a.packages.get(1));
		System.out.println(a.packages);
		assertTrue(a.request.size()==0);
		
	}

	@Test
	public void testAddPerson() {
		SequencingManager a = new SequencingManager();
		Sequencer b = new Sequencer ("name");
		a.workMap.put("name",b);
		assertEquals(b,a.getPerson("name"));
	}

	
	@Test
	public void testAssignSequence() {
		SequencingManager a = new SequencingManager();
		ArrayList<Integer> c = new ArrayList<Integer>();
		c.add(3);
		c.add(4);
		Request e = new Request(1, c);
		a.addRequest(e);
		ArrayList<Integer> fascia = new ArrayList<Integer>();
		fascia.add(3);
		fascia.add(2);
		a.addpackage(1, fascia);
		Sequencer b = new Sequencer("name");
		a.workMap.put("name", b);
		
		assertFalse(a.AssignSequence("name"));
	}

	@Test
	public void testAddpackage() {
		SequencingManager a = new SequencingManager();
	    ArrayList<Integer> b = new ArrayList<Integer>();
	    b.add(3);
	    b.add(4);
	    a.packages.put(1, b);
	    ArrayList<Integer> e=a.packages.get(1);
	    assertEquals((int)e.get(0),3);
	}

	@Test
	public void testAssignDisCard() {
		SequencingManager a = new SequencingManager();
		Sequencer b = new Sequencer ("name");
		ArrayList<Integer> c = new ArrayList<Integer>();
		c.add(3);
		c.add(4);
		Request e = new Request(1, c);
		a.addRequest(e);
		a.workMap.put("name", b);
		b.fPallet.addFascia(3);
		b.rPallet.addFascia(4);
		Request g =a.AssignDisCard("name");
		assertTrue(b.fPallet.size()==0);
		assertTrue(b.rPallet.size()==0);
		assertEquals(g.getRequest(),e.getRequest());
	}
//
//	@Test
//	public void testAssignExport() {
//		fail("Not yet implemented");
//	}

}
