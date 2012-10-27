package tools;

import java.util.Collection;
import java.util.List;

import syntax.Formula;
import naturalDeduction.AndE1;
import naturalDeduction.AndE2;
import naturalDeduction.AndI;
import naturalDeduction.BackwardRule;
import naturalDeduction.BackwardRule.Goal;
import naturalDeduction.ClassicalContradiction;
import naturalDeduction.Deduction;
import naturalDeduction.ForwardRule;
import naturalDeduction.ImpliesE;
import naturalDeduction.ImpliesI;
import naturalDeduction.NotE;
import naturalDeduction.NotI;
import naturalDeduction.OrE;
import naturalDeduction.OrI1;
import naturalDeduction.OrI2;
import naturalDeduction.Theorem;

public class Reflection {
	
	public static Boolean hasCheck(Class<?extends Deduction> rule) {
		try {
			return (Boolean) rule.getMethod("hasCheck", null).invoke(null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static Class<?> formulaClass(Class<?extends Deduction> rule) {
		try {
			return (Class<?>) rule.getMethod("formulaClass", null).invoke(null,null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Formula check(Class<?extends Deduction> rule, Formula f, Collection<Formula> list) {
		try {
			return (Formula) rule.getMethod("check", Collection.class, Formula.class).invoke(null,
				list, f);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Boolean manyGoals(Class<?extends Deduction> rule){
		try {
			return (Boolean) rule.getMethod("manyGoals", null).invoke(null, null);
 		} catch (Exception e) {
 			e.printStackTrace();
 			return false;
 		}
	}
	
	public static void init() {
		ForwardRule.register(AndE1.class);
		ForwardRule.register(AndE2.class);
		ForwardRule.register(ImpliesE.class);
		ForwardRule.register(NotE.class);
		
		BackwardRule.register(ImpliesI.class);
		BackwardRule.register(AndI.class);
		BackwardRule.register(OrI1.class);
		BackwardRule.register(OrI2.class);
		BackwardRule.register(OrE.class);
		BackwardRule.register(NotI.class);
		BackwardRule.register(ClassicalContradiction.class);
		//BackwardRule.register(Direct.class);
	}
	
	public static Goal applyBackwards(Class<?extends Deduction> rule, Formula f, Theorem t) {
		try {
			return (Goal) rule.getMethod("applyBackwards", Formula.class, Theorem.class).invoke(null, f, t);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<Goal> applyManyBackwards(Class<?extends Deduction> rule, Formula f, Theorem t) {
		try {
			return (List<Goal>) rule.getMethod("applyBackwards", Formula.class, Theorem.class).invoke(null, f, t);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
