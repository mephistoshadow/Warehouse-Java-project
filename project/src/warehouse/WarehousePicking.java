package warehouse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class WarehousePicking {

	public static List<String> optimize(List<Integer> skus){
		/*
		File traversal = new File("traversal_table.csv");
	    String travePath = traversal.getAbsolutePath();
	    List<String> traveTable = null;
	    try{
	    	traveTable = Main.readFromCSVFile(travePath);
	    } catch(FileNotFoundException ex){
	    	
	    }
	    
	    List<String> lst = new ArrayList<String>();
	    for (String traveLine : traveTable){
	    	for (int i = 0; i < skus.size(); i++){
	    		if (Integer.valueOf(traveLine.substring(traveLine.lastIndexOf(","), traveLine.length())).equals(skus.get(i))){
	    			lst.add(traveLine);
	    		}
	    	}
	    }
	    */
		List<String> traveTable = new ArrayList<String>();
		for (int i = 0; i < skus.size(); i++){
			traveTable.add(Main.traversalTable.get(skus.get(i)));
		}
	    return traveTable;
	}
}
