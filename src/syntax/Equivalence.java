package syntax;

public class Equivalence extends BinaryConnective {
	
	public Equivalence(Formula left, Formula right) {
		super(left, right);
	}
	
	@Override
	public int compareTo(Formula o) {
		return 1;
	}
}
