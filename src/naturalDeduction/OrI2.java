package naturalDeduction;

import syntax.Formula;
import syntax.Or;

public class OrI2 extends OrIs {
	
	public OrI2(Formula intro, Formula from) {
		super(logicalConsequence(intro, from), fromToList(from));
	}
	
	public static final Formula logicalConsequence(Formula intro, Formula from) {
		return new Or(from, intro);
	}
	
	public static Goal applyBackwards(Formula conclusion, Theorem t) throws FormulaMismatch {
		if(formulaClass.isInstance(conclusion)) {
			Goal ret = new Goal();
			ret.directGoals.add(formulaClass.cast(conclusion).right());
			return ret;
		} else {
			throw new FormulaMismatch();
		}
	}
}