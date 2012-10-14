package syntax;

public class Or extends BinaryConnective {
	static final Integer nary = 2;
	
	public Or(Formula left, Formula right) {
		super(left, right);
	}
	
	@Override
	public int compareTo(Formula o) {
		// TODO Auto-generated method stub
		return 2;
	}
	
	public String toString() {
		return "(" + left().toString() + " OR " + right().toString() + ")";
	}
}
