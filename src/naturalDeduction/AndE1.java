package naturalDeduction;

import syntax.Formula;

public class AndE1 extends AndEs {
	
	static {
		forwardRules.add(AndE2.class);
	}
	
	public AndE1(Deduction from) throws FormulaMismatch {
		super(logicalConsequence(from), from);
	}
	
	public static final Formula logicalConsequence(Deduction from) throws FormulaMismatch {
		if(formulaClass.isInstance(from.formula)) {
			return formulaClass.cast(from.formula).left();
		} else {
			throw new FormulaMismatch();
		}
	}
}