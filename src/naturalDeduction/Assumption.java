package naturalDeduction;

import syntax.Formula;

public class Assumption extends Deduction {
	
	public Assumption(Formula formula) {
		super(formula, null);
	}
	
}