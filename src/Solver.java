import naturalDeduction.*;
import syntax.*;
import tools.Reflection;

public class Solver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Reflection.init();
		
		System.out.println("3: " + testCase3());
		System.out.println("6: " + testCase6());
		System.out.println("7: " + testCase7());
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
	
	public static Boolean testCase3() {
		Predicate p = new Predicate();
		Predicate q = new Predicate();
		Predicate r = new Predicate();
		
		And a = new And(p,q);
		And b = new And(a, r);
		Theorem t = new Theorem(p, new Assumption(b));
		t.forward();
		t.backward();
		System.out.println(t);
		System.out.println(t.constructProof());
		
		return t.proven;
	}
	
	public static boolean testCase6() {
		Predicate p = new Predicate("P");
		Predicate q = new Predicate("Q");
		Predicate r = new Predicate("R");
		
		Formula a = new Or(p, q);
		Formula b = new Or(a, r);
		Theorem t = new Theorem(b, new Assumption(p));
		t.forward();
		t.backward();
		System.out.println(t);
		System.out.println(t.constructProof());
		
		return t.proven;
	}
	
	public static boolean testCase7() {
		Predicate p = new Predicate("P");
		Predicate q = new Predicate("Q");
		Predicate r = new Predicate("R");
		
		Formula a = new And(p, new And(q,r));
		Formula b = new And(new And(p, q), r);
		Theorem t = new Theorem(b, new Assumption(a));
		t.forward();
		t.backward();
		System.out.println(t);
        System.out.println(t.constructProof());

		return t.proven;
	}
}