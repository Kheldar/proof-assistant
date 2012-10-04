package naturalDeduction;

import java.util.ArrayList;

import syntax.And;
import syntax.Formula;

public abstract class OrIs extends BackwardRule {

	protected static final Class<And> formulaClass = And.class;
	
	public OrIs(Formula formula, ArrayList<Deduction> froms) {
		super(formula, froms);
	}

	public OrIs(Formula formula, Deduction from) {
		super(formula, from);
	}

	public OrIs(Formula conclusion, Goal g) {
		super(conclusion);
		this.newGoals = g;
	}
	
	protected static ArrayList<Deduction> fromToList(Deduction from) {
		ArrayList<Deduction> list = new ArrayList<Deduction>();
		list.add(from);
		return list;
	}

}