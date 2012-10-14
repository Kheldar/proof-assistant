package naturalDeduction;

import java.util.ArrayList;

import syntax.Formula;
 
public class Direct extends BackwardRule {

	public Direct(Formula from) {
		super(from, fromToList(from));
	}
	
	private static ArrayList<Formula> fromToList(Formula from) {
		ArrayList<Formula> list = new ArrayList<Formula>();
		list.add(from);
		return list;
	}
	
	public final Formula from() {
		return froms.get(0);
	}
	
	public static Boolean hasCheck() {
		return false;
	}
}
