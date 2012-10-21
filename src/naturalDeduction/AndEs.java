package naturalDeduction;

import java.util.List;

import syntax.And;
import syntax.Formula;
import syntax.LogicalSymbol;

public abstract class AndEs extends ForwardRule {

	protected static final Class<And> formulaClass = And.class;
	
	public AndEs(Formula consequent, List<Formula> premises) {
		super(consequent, premises);
	}
	
	public static final Class<? extends LogicalSymbol> formulaClass() {
		return formulaClass;
	}
}