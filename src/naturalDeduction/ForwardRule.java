package naturalDeduction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import syntax.Formula;

public abstract class ForwardRule extends Deduction {
	
	public static Set<Class<? extends ForwardRule>> forwardRules = new HashSet<Class<? extends ForwardRule>>();
	
	//public abstract Class<? extends LogicalSymbol> formulaClass();
	
	public ForwardRule(Formula formula, ArrayList<Formula> froms) {
		super(formula, froms);
	}
	
	public ForwardRule(Formula formula, Formula from) {
		super(formula, from);
	}
	
	public ForwardRule(Formula formula) {
		super(formula);
	}
	
	// Would be better if we could do this reflectively.
	public static final Set<Class<? extends ForwardRule>> forwardRules() {
		return forwardRules;
	}
}
