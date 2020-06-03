package warehouse;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class LoaderTester {

  @Test
  public void testLoaderConstruct() {
    Loader loader = new Loader("Steve");
    assertTrue(loader instanceof Loader);
  }

  @Test
  public void testLoaderExtends() {
    Loader loader = new Loader("Steve");
    assertTrue(loader instanceof Person);
  }

  @Test
  public void testAddFront() {
    ArrayList<Integer> fascias = new ArrayList<Integer>();
    fascias.add(3);
    fascias.add(5);
    Pallet pallet = new Pallet(fascias, true, 1);
    Loader loader = new Loader("Steve");
    loader.addFront(pallet);
    assertEquals(loader.loadFront.size(), 1);
  }

  @Test
  public void testAddRear() {
    ArrayList<Integer> fascias = new ArrayList<Integer>();
    fascias.add(3);
    fascias.add(5);
    Pallet pallet = new Pallet(fascias, true, 1);
    Loader loader = new Loader("Steve");
    loader.addRear(pallet);
    assertEquals(loader.loadRear.size(), 1);
  }

  @Test
  public void testCheckOrder() {
    // Check order to request
    fail("Not yet implemented");
  }

  @Test
  public void testSetTruck() {
    Truck t = new Truck();
    Loader l = new Loader("Load");
    l.setTruck(t);
    assertEquals(l.truck, t);
  }
  
  @Test 
  public void testTruckSize() {
    Truck t = new Truck();
    Truck t2 = new Truck();
    ArrayList<Integer> fascias = new ArrayList<Integer>();
    fascias.add(3);
    fascias.add(5);
    Pallet pallet = new Pallet(fascias, true, 1);
    t.add(pallet);
    assertFalse(t.equals(t2));
  }

  @Test
  public void testLoaderExport() {
    fail("Not yet implemented");
  }
}
