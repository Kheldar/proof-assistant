package naturalDeduction;

import java.util.ArrayList;

import syntax.Formula;
import syntax.Not;

//Possibly equiv to Double-Negation Elimination? Need to check this.
public class NotE extends Deduction {
	
	protected static final Class<Not> formulaClass = Not.class;
	
	public NotE(Deduction notP) throws FormulaMismatch  {
		super(logicalConsequence(notP), fromToList(notP));
	}
	
	public static final Formula logicalConsequence(Deduction notP) throws FormulaMismatch {
		if(NotE.checkType(notP.formula)) {
			Not notnot = formulaClass.cast(notP.formula);
			Not not = (Not) notnot.subFormula();
			return not.subFormula();
		} else {
			throw new FormulaMismatch();
		}
	}
	
	private static Boolean checkType(Formula nnP) {
		if(formulaClass.isInstance(nnP)) {
			Not not = formulaClass.cast(nnP);
			if(formulaClass.isInstance(not.subFormula())) {
				return true;
			}
		}
		
		return false;
	}
	
	private static ArrayList<Deduction> fromToList(Deduction notPToNeg) {
		ArrayList<Deduction> list = new ArrayList<Deduction>();
		list.add(notPToNeg);
		return list;
	}
}
