package naturalDeduction;

import java.util.ArrayList;

import syntax.Formula;
import syntax.LogicalSymbol;
import syntax.Or;

public abstract class OrIs extends BackwardRule {

	protected static final Class<Or> formulaClass = Or.class;
	
	public OrIs(Formula formula, ArrayList<Formula> froms) {
		super(formula, froms);
	}

	public OrIs(Formula formula, Formula from) {
		super(formula, from);
	}

	public OrIs(Formula conclusion, Goal g) {
		super(conclusion);
		this.newGoals = g;
	}
	
	public OrIs(Formula consequence) {
		super(consequence);
	}
	
	protected static ArrayList<Formula> fromToList(Formula from) {
		ArrayList<Formula> list = new ArrayList<Formula>();
		list.add(from);
		return list;
	}
	
	public static final Class<? extends LogicalSymbol> formulaClass() {
		return formulaClass;
	}
}