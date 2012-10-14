package naturalDeduction;

import syntax.Formula;

public class Assumption extends Deduction {
	
	public Assumption(Formula formula) {
		super(formula);
		correct = true;
		complete = true;
	}
	
	protected int priority() {
		return 0;
	}
}