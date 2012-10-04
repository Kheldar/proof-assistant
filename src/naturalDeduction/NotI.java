package naturalDeduction;

import java.util.ArrayList;

import syntax.Formula;
import syntax.Not;

public class NotI extends Deduction {
	
	public NotI(Assumption p, Deduction pToNeg) {
		super(logicalConsequence(p, pToNeg), fromToList(pToNeg));
	}
	
	public static final Formula logicalConsequence(Assumption p, Deduction pToNeg) {
		//TODO: Check pToNeg is correct.
		return new Not(p.formula);
	}
	
	private static ArrayList<Deduction> fromToList(Deduction pToNeg) {
		ArrayList<Deduction> list = new ArrayList<Deduction>();
		list.add(pToNeg);
		return list;
	}
}
