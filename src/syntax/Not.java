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
	public int compareTo(Formula o) {
		// TODO Auto-generated method stub
		return 5;
	}
}