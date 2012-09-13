package syntax;

public class Or extends Connective {
	static final Integer nary = 2;
	
	public Or(Formula left, Formula right) {
		subFormulas.add(left);
		subFormulas.add(right);
	}
}
