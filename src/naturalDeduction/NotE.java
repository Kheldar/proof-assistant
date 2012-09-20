package naturalDeduction;

import java.util.ArrayList;

import syntax.Formula;
import syntax.Not;

//Possibly equiv to Double-Negation Elimination? Need to check this.
public class NotE extends Deduction {
	
	protected static final Class<Not> formulaClass = Not.class;
	
	public NotE(Deduction notP, Deduction notPToNeg) throws FormulaMismatch  {
		super(logicalConsequence(notP, notPToNeg), fromToList(notPToNeg));
	}
	
	public static final Formula logicalConsequence(Deduction notP, Deduction NotPToNeg) throws FormulaMismatch {
		if(formulaClass.isInstance(notP.formula)) {
			return formulaClass.cast(notP.formula).subFormula();
		} else {
			throw new FormulaMismatch();
		}
	}
	
	private static ArrayList<Deduction> fromToList(Deduction notPToNeg) {
		ArrayList<Deduction> list = new ArrayList<Deduction>();
		list.add(notPToNeg);
		return list;
	}
}
