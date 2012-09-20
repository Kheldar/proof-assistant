package syntax;

public class Or extends BinaryConnective {
	static final Integer nary = 2;
	
	public Or(Formula left, Formula right) {
		super(left, right);
	}
}
