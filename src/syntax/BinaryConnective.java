package syntax;

public abstract class BinaryConnective extends Connective {
	static final Integer nary = 2;
	
	public BinaryConnective(Formula left, Formula right) {
		subFormulas.add(left);
		subFormulas.add(right);
	}
	
	public Double weight() {
		return 1.0 + left().weight() + right().weight();
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
	
	public boolean equals(Object o) {		
		if(super.equals(o)) {
			return true;
		} else if(this.getClass().equals(o.getClass())) {
			BinaryConnective c = (BinaryConnective) o;
			return c.left().equals(left()) && c.right().equals(right());
		}
		return false;
	}
}
