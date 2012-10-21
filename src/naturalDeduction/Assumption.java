package naturalDeduction;

import syntax.Formula;

public class Assumption extends Deduction {
	
	public Assumption(Formula formula) {
		super(formula);
	}
	
	protected int priority() {
		return 0;
	}
}