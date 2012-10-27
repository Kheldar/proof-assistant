import naturalDeduction.*;
import syntax.*;
import tools.Reflection;

public class Solver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Reflection.init();
		
//		System.out.println("2: " + testCase2());
//		System.out.println("3: " + testCase3());
//		System.out.println("6: " + testCase6());
//		System.out.println("7: " + testCase7());
//		System.out.println("9: " + testCase9());
		System.out.println("10: " + testCase10());
	}
	
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
	
	public static Boolean testCase2() {
		Predicate p = new Predicate("P");
		Predicate q = new Predicate("Q");
		
		Formula sub = new Implies(q,p);
		Formula theorem = new Implies(p, sub);
		
		Theorem t = new Theorem(theorem);
		t.run();
		System.out.println(t);
		System.out.println(t.constructProof());
		
		return t.proven;
	}
	
	public static Boolean testCase3() {
		Predicate p = new Predicate();
		Predicate q = new Predicate();
		Predicate r = new Predicate();
		
		And a = new And(p,q);
		And b = new And(a, r);
		Theorem t = new Theorem(p, new Assumption(b));
		t.run();
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
		t.run();
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
		t.run();
		System.out.println(t);
        System.out.println(t.constructProof());

		return t.proven;
	}
	
	public static boolean testCase8() {
		Predicate p = new Predicate("P");
		Predicate q = new Predicate("Q");
		
		Formula a = new And(new Implies(new And(p,q), p), new Implies(new And(p,q), q));
		
		Theorem t = new Theorem(a);
		t.run();
		System.out.println(t);
        System.out.println(t.constructProof());

		return t.proven;
	}
	
	public static boolean testCase9() {
		Predicate p = new Predicate("P");
		Predicate q = new Predicate("Q");
		Predicate r = new Predicate("R");
		Predicate s = new Predicate("S");
		
		Formula a = new Implies(
						new And(
							new Or(p,q),
							new And(
								new And(
									new Implies(p,r),
									new Implies(q,r)
								),
							new Implies(r,s))), 
						s);
		
		Theorem t = new Theorem(a);
		t.run();
		System.out.println(t);
        System.out.println(t.constructProof());

		return t.proven;
	}
	
	public static boolean testCase10() {
		Predicate p = new Predicate("P");
		Predicate q = new Predicate("Q");
		
		Formula a = new Implies(p, new Implies(new Not(p), new Not(q)));
		
		Theorem t = new Theorem(a);
		t.run();
		System.out.println(t);
        System.out.println(t.constructProof());

		return t.proven;
	}
}