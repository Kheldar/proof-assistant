package naturalDeduction;

import java.util.ArrayList;

import syntax.Formula;
import syntax.LogicalSymbol;
import syntax.Or;

public abstract class OrIs extends BackwardRule {

	protected static final Class<Or> formulaClass = Or.class;

	public OrIs(Formula conclusion, Goal g) {
		super(conclusion);
		this.newGoals = g;
	}
	
	public OrIs(Formula consequence) {
		super(consequence);
	}
	
	public static final Class<? extends LogicalSymbol> formulaClass() {
		return formulaClass;
	}
}