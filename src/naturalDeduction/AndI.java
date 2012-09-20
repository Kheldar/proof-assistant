package naturalDeduction;

import java.util.ArrayList;

import syntax.And;
import syntax.Formula;

public class AndI extends Deduction {
	
	public AndI(Deduction from1, Deduction from2) {
		super(logicalConsequence(from1, from2), fromsToList(from1, from2));
	}
	
	public static Formula logicalConsequence(Deduction from1, Deduction from2) {
		return new And(from1.formula, from2.formula);
	}
	
	private static ArrayList<Deduction> fromsToList(Deduction from1, Deduction from2) {
		ArrayList<Deduction> list = new ArrayList<Deduction>();
		list.add(from1);
		list.add(from2);
		return list;
	}
	
	public final Deduction fromLeft() {
		return froms.get(0);
	}
	
	public final Deduction fromRight() {
		return froms.get(1);
	}
}