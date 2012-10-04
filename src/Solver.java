import naturalDeduction.*;
import syntax.*;

import java.util.ArrayList;
import java.util.Stack;

public class Solver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Formula toProve = testCase2();
		
		System.out.println(toProve.getClass());
		
		//Construct core Theorem
		Theorem t = new Theorem(toProve);
		//magic(t);
	}
	
	public static void magic(Theorem t) {
		if(!t.noDeductions()) {
			forward(t);
		}
		
		Stack<Deduction> solvedGoals = new Stack<Deduction>();
		Stack<Formula> goals = new Stack<Formula>();
		goals.addAll(t.goals);
		
		while(!goals.empty()) {
			Formula f = goals.pop();
			for(Class<? extends BackwardRule> clas : BackwardRule.backwardRules()) {
				try {
					if(clas.getMethod("formulaClass", (Class<?>) null).invoke(null, 0) == f.getClass()) {
						if(((Boolean) clas.getMethod("hasCheck", (Class<?>)  null).invoke(null,  0))) {
							//TODO
						} else {
							BackwardRule d = clas.cast(clas.getConstructor(Formula.class).newInstance(f));
							solvedGoals.add(d);
							if(!d.newGoals.directGoals.isEmpty()) {
								for(Formula g : d.newGoals.directGoals) {
									Deduction from = t.inDeductions(g);
									if(from != null) {
										//TODO: Up to here.
									}
								}
							}
						}
					}
				} catch (Exception e) {
					
				}
			}
		}
	}
	
	public static void forward(Theorem t) {
		ArrayList<Deduction> tempD = new ArrayList<Deduction>(t.getDeductions());
		
		//This is HORRIBLY unsafe. Do not use in a concurrent setting.
		int i = 0;
		while(i < tempD.size()) {
			Deduction proof = tempD.get(i);
			for(Class<? extends ForwardRule> clas : ForwardRule.forwardRules()) {
				//Reflection all up in this ^_^
				try {
					//Check if our formula matches the Class appropriate for the rule.
					if(clas.getMethod("formulaClass", (Class<?>) null).invoke(null, 0) == proof.formula.getClass()) {
						// Check to see if the rule needs to check anything. (Different constructor profiles).
						if(((Boolean) clas.getMethod("hasCheck", (Class<?>)  null).invoke(null,  0))) {
							
							//Check that we meet the requirements. (And return the requirements).
							Deduction p = (Deduction) clas.getMethod("check", tempD.getClass(), Formula.class).invoke(null,
									tempD, proof.formula);
							if(p != null) {
								tempD.add(clas.cast(clas.getConstructor(Deduction.class, Deduction.class).newInstance(p, proof)));
							}
						} else {
							tempD.add(clas.cast(clas.getConstructor(Deduction.class).newInstance(proof)));
						}
					}
				} catch (Exception e) {
					
				}
			}
			i++;
		}
		
		t.addAll(tempD.subList(t.numDeductions(), tempD.size() - 1));
	}
	
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
	
	public static Formula testCase2() {
		Predicate p = new Predicate();
		Predicate q = new Predicate();
		
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