package syntax;

public abstract class BinaryConnective extends Connective {
	static final Integer nary = 2;
	
	public BinaryConnective(Formula left, Formula right) {
		subFormulas.add(left);
		subFormulas.add(right);
	}
	
	public Formula left() {
		return this.subFormulas.get(0);
	}
	
	public Formula right() {
		return this.subFormulas.get(1);
	}
	
	protected Integer nary() {
		return nary;
	}
}
