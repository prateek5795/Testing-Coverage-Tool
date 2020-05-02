package Testing;

import java.util.*;


public class CollectionCoverage {
	public static HashMap<Integer, String> map;
    public static TreeMap<Integer, HashMap<String, List<Integer>>> suite;
    public static HashMap<String, List<Integer>> cases;
    
    public static void visitLine(String class_name, int line_num){
        if (cases == null)
            return;
        if (class_name == null) 
            return;
        List<Integer> list = cases.get(class_name);
        if(list == null) {
            list = new ArrayList<Integer>();
        }
        if(!list.contains(line_num))
        	list.add(line_num);
        cases.put(class_name, list);
    }
}
