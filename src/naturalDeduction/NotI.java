package naturalDeduction;

import java.util.ArrayList;

import syntax.Formula;
import syntax.Not;

public class NotI extends Deduction {
	
	public NotI(Formula p, Formula pToNeg) {
		super(logicalConsequence(p, pToNeg), fromToList(pToNeg));
	}
	
	public static final Formula logicalConsequence(Formula p, Formula pToNeg) {
		//TODO: Check pToNeg is correct.
		return new Not(p);
	}
	
	private static ArrayList<Formula> fromToList(Formula pToNeg) {
		ArrayList<Formula> list = new ArrayList<Formula>();
		list.add(pToNeg);
		return list;
	}
}
