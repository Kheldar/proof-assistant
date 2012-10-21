package naturalDeduction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import syntax.Formula;

public abstract class ForwardRule extends Deduction {
	
	protected static Set<Class<? extends ForwardRule>> forwardRules = new HashSet<Class<? extends ForwardRule>>();
	
	public ForwardRule(Formula consequent, List<Formula> premises) {
		super(consequent, premises);
	}
	
	public ForwardRule(Formula consequent) {
		super(consequent);
	}

	public static void register(Class<? extends ForwardRule> rule) {
		forwardRules.add(rule);
	}
	
	// Would be better if we could do this reflectively.
	public static final Set<Class<? extends ForwardRule>> forwardRules() {
		return forwardRules;
	}
}
