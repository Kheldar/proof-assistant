package syntax;

public class Equivalence extends Connective {
	static final Integer nary = 2;
	
	public Equivalence(Formula left, Formula right) {
		subFormulas.add(left);
		subFormulas.add(right);
	}
}
