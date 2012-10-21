package naturalDeduction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import syntax.Formula;

public abstract class BackwardRule extends Deduction {
	
	protected static Set<Class<? extends BackwardRule>> backwardRules = new HashSet<Class<? extends BackwardRule>>();
	
	public Goal newGoals = new Goal(); 
	
	public BackwardRule(Formula consequence) {
		super(consequence);
	}
	
	public BackwardRule(Formula formula, Proof premise) {
		super(formula, premise);
	}
	
	// Would be better if we could do this reflectively.
	public static final Set<Class<? extends BackwardRule>> backwardRules() {
		return backwardRules;
	}
	
	public static void register(Class<? extends BackwardRule> rule) {
		backwardRules.add(rule);
	}
	
	/**
	 * @author Silk
	 *
	 * directGoals and proofGoals should not both be non-empty.
	 */
	public static class Goal {
		public ArrayList<Formula> directGoals = new ArrayList<Formula>();
		public Proof proofGoal = null;
		public Class<? extends BackwardRule> rule;
	}
}
