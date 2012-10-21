package naturalDeduction;

import java.util.Collection;
import java.util.List;

import syntax.Formula;

public class Direct extends BackwardRule {

	protected static final Class<Formula> formulaClass = Formula.class;
	
	public Direct(Formula consequent) {
		super(consequent);
	}
	
	public static Boolean hasCheck() {
		return false;
	}
	
	public static Class<Formula> formulaClass() {
		return formulaClass;
	}
	
	public static Formula applyForward(List<Formula> premises) throws BadPremises {
		if(premises.size() != 1) {
			throw new BadPremises();
		} else {
			return premises.get(0);
		}
	}
	
	public static Goal applyBackwards(Formula conclusion, Theorem t) {
		Goal g = new Goal();
		g.directGoals.add(conclusion);
		return g;
	}
}
