package syntax;

public class Implies extends Connective {
	static final Integer nary = 2;
	
	public Implies(Formula left, Formula right) {
		subFormulas.add(left);
		subFormulas.add(right);
	}
}