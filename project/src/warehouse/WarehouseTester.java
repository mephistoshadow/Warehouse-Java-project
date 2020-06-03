package warehouse;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class WarehouseTester {

  Warehouse warehouse;
  List<String> traversal;
  List<String> initial;
  
  @Before
  public void setUp() {
    warehouse = new Warehouse(Integer.TYPE);
    traversal = new ArrayList<String>();
    initial = new ArrayList<String>();
    traversal.add("A,0,0,0,1");
    traversal.add("A,0,0,1,2");
    traversal.add("B,0,2,2,5");
    initial.add("A,0,0,1,18");
    initial.add("A,0,0,1,6");
  }
  
  @Test
  public void testGenrateTraversal() {
    this.warehouse.genrateTraversal(this.traversal);
    try{
      Field field1 = Warehouse.class.getDeclaredField("inventory");
      Field field2 = Warehouse.class.getDeclaredField("reverseInventory");
      field1.setAccessible(true);
      field2.setAccessible(true);
      @SuppressWarnings("unchecked")
      Map<Integer, String> inventory = (Map<Integer, String>) field1.get(this.warehouse);
      @SuppressWarnings("unchecked")
      Map<String, Integer> reverseInventory = (Map<String, Integer>) field2.get(this.warehouse);
      assertEquals(inventory.get(5), "B,0,2,2,30");
      assertEquals(inventory.get(2), "A,0,0,1,30");
      assertEquals(reverseInventory.get("A,0,0,0"), new Integer(1));
      assertEquals(reverseInventory.get("B,0,2,2"), new Integer(5));
    } catch(NoSuchFieldException ex){
      ex.printStackTrace();
    } catch (IllegalArgumentException ex) {
      // TODO Auto-generated catch block
      ex.printStackTrace();
    } catch (IllegalAccessException ex) {
      // TODO Auto-generated catch block
      ex.printStackTrace();
    }
    
  }

  @Test
  public void testGenerateInventory() {
    this.warehouse.genrateTraversal(traversal);
    this.warehouse.generateInventory(initial);
    try{
      Field field1 = Warehouse.class.getDeclaredField("inventory");
      field1.setAccessible(true);
      @SuppressWarnings("unchecked")
      Map<Integer, String> inventory = (Map<Integer, String>) field1.get(this.warehouse);
      assertEquals(inventory.get(5),"B,0,2,2,30");
      assertEquals(inventory.get(2),"A,0,0,1,6");
      assertEquals(inventory.get(1),"A,0,0,0,30");
    } catch(NoSuchFieldException ex){
      ex.printStackTrace();
    } catch (IllegalArgumentException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Test
  public void testModifyAmount() {
    this.initial.add("B,0,2,2,3");
    this.warehouse.genrateTraversal(traversal);
    this.warehouse.generateInventory(initial);
    this.warehouse.modifyAmount("B,0,2,2");
    try{
      Field field1 = Warehouse.class.getDeclaredField("inventory");
      Field field2 = Warehouse.class.getDeclaredField("replenishRequest");
      field1.setAccessible(true);
      field2.setAccessible(true);
      @SuppressWarnings("unchecked")
      Map<Integer, String> inventory = (Map<Integer, String>) field1.get(this.warehouse);
      @SuppressWarnings("unchecked")
      Set<String> replenish = (Set<String>) field2.get(this.warehouse);
      assertEquals(inventory.get(5), "B,0,2,2,30");
      assertEquals(replenish.size(), 0);
    } catch(NoSuchFieldException ex){
      ex.printStackTrace();
    } catch (IllegalArgumentException ex) {
      // TODO Auto-generated catch block
      ex.printStackTrace();
    } catch (IllegalAccessException ex) {
      // TODO Auto-generated catch block
      ex.printStackTrace();
    }
  }

  @Test
  public void testPickOneInt() {
    this.warehouse.genrateTraversal(traversal);
    this.warehouse.generateInventory(initial);
    this.warehouse.pickOne(2);
    try{
      Field field1 = Warehouse.class.getDeclaredField("inventory");
      Field field2 = Warehouse.class.getDeclaredField("replenishRequest");
      field1.setAccessible(true);
      field2.setAccessible(true);
      @SuppressWarnings("unchecked")
      Map<Integer, String> inventory = (Map<Integer, String>) field1.get(this.warehouse);
      @SuppressWarnings("unchecked")
      Set<String> replenish = (Set<String>) field2.get(this.warehouse);
      assertEquals(inventory.get(2), "A,0,0,1,5");
      assertEquals(replenish.size(), 1);
    } catch(NoSuchFieldException ex){
      ex.printStackTrace();
    } catch (IllegalArgumentException ex) {
      // TODO Auto-generated catch block
      ex.printStackTrace();
    } catch (IllegalAccessException ex) {
      // TODO Auto-generated catch block
      ex.printStackTrace();
    }
  }

  @Test
  public void testPickOneString() {
    this.warehouse.genrateTraversal(traversal);
    this.warehouse.generateInventory(initial);
    this.warehouse.pickOne("A,0,0,1");
    try{
      Field field1 = Warehouse.class.getDeclaredField("inventory");
      Field field2 = Warehouse.class.getDeclaredField("replenishRequest");
      field1.setAccessible(true);
      field2.setAccessible(true);
      @SuppressWarnings("unchecked")
      Map<Integer, String> inventory = (Map<Integer, String>) field1.get(this.warehouse);
      @SuppressWarnings("unchecked")
      Set<String> replenish = (Set<String>) field2.get(this.warehouse);
      assertEquals(inventory.get(2), "A,0,0,1,5");
      assertEquals(replenish.size(), 1);
    } catch(NoSuchFieldException ex){
      ex.printStackTrace();
    } catch (IllegalArgumentException ex) {
      // TODO Auto-generated catch block
      ex.printStackTrace();
    } catch (IllegalAccessException ex) {
      // TODO Auto-generated catch block
      ex.printStackTrace();
    }
  }

  @Test
  public void testRequestReplenish() {
    this.warehouse.genrateTraversal(traversal);
    this.warehouse.generateInventory(initial);
    this.warehouse.requestReplenish("A,0,0,1");
    try{
      Field field = Warehouse.class.getDeclaredField("replenishRequest");
      field.setAccessible(true);
      @SuppressWarnings("unchecked")
      Set<String> replenish = (Set<String>) field.get(this.warehouse);
      assertEquals(replenish.size(), 1);
      assertTrue(replenish.contains("A,0,0,1"));
    } catch(NoSuchFieldException ex){
      ex.printStackTrace();
    } catch (IllegalArgumentException ex) {
      // TODO Auto-generated catch block
      ex.printStackTrace();
    } catch (IllegalAccessException ex) {
      // TODO Auto-generated catch block
      ex.printStackTrace();
    }
  }

  @Test
  public void testGetTraversal() {
    this.warehouse.genrateTraversal(traversal);
    Map<Integer, String> traversalTable = this.warehouse.getTraversal();
    assertEquals(traversalTable.get(2), "A,0,0,1,2");
  }

}
