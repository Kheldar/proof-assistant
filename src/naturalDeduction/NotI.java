package naturalDeduction;

import syntax.False;
import syntax.Formula;
import syntax.Implies;
import syntax.LogicalSymbol;
import syntax.Not;

public class NotI extends BackwardRule {
	
	protected static final Class<Not> formulaClass = Not.class;
	
	public NotI(Formula consequent) {
		super(consequent);
	}
	
	public static Goal applyBackwards(Formula conclusion, Theorem t) throws FormulaMismatch {
		if(formulaClass.isInstance(conclusion)) {
			Not n = (Not) conclusion;
			Goal g = new Goal();
			g.rule = NotI.class;
			g.directGoals.add(new Implies(n.subFormula(), new False()));
			return g;
		} else {
			throw new FormulaMismatch();
		}
	}
	
	public static final Class<? extends LogicalSymbol> formulaClass() {
		return formulaClass;
	}
	
}
