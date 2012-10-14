package naturalDeduction;

import java.util.ArrayList;

import syntax.And;
import syntax.Formula;

public class AndI extends BackwardRule {
	
	public AndI(Formula from1, Formula from2) {
		super(logicalConsequence(from1, from2), fromsToList(from1, from2));
	}
	
	public static Formula logicalConsequence(Formula from1, Formula from2) {
		return new And(from1, from2);
	}
	
	private static ArrayList<Formula> fromsToList(Formula from1,Formula from2) {
		ArrayList<Formula> list = new ArrayList<Formula>();
		list.add(from1);
		list.add(from2);
		return list;
	}
	
	public final Formula fromLeft() {
		return froms.get(0);
	}
	
	public final Formula fromRight() {
		return froms.get(1);
	}
}