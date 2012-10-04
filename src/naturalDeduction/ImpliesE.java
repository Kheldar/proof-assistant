package naturalDeduction;

import java.util.ArrayList;

import java.util.Collection;

import syntax.Implies;
import syntax.Formula;

public class ImpliesE extends Deduction {
	
	protected static final Class<Implies> formulaClass = Implies.class;
	
	public ImpliesE(Deduction p, Deduction pIq) throws FormulaMismatch {
		super(logicalConsequence(pIq), fromsToList(p, pIq));
	}
	
	public static final Formula logicalConsequence(Deduction pIq) throws FormulaMismatch {
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
	
	public static Boolean hasCheck() {
		return true;
	}
	
	public static final Deduction check(Collection<Deduction> proofs, Formula lookingFor) {
		for(Deduction p : proofs) {
			if(p.formula.equals(lookingFor))
				return p;
		}
		return null;
	}
}
