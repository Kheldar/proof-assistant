package naturalDeduction;

import java.util.ArrayList;

import java.util.Collection;

import syntax.Implies;
import syntax.Formula;
import syntax.LogicalSymbol;

public class ImpliesE extends ForwardRule {
	
	protected static final Class<Implies> formulaClass = Implies.class;
	
	public ImpliesE(Formula p, Formula pIq) throws FormulaMismatch {
		super(logicalConsequence(pIq), fromsToList(p, pIq));
	}
	
	public static final Formula logicalConsequence(Formula pIq) throws FormulaMismatch {
		if(formulaClass.isInstance(pIq)) {
			return formulaClass.cast(pIq).right();
		} else {
			throw new FormulaMismatch();
		}
	}
	
	private static ArrayList<Formula> fromsToList(Formula p, Formula pIq) {
		ArrayList<Formula> list = new ArrayList<Formula>();
		list.add(p);
		list.add(pIq);
		return list;
	}
	
	public static Boolean hasCheck() {
		return true;
	}
	
	//Need to make sure we have p;
	public static final Formula check(Collection<Formula> proofs, Formula lookingFor) {
		Implies temp = (Implies) lookingFor;
		if(proofs.contains(temp.left()))
			return temp.left();
		else
			return null;
	}
	
	public static final Class<? extends LogicalSymbol> formulaClass() {
		return formulaClass;
	}
}
