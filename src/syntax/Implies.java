package syntax;

public class Implies extends BinaryConnective {
	
	public Implies(Formula left, Formula right) {
		super(left, right);
	}
	
	@Override
	public int classWeight() {
		return 5;
	}
	
	public String toString() {
		return "("+left().toString() + " IMPLIES " + right().toString()+")";
	}
}