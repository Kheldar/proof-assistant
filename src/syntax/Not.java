package syntax;

public class Not extends Connective {
	static final Integer nary = 1;
	
	public Not(Formula sub) {
		subFormulas.add(sub);
	}
	
	public Formula subFormula() {
		return subFormulas.get(0);
	}
	
	protected Integer nary() {
		return nary;
	}
	
	@Override
	public int classWeight() {
		return 3;
	}
	
	public Double weight() {
		return 1.0 + subFormula().weight();
	}
	
	public String toString() {
		return "(NOT " + subFormula().toString() + ")";
	}
	
	public boolean equals(Object o) {
		if(super.equals(o))
			return true;
		else if(this.getClass().equals(o.getClass())) {
			Not n = (Not) o;
			return subFormula().equals(n.subFormula());
		}
		return false;
	}
}