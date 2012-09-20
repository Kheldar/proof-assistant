package naturalDeduction;
import java.util.ArrayList;

import syntax.Formula;
import syntax.Implies;

public class ImpliesI extends Deduction {
	public ImpliesI(Assumption p, Deduction pToQ) {
		super(logicalConsequence(p, pToQ), fromsToList(p, pToQ));
	}
	
	public static final Formula logicalConsequence(Assumption p, Deduction pToQ) {
		//TODO: Check that 'p' is a leaf-node in the deduction chain 'pToQ'.
		return new Implies(p.formula, pToQ.formula);
	}
	
	private static ArrayList<Deduction> fromsToList(Assumption p, Deduction pToQ) {
		ArrayList<Deduction> list = new ArrayList<Deduction>();
		list.add(p);
		list.add(pToQ);
		return list;
	}
}