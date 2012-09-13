package syntax;

public class And extends Connective {
	
	static final Integer nary = 2;
	
	public And(Formula left, Formula right) {
		subFormulas.add(left);
		subFormulas.add(right);
	}
}
