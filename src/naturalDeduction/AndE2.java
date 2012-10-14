package naturalDeduction;

import syntax.Formula;

public class AndE2 extends AndEs {
	
	public AndE2(Formula from) throws FormulaMismatch {
		super(logicalConsequence(from), from);
	}
	
	public static final Formula logicalConsequence(Formula from) throws FormulaMismatch {
		if(formulaClass.isInstance(from)) {
			return formulaClass.cast(from).right();
		} else {
			throw new FormulaMismatch();
		}
	}
}
