package naturalDeduction;

import syntax.Formula;

public class OrI2 extends OrIs {
	
	public OrI2(Formula consequence) {
		super(consequence);
	}
	
	public static Goal applyBackwards(Formula conclusion, Theorem t) throws FormulaMismatch {
		if(formulaClass.isInstance(conclusion)) {
			Goal ret = new Goal();
			ret.rule = OrI2.class;
			ret.directGoals.add(formulaClass.cast(conclusion).right());
			return ret;
		} else {
			throw new FormulaMismatch();
		}
	}
}