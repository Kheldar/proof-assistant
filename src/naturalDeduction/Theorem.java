package naturalDeduction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Collection;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;

import naturalDeduction.BackwardRule.Goal;
import syntax.Formula;
import syntax.Implies;
import tools.Reflection;
import tools.RemoveListDuplicates;

public class Theorem {

	public Assumption assumption;
	protected HashSet<Formula> known = new HashSet<Formula>();
	public ArrayList<Proof> subproofs = new ArrayList<Proof>();
	public PriorityQueue<Formula> goals = new PriorityQueue<Formula>();
	public Stack<Formula> incompleteGoals = new Stack<Formula>();
	public Formula endGoal;
	
	public Boolean proven = false;
	Boolean needsForward = false;
	
	public Theorem(Formula toProve) {
		this.endGoal = toProve;
		goals.add(toProve);
		
		//TODO: If we already have toProve, add Direct deduction. BUT: Do not check 'inKnowledge'. It may not exist properly at this point.
	}
	
	public Theorem(Formula toProve, Assumption a) {
		this(toProve);
		this.assumption = a;
		known.add(a.consequent);
		needsForward = true;
	}
	
	public void add(Formula f) {
		if(f.equals(endGoal)) {
			known.add(f);
			proven = true;
			//Need to set this to a formula we've been working with, so we can backtrack.
			this.endGoal = f;
			needsForward = false;
		} else if(!inKnowledge(f)) {
			known.add(f);
			newDeduction(f);			
			needsForward = true;
		}
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Formula> getKnowledge() {
		return (Collection<Formula>) known.clone();
	}
	
	public Boolean inKnowledge(Formula f) {
		return getKnowledge().contains(f);
	}
	
	public Boolean inKnowledge(Collection<Formula> c) {
		return getKnowledge().containsAll(c);
	}
	
	public String toString() {
		String s = known.toString() + "\n";
		if(!subproofs.isEmpty()) {
			for(Proof p : subproofs) {
				s = s+p.toString();
			}
		}
		return s;
	}
	
	protected Integer depth() {
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public String constructProof() {
		String s = "";
		ArrayList<Deduction> ds = new ArrayList<Deduction>();
		ds.addAll(endGoal.deductionList());
		Collections.reverse(ds);
		ds = (ArrayList<Deduction>) RemoveListDuplicates.removeDuplicates(ds);

		Integer i = 0;
		for(Deduction d : ds) {
			s = s + i.toString() + "\t| " + d.toString() + "\n";
			if(d.getClass().equals(Assumption.class)) {
				s = s + "\t|--------------------\n";
			}
			i++;
		}
		
		return s;
	}
	
	public void run() {
		if(!proven) {
			forward();
			backward();
		}
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
		
		checkOldGoals();
		
		if(needsForward) {
			needsForward = false;
			forward();
		}
	}
	
	public void backward() {
		while(!goals.isEmpty()) {
			Formula f = goals.poll();
			newGoal(f);
			if(needsForward) {
				forward();
			}
		}
	}
	
	public void newGoal(Formula current) {
		for(Class<? extends BackwardRule> rule : BackwardRule.backwardRules()) {
			try {
				if(Reflection.formulaClass(rule).equals(current.getClass()) 
						|| Reflection.formulaClass(rule).equals(Formula.class)) {
					//Create a new instance of the rule.
					Goal g = Reflection.applyBackwards(rule, current, this);
					
					//Check to see what (if anything) we need in order to apply this rule.
					if(!g.directGoals.isEmpty()) {
						if(inKnowledge(g.directGoals)) {
							BackwardRule deduction = rule.getConstructor(Formula.class).newInstance(current);
							//Need to go through and get the formulas from IN knowledge (not enough that they're equal),
							//they need to be the same object.
							//Thankfully, newGoals.directGoals is relatively small.
							
							for(Formula know : getKnowledge()) {
								for(Formula goal : g.directGoals) {
									if(goal.equals(know)) {
										deduction.addPremise(know);
									}
								}
							}
							add(deduction.consequent);
						} else {
							for(Formula f : g.directGoals) {
								if(!inKnowledge(f) && !goals.contains(f)) {
									goals.add(f);
								}
							}
							current.possibleGoals.add(g);
							incompleteGoals.add(current);
						}
					} else if (g.proofGoal != null) {
						g.proofGoal.run();
						if(g.proofGoal.proven) {
							//TODO: Relectivise this.
							if(Reflection.formulaClass(rule).equals(Implies.class)) {
								BackwardRule deduction = rule.getConstructor(Proof.class).newInstance(g.proofGoal);
								add(deduction.consequent);
							} else if(Reflection.formulaClass(rule).equals(Implies.class)) {
								
							}
						} else {
							//TODO: Failure detection, etc.
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void checkOldGoals() {
		Stack<Formula> temp = new Stack<Formula>();
		Formula f;
		while(!incompleteGoals.empty()) {
			f = incompleteGoals.pop();
			Boolean clear = false;
			for(Goal g : f.possibleGoals) {
				if(inKnowledge(g.directGoals)) {
					clear = true;
					try {
						Deduction d = g.rule.getConstructor(Formula.class).newInstance(f);
						for(Formula know : getKnowledge()) {
							for(Formula premise : g.directGoals) {
								if(premise.equals(know)) {
									d.addPremise(know);
								}
							}
						}
						add(d.consequent);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			if(clear) {
				f.possibleGoals.clear();
			} else {
				temp.push(f);
			}
		}
		
		incompleteGoals = temp;
		
		//TODO: Proof goals?
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
							List<Formula> input = new ArrayList<Formula>();
							input.add(proven);
							Deduction d = rule.cast(rule.getConstructor(List.class).newInstance(input));
							add(d.consequent);
						}
					} else {
						List<Formula> input = new ArrayList<Formula>();
						input.add(proven);
						Deduction d = rule.cast(rule.getConstructor(List.class).newInstance(input));
						add(d.consequent);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}