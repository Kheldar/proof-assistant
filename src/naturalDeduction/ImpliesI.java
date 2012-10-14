package naturalDeduction;
import syntax.Formula;
import syntax.Implies;
import syntax.LogicalSymbol;

public class ImpliesI extends BackwardRule {
	
	protected static final Class<Implies> formulaClass = Implies.class;
	
	public ImpliesI(Proof p) {
		super(logicalConsequence(p), p);
	}
	
	public static final Formula logicalConsequence(Proof p) {
		//TODO: Check that 'p' is a leaf-node in the deduction chain 'pToQ'.
		return new Implies(p.assumption.formula, p.endGoal);
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