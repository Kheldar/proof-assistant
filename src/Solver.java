import naturalDeduction.*;
import syntax.*;
import tools.Reflection;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Solver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Reflection.init();
		Formula toProve = testCase2();
		
		System.out.println(toProve.getClass());
		
		//Construct core Theorem
		Theorem t = new Theorem(toProve);
		t.forward();
		t.backward();
		System.out.println(t);
		System.out.println(t.getDeductions());
	}
	
//	public static Boolean magic(Theorem t) {
//		if(!t.noDeductions()) {
//			forward(t);
//		}
//		
//		ArrayList<Formula> incompleteGoals = new ArrayList<Formula>();
//		
//		Formula current;
//		//While we have more goals
//		while(!t.goals.isEmpty()) {
//			current = t.goals.poll();
//			//Look at all our backwardy rules
//			for(Class<? extends BackwardRule> clas : BackwardRule.backwardRules()) {
//				try {
//					//Check to see if the rule matches the goal.
//					if(clas.getMethod("formulaClass", (Class<?>) null).invoke(null, (Object[]) null) == current.getClass()) {
//						//Create a new instance of the rule.
//						BackwardRule deduction = clas.getConstructor(Formula.class).newInstance(current);
//						
//						//Check to see what (if anything) we need in order to apply this rule.
//						if(!deduction.newGoals.directGoals.isEmpty()) {
//							//Check to see if we've already achieved those goals.
//							if(t.inKnowledge(deduction.newGoals.directGoals)) {
//								//Need to go through and get the formulas from IN knowledge (not enough that they're equal),
//								//they need to be the same object.
//								//Thankfully, newGoals.directGoals is relatively small.
//								for(Formula known : t.getKnowledge()) {
//									for(Formula goal : deduction.newGoals.directGoals) {
//										if(goal.equals(known)) {
//											deduction.froms.add(known);
//										}
//									}
//								}
//								t.add(deduction);
//								
//								//Need to go through forward again, since we added something new to the deductions.
//								forward(t);
//								
//								//Backtrack
//								
//								
//								
//							} else {
//								incompleteGoals.add(current);
//								for(Formula f : deduction.newGoals.directGoals) {
//									if(t.inKnowledge(f)) {
//										deduction.froms.add(f);
//									} else {
//										t.goals.add(f);
//									}
//								}
//								current.possibleFroms.add(deduction);
//							}
//						} else if(deduction.newGoals.proofGoal != null) {
//							//Note, should only reach here when all non-proof-goals in t.goals are exhausted.
//							if(magic(deduction.newGoals.proofGoal)) {
//								t.subproofs.add(deduction.newGoals.proofGoal);
//							}
//						}
//					}
//				} catch (Exception e) {
//					
//				}
//			}
//		}
//		
//		return false;
//	}
//	
//	public void fillGaps(List<Formula> incompleteGoals, Theorem t) { 
//		Boolean completed = false;
//		
//		ListIterator<Formula> i = incompleteGoals.listIterator(incompleteGoals.size());
//		
//		Formula f;
//		while(i.hasPrevious()) {
//			f = i.previous();
//			
//		}
//	}
	
//	public Theorem testCase1() {
//		Predicate p = new Predicate();
//		Predicate q = new Predicate();
//		
//		Assumption ap = new Assumption(p);
//		
//		Formula toProve = new Implies(p, new Implies(q,p));
//		
//		Theorem t = new Theorem(toProve);
//		
//		Proof main = t.subproofs.get(0);
//		
//		Proof sub = new Proof(ap, main);
//		sub.add(new Direct(ap));
//		
//		main.add(new ImpliesI(sub));
//		
//		return t;
//	}
	
	public static Formula testCase4() {
		Predicate p = new Predicate();
		Predicate q = new Predicate();
				
		Formula a = new Implies(p,q);
		Formula b = new And(p,a);
				
		return b;
	}
	
	public static Formula testCase5() {
		Predicate p = new Predicate("P");
		Predicate q = new Predicate("Q");
				
		
		Formula a = new Or(p,q);
		Formula b = new Implies(p,a);
		return b;
	}
	
	public static Formula testCase2() {
		Predicate p = new Predicate("P");
		Predicate q = new Predicate("Q");
		
		Formula sub = new Implies(q,p);
		Formula theorem = new Implies(p, sub);
		return theorem;
	}
	
	public static Formula testCase3() {
		Predicate p = new Predicate();
		Predicate q = new Predicate();
		
		return new And(p,q);
	}
}