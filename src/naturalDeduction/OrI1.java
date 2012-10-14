package naturalDeduction;

import syntax.Formula;
import syntax.Or;

public class OrI1 extends OrIs {
	
	public OrI1(Formula consequence, Formula from) {
		super(logicalConsequence(consequence, from), fromToList(from));
	}
	
	public OrI1(Formula consequence) {
		super(consequence);
	}
	
	public static final Formula logicalConsequence(Formula consequence, Formula from) {
		//TODO: Check;
		return consequence;
	}
	
	public static Goal applyBackwards(Formula conclusion, Theorem t) throws FormulaMismatch {
		if(formulaClass.isInstance(conclusion)) {
			Goal ret = new Goal();
			ret.directGoals.add(formulaClass.cast(conclusion).left());
			return ret;
		} else {
			throw new FormulaMismatch();
		}
	}
}
