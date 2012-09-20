package naturalDeduction;

import java.util.ArrayList;

import syntax.Formula;
import syntax.Or;

public class OrI2 extends Deduction {
	public OrI2(Formula intro, Deduction from) {
		super(logicalConsequence(intro, from), fromToList(from));
	}
	
	public static final Formula logicalConsequence(Formula intro, Deduction from) {
		return new Or(from.formula, intro);
	}
	
	private static ArrayList<Deduction> fromToList(Deduction from) {
		ArrayList<Deduction> list = new ArrayList<Deduction>();
		list.add(from);
		return list;
	}
}
