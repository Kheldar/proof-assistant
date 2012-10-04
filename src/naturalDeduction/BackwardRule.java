package naturalDeduction;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import syntax.Formula;

public abstract class BackwardRule extends Deduction {
	
	protected static Set<Class<? extends BackwardRule>> backwardRules = new TreeSet<Class<? extends BackwardRule>>();
	
	public Goal newGoals = new Goal(); 
	
	public BackwardRule(Formula formula, ArrayList<Deduction> froms) {
		super(formula, froms);
	}
	
	public BackwardRule(Formula formula, Deduction from) {
		super(formula, from);
	}
	
	public BackwardRule(Formula formula) {
		super(formula);
	}
	
	// Would be better if we could do this reflectively.
	public static final Set<Class<? extends BackwardRule>> backwardRules() {
		return backwardRules;
	}
	
	public static class Goal {
		public ArrayList<Formula> directGoals = new ArrayList<Formula>();
		public ArrayList<Proof> proofGoals = new ArrayList<Proof>();
	}
}
