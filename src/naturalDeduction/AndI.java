package naturalDeduction;

import java.util.List;
import syntax.And;
import syntax.Formula;
import syntax.LogicalSymbol;

public class AndI extends BackwardRule {
	
	protected static final Class<And> formulaClass = And.class;
	
	public AndI(Formula consequent) {
		super(consequent);
	}
	
	public static Formula applyForward(List<Formula> premises) throws BadPremises {
		if(premises.size() != 2) {
			throw new BadPremises();
		} else {
			return new And(premises.get(0), premises.get(1));
		}
	}
	
	public static Goal applyBackwards(Formula conclusion, Theorem t) throws FormulaMismatch {
		if(formulaClass.isInstance(conclusion)) {
			Goal ret = new Goal();
			And a = (And) conclusion;
			ret.directGoals.add(a.left());
			ret.directGoals.add(a.right());
			ret.rule = AndI.class;
			return ret;
		} else {
			throw new FormulaMismatch();
		}
	}
	
	public static final Class<? extends LogicalSymbol> formulaClass() {
		return formulaClass;
	}
}