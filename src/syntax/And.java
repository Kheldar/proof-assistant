package syntax;

public class And extends BinaryConnective {
	
	public And(Formula left, Formula right) {
		super(left, right);
	}
	
	@Override
	public int compareTo(Formula o) {
		return 0;
	}
	
	public String toString() {
		return "("+left().toString() + " AND " + right().toString()+")";
	}
}
