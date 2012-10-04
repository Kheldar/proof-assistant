package naturalDeduction;

import syntax.Formula;

public class AndE2 extends AndEs {
	
	static {
		forwardRules.add(AndE2.class);
	}
	
	public AndE2(Deduction from) throws FormulaMismatch {
		super(logicalConsequence(from), from);
	}
	
	public static final Formula logicalConsequence(Deduction from) throws FormulaMismatch {
		if(formulaClass.isInstance(from.formula)) {
			return formulaClass.cast(from.formula).right();
		} else {
			throw new FormulaMismatch();
		}
	}
}
