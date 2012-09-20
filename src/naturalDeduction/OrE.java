package naturalDeduction;

import java.util.ArrayList;

import syntax.Formula;

public class OrE extends Deduction {
	public OrE(Deduction pOrQ, Deduction pToR, Deduction qToR) {
		super(logicalConsequence(pOrQ, pToR, qToR), fromsToList(pOrQ, pToR, qToR));
	}
	
	private static final Formula logicalConsequence(Deduction pOrQ, Deduction pToR, Deduction qToR) {
		//TODO: Check if 'pToR' and 'qToR' are actually valid, and do in-fact assume 'p' / 'q'.
		return pToR.formula;
	}
	
	private static ArrayList<Deduction> fromsToList(Deduction pOrQ, Deduction pToR, Deduction qToR) {
		ArrayList<Deduction> list = new ArrayList<Deduction>();
		list.add(pOrQ);
		list.add(pToR);
		list.add(qToR);
		return list;
	}
}