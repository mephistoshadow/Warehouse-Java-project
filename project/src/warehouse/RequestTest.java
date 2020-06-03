package warehouse;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class RequestTest {
	@Test
	public void testgetId(){
		Request r1 = new Request(1,new ArrayList<Integer>(Arrays.asList(2, 3, 4)));
		Request r2 = new Request(2,new ArrayList<Integer>(Arrays.asList(5, 2, 6)));
		assertEquals("Should have the same id for r2 and r2", r2.getId(), r2.getId());
		assertEquals("Should have r2 should have id 1 more than r1", r1.getId() + 1, r2.getId());
		// Each request has different id.
		assertNotEquals(r1.getId(), r2.getId());
	}
	
	@Test
	public void testcompare(){
		Request r1 = new Request(1,new ArrayList<Integer>(Arrays.asList(2, 3, 4)));
		Request r2 = new Request(2,new ArrayList<Integer>(Arrays.asList(5, 2, 6)));
		Request r3 = new Request(3,new ArrayList<Integer>(Arrays.asList(1, 2, 3)));
		// check r1 is smaller than r3.
		assertEquals("r1 is not smaller than r3", r1.compare(r1,  r3), -2);
		// check r3 is larger than r2.
		assertEquals("r3 is not larger than r1", r1.compare(r3, r2), 1);
		// check r1 is equal to r1.
		assertEquals("r1 is not equal to r1", r3.compare(r1,  r1), 0);
	}
	
	@Test 
	public void testCompareTo(){
		Request r1 = new Request(1,new ArrayList<Integer>(Arrays.asList(2, 3, 4)));
		Request r2 = new Request(2,new ArrayList<Integer>(Arrays.asList(5, 2, 6)));
		Request r3 = new Request(3,new ArrayList<Integer>(Arrays.asList(1, 2, 3)));
		// check r1 is smaller than r3.
		assertTrue("r1 is not smaller than r3", r1.compareTo(r3) < 0);
		// check r3 is larger than r2.
		assertTrue("r3 is not larger than r1", r3.compareTo(r2) > 0);
		// check r1 is equal to r1.
		assertTrue("r1 is not equal to r1", r1.compareTo(r1) == 0);
	}
	
	@Test
	public void testequals(){
		Request r1 = new Request(1,new ArrayList<Integer>(Arrays.asList(2, 3, 4)));
		Request r2 = new Request(2,new ArrayList<Integer>(Arrays.asList(5, 2, 6)));
		Request r3 = new Request(3,new ArrayList<Integer>(Arrays.asList(1, 2, 3)));
		// check r1 is the same as r1.
		assertEquals("r1 is not the same as r1", r1, r1);
		// check r2 and r3 are different.
		assertNotEquals("r2 and r3 are the same", r2, r3);
		assertNotEquals("r2 and r3 are the same", r3, r2);
	}
}
