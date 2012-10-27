package tools;

import java.util.ArrayList;

public class ListOperations {
	
	public static ArrayList<?> removeDuplicates(ArrayList<?> list) {
		ArrayList<Object> temp = new ArrayList<Object>();
		for(Object i : list) {
			if(!temp.contains(i)) {
				temp.add(i);
			}
		}
		return temp;
	}
}
