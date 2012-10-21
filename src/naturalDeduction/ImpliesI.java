package naturalDeduction;
import syntax.Formula;
import syntax.Implies;
import syntax.LogicalSymbol;

public class ImpliesI extends BackwardRule {
	
	protected static final Class<Implies> formulaClass = Implies.class;
	
	public ImpliesI(Proof premise) throws BadPremises {
		super(applyForwards(premise), premise);
	}
	
	public static Formula applyForwards(Proof premise) throws BadPremises {
		if(premise.proven) {
			return new Implies(premise.assumption.consequent, premise.endGoal);
		} else {
			throw new BadPremises();
		}
	}
	
	public static Class<? extends LogicalSymbol> formulaClass() {
		return formulaClass;
	}
	
	public static Goal applyBackwards(Formula conclusion, Theorem t) throws FormulaMismatch {
		if(formulaClass.isInstance(conclusion)) {
			Goal ret = new Goal();
			Implies i = (Implies) conclusion;
			ret.proofGoal = new Proof(i.right(), new Assumption(i.left()), t);
			return ret;
		} else {
			throw new FormulaMismatch();
		}
	}
}