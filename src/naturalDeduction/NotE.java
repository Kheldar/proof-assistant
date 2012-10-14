package naturalDeduction;

import java.util.ArrayList;

import syntax.Formula;
import syntax.Not;

//Should be:
//[~p] .. F | p
public class NotE extends Deduction {
	
	protected static final Class<Not> formulaClass = Not.class;
	
	public NotE(Formula notP) throws FormulaMismatch  {
		super(logicalConsequence(notP), fromToList(notP));
	}
	
	public static final Formula logicalConsequence(Formula notP) throws FormulaMismatch {
		if(NotE.checkType(notP)) {
			Not notnot = formulaClass.cast(notP);
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
	
	private static ArrayList<Formula> fromToList(Formula notPToNeg) {
		ArrayList<Formula> list = new ArrayList<Formula>();
		list.add(notPToNeg);
		return list;
	}
}