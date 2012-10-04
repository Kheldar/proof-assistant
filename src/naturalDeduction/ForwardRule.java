package naturalDeduction;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import syntax.Formula;

public abstract class ForwardRule extends Deduction {
	
	protected static Set<Class<? extends ForwardRule>> forwardRules = new TreeSet<Class<? extends ForwardRule>>();
	
	//public abstract Class<? extends LogicalSymbol> formulaClass();
	
	public ForwardRule(Formula formula, ArrayList<Deduction> froms) {
		super(formula, froms);
	}
	
	public ForwardRule(Formula formula, Deduction from) {
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
