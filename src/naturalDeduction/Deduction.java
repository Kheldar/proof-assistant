package naturalDeduction;

import syntax.Formula;

public abstract class Deduction {
	Formula formula;
		
	public Deduction(Formula formula) {
		this.formula = formula;
	}
}