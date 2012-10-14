package naturalDeduction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import syntax.Formula;

public abstract class BackwardRule extends Deduction {
	
	protected static Set<Class<? extends BackwardRule>> backwardRules = new HashSet<Class<? extends BackwardRule>>();
	
	public Goal newGoals = new Goal(); 
	
	public BackwardRule(Formula formula, ArrayList<Formula> froms) {
		super(formula, froms);
	}
	
	public BackwardRule(Formula formula, Formula from) {
		super(formula, from);
	}
	
	public BackwardRule(Formula formula) {
		super(formula);
	}
	
	public BackwardRule(Formula formula, Proof p) {
		super(formula, p);
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
	}
}
