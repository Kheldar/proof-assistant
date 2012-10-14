package naturalDeduction;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.PriorityQueue;

import naturalDeduction.BackwardRule.Goal;
import syntax.Formula;
import syntax.Implies;
import tools.Reflection;

public class Theorem {

	public Assumption assumption;
	protected ArrayList<Deduction> deductions = new ArrayList<Deduction>();
	protected HashSet<Formula> known = new HashSet<Formula>();
	public ArrayList<Proof> subproofs = new ArrayList<Proof>();
	public PriorityQueue<Formula> goals = new PriorityQueue<Formula>();
	public final Formula endGoal;
	
	Boolean proven = false;
	Boolean needsForward = false;
	
	public Theorem(Formula toProve) {
		this.endGoal = toProve;
		goals.add(toProve);
	}
	
	public Theorem(Formula toProve, Assumption a) {
		this(toProve);
		this.assumption = a;
		deductions.add(a);
		known.add(a.formula);
		needsForward = true;
	}
	
	public void add(Deduction d) {
		if(!inKnowledge(d.formula)) {
			deductions.add(d);
			known.add(d.formula);
			newDeduction(d.formula);
			if(d.formula.equals(endGoal)) {
				proven = true;
			} else {
				needsForward = true;
			}
		}
	}
	
	public void addAll(Collection<Deduction> c) {
		deductions.addAll(c);
		for(Deduction d : c) {
			known.add(d.formula);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Deduction> getDeductions() {
		return (Collection<Deduction>) deductions.clone();
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Formula> getKnowledge() {
		return (Collection<Formula>) known.clone();
	}
	
	public Deduction inDeductions(Formula f) {
		if(inKnowledge(f)) {
			for(Deduction d : deductions) {
				if(d.formula.equals(f)) {
					return d;
				}
			}
		}
		return null;
	}
	
	public Boolean inKnowledge(Formula f) {
		return getKnowledge().contains(f);
	}
	
	public Boolean inKnowledge(Collection<Formula> c) {
		return getKnowledge().containsAll(c);
	}
	
	public Boolean noDeductions() {
		return deductions.isEmpty();
	}
	
	public Integer numDeductions() {
		return deductions.size();
	}
	
	public String toString() {
		return known.toString() + (subproofs.isEmpty()? "" : "\n\t" + subproofs.toString());
	}
	
	public void run() {
		forward();
		backward();
	}
	
	public void forward() {
		ArrayList<Formula> tempK = new ArrayList<Formula>(getKnowledge());
		
		//This is HORRIBLY unsafe. Do not use in a concurrent setting.
		int i = 0;
		while(i < tempK.size()) {
			Formula proven = tempK.get(i);
				newDeduction(proven);
			i++;
		}
		
		if(needsForward) {
			needsForward = false;
			forward();
		}
	}
	
	public void backward() {
		while(!goals.isEmpty()) {
			Formula f = goals.poll();
			newGoal(f);
		}
	}
	
	public void newGoal(Formula current) {
		for(Class<? extends BackwardRule> rule : BackwardRule.backwardRules()) {
			try {
				if(Reflection.formulaClass(rule).equals(current.getClass())) {
					//Create a new instance of the rule.
					Goal g = Reflection.applyBackwards(rule, current, this);
					
					//Check to see what (if anything) we need in order to apply this rule.
					if(!g.directGoals.isEmpty()) {
						if(inKnowledge(g.directGoals)) {
							BackwardRule deduction = rule.getConstructor(Formula.class).newInstance(current);
							//Need to go through and get the formulas from IN knowledge (not enough that they're equal),
							//they need to be the same object.
							//Thankfully, newGoals.directGoals is relatively small.
							
							for(Formula known : getKnowledge()) {
								for(Formula goal : deduction.newGoals.directGoals) {
									if(goal.equals(known)) {
										deduction.froms.add(known);
									}
								}
							}
							add(deduction);
						} else {
							for(Formula f : g.directGoals) {
								if(!inKnowledge(f) && goals.contains(f)) {
									goals.add(f);
								}
							}
							//current.possibleFroms.add(deduction);
						}
					} else if (g.proofGoal != null) {
						g.proofGoal.run();
						if(g.proofGoal.proven) {
							//TODO: Relectivise this.
							if(Reflection.formulaClass(rule).equals(Implies.class)) {
								BackwardRule deduction = rule.getConstructor(Proof.class).newInstance(g.proofGoal);
								add(deduction);
							} else if(false) {
								//TODO: False goal;
							}
						} else {
							//TODO: ???
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void newDeduction(Formula proven) {
		for(Class<? extends ForwardRule> rule : ForwardRule.forwardRules()) {
			try {
				//Check if our formula matches the Class appropriate for the rule.
				if(Reflection.formulaClass(rule).equals(proven.getClass())) {
					// Check to see if the rule needs to check anything. (Different constructor profiles).
					if((Reflection.hasCheck(rule))) {
						//Check that we meet the requirements. (And return the requirements).
						Formula f = Reflection.check(rule, proven, getKnowledge());
						if(f != null) {
							Deduction d = rule.cast(rule.getConstructor(Formula.class, Formula.class).newInstance(f, proven));
							add(d);
						}
					} else {
						Deduction d = rule.cast(rule.getConstructor(Formula.class).newInstance(proven));
						add(d);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}