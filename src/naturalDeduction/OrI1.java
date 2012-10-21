package naturalDeduction;

import syntax.Formula;

public class OrI1 extends OrIs {
	
	public OrI1(Formula consequence) {
		super(consequence);
	}
	
	public static Goal applyBackwards(Formula conclusion, Theorem t) throws FormulaMismatch {
		if(formulaClass.isInstance(conclusion)) {
			Goal ret = new Goal();
			ret.rule = OrI1.class;
			ret.directGoals.add(formulaClass.cast(conclusion).left());
			return ret;
		} else {
			throw new FormulaMismatch();
		}
	}
}
