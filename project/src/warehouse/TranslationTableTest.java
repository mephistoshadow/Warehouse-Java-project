package warehouse;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TranslationTableTest {
  
  TranslationTable translationTable;
  List<String> table;
  
  @Before
  public void setUp() {
    this.translationTable = new TranslationTable();
    this.table = new ArrayList<String>();
    table.add("White,SS,12,5");
    table.add("Red,SES,4,11");
    table.add("Red,SS,7,17");
    this.translationTable.generateTable(table);
  }
  
  @Test
  public void testGenerateTable() {
    try{
      Field field1 = TranslationTable.class.getDeclaredField("frontFascia");
      Field field2 = TranslationTable.class.getDeclaredField("rearFascia");
      field1.setAccessible(true);
      field2.setAccessible(true);
      @SuppressWarnings("unchecked")
      Map<String, Integer> front = (Map<String, Integer>) field1.get(this.translationTable);
      @SuppressWarnings("unchecked")
      Map<String, Integer> rear = (Map<String, Integer>) field2.get(this.translationTable);
      assertEquals(front.get("White,SS"), new Integer(12));
      assertEquals(front.get("Red,SES"), new Integer(4));
      assertEquals(rear.get("Red,SS"), new Integer(17));
      assertEquals(rear.get("Red,SES"), new Integer(11));
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
  public void testTranslate() {
    assertEquals(this.translationTable.translate("Red,SES", true), 4);
    assertEquals(this.translationTable.translate("White,SS", false), 5);
  }

}
