package naturalDeduction;

import java.util.ArrayList;
 
public class Direct extends Deduction {

	public Direct(Deduction from) {
		super(from.formula, fromToList(from));
	}
	
	private static ArrayList<Deduction> fromToList(Deduction from) {
		ArrayList<Deduction> list = new ArrayList<Deduction>();
		list.add(from);
		return list;
	}
	
	public final Deduction from() {
		return froms.get(0);
	}
	
	public static Boolean hasCheck() {
		//TODO: ???
		return false;
	}
}
