package syntax;

public class Not extends Connective {
	static final Integer nary = 1;
	
	public Not(Formula sub) {
		subFormulas.add(sub);
	}
}