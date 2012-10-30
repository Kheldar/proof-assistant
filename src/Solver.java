import mouse.runtime.SourceString;
import naturalDeduction.*;
import syntax.*;
import tools.Reflection;
import parser.*;

public class Solver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Reflection.init();
		
		FormulaParser p = new FormulaParser();
		p.parse(new SourceString(args[0]));
		System.out.println(p.rhs(0).get());
		Theorem t = new Theorem((Formula) p.rhs(0).get());
		t.run();
		System.out.println(t.constructProof());
		System.out.println(t.proven);
		
		
//		System.err.println("2: " + testCase2());
//		System.err.println("3: " + testCase3());
//		System.err.println("6: " + testCase6());
//		System.err.println("7: " + testCase7());
//		System.err.println("8: " + testCase8());
//		System.err.println("9: " + testCase9());
//		System.err.println("10: " + testCase10());
//		System.err.println("11: " + testCase11());
	}
	
	public static boolean testCase2() {
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
	
	public static boolean testCase3() {
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
	
	public static boolean testCase11() {
		Predicate p = new Predicate("P");
		
		Formula a = new Implies(new False(), p);
		
		Theorem t = new Theorem(a);
		t.run();
		System.out.println(t);
        System.out.println(t.constructProof());

		return t.proven;
	}
}