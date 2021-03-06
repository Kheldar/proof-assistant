package naturalDeduction;

import java.util.Collection;

import syntax.False;
import syntax.Formula;
import syntax.Implies;
import syntax.Not;

public class ClassicalContradiction extends BackwardRule {

	protected static final Class<Formula> formulaClass = Formula.class;
	
	public ClassicalContradiction(Formula consequence) {
		super(consequence);
	}
	
	public static Boolean hasCheck() {
		return true;
	}
	
	public static final Formula check(Collection<Formula> proofs, Formula toCheck) {
		if(toCheck.getClass().equals(False.class) || toCheck.getClass().equals(Implies.class))
			return null;
		else
			return toCheck;
	}
	
	public static Goal applyBackwards(Formula conclusion, Theorem t) {
		Goal g = new Goal();
		g.rule = ClassicalContradiction.class;
		g.directGoals.add(new Implies(new Not(conclusion), new False()));
		return g;
	}
	
	public static final Class<? extends Formula> formulaClass() {
		return formulaClass;
	}
}
