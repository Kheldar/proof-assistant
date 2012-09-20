package syntax;

public class Implies extends BinaryConnective {
	static final Integer nary = 2;
	
	public Implies(Formula left, Formula right) {
		super(left, right);
	}
}