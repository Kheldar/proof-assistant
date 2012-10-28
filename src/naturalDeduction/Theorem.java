package naturalDeduction;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Collection;
import java.util.PriorityQueue;

import naturalDeduction.BackwardRule.Goal;
import syntax.Formula;
import syntax.Implies;
import tools.Reflection;
import tools.ListOperations;
import tools.SpecHashSet;

public class Theorem {

	public Assumption assumption;
	protected SpecHashSet known = new SpecHashSet();
	public ArrayList<Proof> subproofs = new ArrayList<Proof>();
	public PriorityQueue<Formula> goals = new PriorityQueue<Formula>();
	public LinkedList<Formula> incompleteGoals = new LinkedList<Formula>();
	public Formula endGoal;
	
	public Boolean proven = false;
	Boolean needsForward = false;
	
	public Theorem(Formula toProve) {
		try {
			this.endGoal = (Formula) toProve.clone();
			goals.add(this.endGoal);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Theorem(Formula toProve, Assumption a) {
		this(toProve);
		this.assumption = a;
		addKnowledge(a);
		needsForward = true;
	}
	
	public void add(Deduction d) {
		Formula f = d.consequent;
		
//		System.err.println("Context: " + assumption + " ---> " + endGoal);
//		System.err.println(d);
		if(f.equals(endGoal)) {		
			addKnowledge(d);
			proven = true;
			//Need to set this to a formula we've been working with, so we can backtrack.
			this.endGoal = f;
			needsForward = false;
		} else if(!inKnowledge(f)) {
			addKnowledge(d);
			newDeduction(f);
			needsForward = true;
		}
	}
	
	protected void addKnowledge(Deduction d) {
		known.add(d.consequent);
		d.consequent.setFrom(d);
	}
	
	@SuppressWarnings("unchecked")
	public SpecHashSet getKnowledge() {
		return (SpecHashSet) known.clone();
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
	
	public String constructProof() {
		return constructProof(0, new Integer[]{0});
	}
	
	@SuppressWarnings("unchecked")
	protected String constructProof(Integer level, Integer[] lineNo) {
		StringBuffer s = new StringBuffer();
		ArrayList<Deduction> ds = new ArrayList<Deduction>();
		ds.addAll(endGoal.deductionList());
		
		if(assumption != null && !ds.contains(assumption))
			ds.add(assumption);
		Collections.reverse(ds);
		ds = (ArrayList<Deduction>) ListOperations.removeDuplicates(ds);
		
		for(Deduction d : ds) {
			if(d.getClass().equals(ImpliesI.class)) {
				s.append(d.proofPremise.constructProof(level + 1, lineNo));
			}
			
			//Check to see if knowledge is "local"
			if(known.contains(d.consequent)) {
				s.append(lineNo[0].toString() + "\t" + repeatString(" |", level + 1));
				s.append(d.toString() + "\n");
				lineNo[0]++;
			}
			
			if(d.equals(assumption)) {
				s.append("\t" + repeatString(" |", level + 1));
				s.append("--------------------\n");
			}
		}
		
		return s.toString();
	}
	
	protected String repeatString(String s, Integer n) {
		StringBuffer buf = new StringBuffer(s.length() * n);
		for(int i = 0; i < n; i++)
			buf.append(s);
		return buf.toString();
	}
	
	public void run() {
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			
		}
		
		if(inKnowledge(endGoal) && (endGoal.getFrom() == null || endGoal.getFrom().getPremises().isEmpty())) {
			Deduction d = new Direct(endGoal);
			add(d);
		}
		
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
		//TODO: Add check to see if we already have the goal.
		while(!proven && !goals.isEmpty()) {
			Formula f = goals.poll();
//			System.err.println("Context: " + assumption + " ---> " + endGoal);
//			System.err.println(f);
			newGoal(f);
			if(needsForward) {
				forward();
			}
		}
	}
	
	public Boolean handleSingleGoal(Goal g, Class<? extends BackwardRule> rule, Formula current) throws InstantiationException, 
		IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Boolean b;
		if(b = inKnowledge(g.directGoals)) {
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
			add(deduction);
		} else {
			for(Formula f : g.directGoals) {
				if(!inKnowledge(f) && !goals.contains(f)) {
					goals.add(f);
				}
			}
			current.possibleGoals.add(g);
			incompleteGoals.add(current);
		}
		
		return b;
	}
	
	public void newGoal(Formula current) {
		for(Class<? extends BackwardRule> rule : BackwardRule.backwardRules()) {
			try {
				if(Reflection.formulaClass(rule).equals(current.getClass()) 
						|| Reflection.formulaClass(rule).equals(Formula.class)) {
					
					Formula chk = null;
					if(Reflection.hasCheck(rule)) {
						chk = Reflection.check(rule, current, getKnowledge());
					}
					
					
					if(!Reflection.hasCheck(rule) || chk != null) {
						if(Reflection.manyGoals(rule)) {
							List<Goal> goals = Reflection.applyManyBackwards(rule, current, this);
							for(Goal g : goals) {							
								if(handleSingleGoal(g, rule, current))
									break;
							}
						} else {
							Goal g = Reflection.applyBackwards(rule, current, this);
							//Check to see what (if anything) we need in order to apply this rule.
							if(!g.directGoals.isEmpty()) {
								handleSingleGoal(g, rule, current);
							} else if (g.proofGoal != null) {
								g.proofGoal.run();
								if(g.proofGoal.proven) {
									//TODO: Relectivise this.
									if(Reflection.formulaClass(rule).equals(Implies.class)) {
										BackwardRule deduction = rule.getConstructor(Proof.class).newInstance(g.proofGoal);
										add(deduction);
									}
								} else {
									//TODO: Failure detection, etc.
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void checkOldGoals() {
		LinkedList<Formula> temp = new LinkedList<Formula>();
		Formula f;
		while(!proven && !incompleteGoals.isEmpty()) {
			f = incompleteGoals.poll();
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
						add(d);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			if(clear) {
				f.possibleGoals.clear();
			} else {
				temp.add(f);
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
							input.add(f);
							Deduction d = rule.cast(rule.getConstructor(List.class).newInstance(input));
							add(d);
						}
					} else {
						List<Formula> input = new ArrayList<Formula>();
						input.add(proven);
						Deduction d = rule.cast(rule.getConstructor(List.class).newInstance(input));
						add(d);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}