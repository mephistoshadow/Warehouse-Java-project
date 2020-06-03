package warehouse;

import static org.junit.Assert.*;

import org.junit.Test;

public class PersonTest {
	@Test
	public void testequals(){
		Person p1 = new Person("Jim");
		Person p2 = new Person("Jack");
		assertNotEquals("These should be different people.", p1, p2);
		p2 = new Person("Jim");
		// Symmetry
		assertEquals("Should be the same person.", p1, p2);
		assertEquals("Should be the same person.", p2, p1);
		// Reflexivity
		assertEquals("Should prove reflexitivity", p1, p1);
		// transitivity
		Person p3 = new Person("Jim");
		assertEquals("Should be the same person.", p1, p3);
		assertEquals("Should be the same person.", p2, p3);
		assertEquals("Should be the same person.", p1, p2);
	}
	
	@Test
	public void testgetName(){
		Person p1 = new Person("Edward");
		assertEquals("Should be the same person.", p1.getName(), "Edward");
		assertNotEquals("Shouldn't be the value of getName method.", p1.getName(), "Jack");
	}
	
	@Test 
	public void testgetRequestId(){
		Person p2 = new Person("kyte");
		p2.setRequestId(3);
		assertEquals(p2.getRequestId(), 3);
		assertNotEquals(p2.getRequestId(), 2);
	}
}
