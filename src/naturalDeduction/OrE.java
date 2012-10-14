package naturalDeduction;

import java.util.ArrayList;

import syntax.Formula;

public class OrE extends Deduction {
	public OrE(Formula pOrQ, Formula pToR, Formula qToR) {
		super(logicalConsequence(pOrQ, pToR, qToR), fromsToList(pOrQ, pToR, qToR));
	}
	
	private static final Formula logicalConsequence(Formula pOrQ, Formula pToR, Formula qToR) {
		//TODO: Check if 'pToR' and 'qToR' are actually valid, and do in-fact assume 'p' / 'q'.
		return pToR;
	}
	
	private static ArrayList<Formula> fromsToList(Formula pOrQ, Formula pToR, Formula qToR) {
		ArrayList<Formula> list = new ArrayList<Formula>();
		list.add(pOrQ);
		list.add(pToR);
		list.add(qToR);
		return list;
	}
}