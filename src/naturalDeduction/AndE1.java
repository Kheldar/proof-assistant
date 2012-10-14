package naturalDeduction;

import syntax.Formula;

public class AndE1 extends AndEs {
	
	public AndE1(Formula from) throws FormulaMismatch {
		super(logicalConsequence(from), from);
	}
	
	public static final Formula logicalConsequence(Formula from) throws FormulaMismatch {
		if(formulaClass.isInstance(from)) {
			return formulaClass.cast(from).left();
		} else {
			throw new FormulaMismatch();
		}
	}
}