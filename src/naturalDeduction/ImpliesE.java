package naturalDeduction;

import java.util.ArrayList;

import syntax.Implies;
import syntax.Formula;

public class ImpliesE extends Deduction {
	
	protected static final Class<Implies> formulaClass = Implies.class;
	
	public ImpliesE(Deduction p, Deduction pIq) throws FormulaMismatch {
		super(logicalConsequence(p, pIq), fromsToList(p, pIq));
	}
	
	public static final Formula logicalConsequence(Deduction p, Deduction pIq) throws FormulaMismatch {
		if(formulaClass.isInstance(pIq)) {
			return formulaClass.cast(pIq.formula).right();
		} else {
			throw new FormulaMismatch();
		}
	}
	
	private static ArrayList<Deduction> fromsToList(Deduction p, Deduction pIq) {
		ArrayList<Deduction> list = new ArrayList<Deduction>();
		list.add(p);
		list.add(pIq);
		return list;
	}
}
