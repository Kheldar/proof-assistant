package syntax;

public abstract class Quantifier extends LogicalSymbol {
	Variable var;
	Formula f;
	
	public Quantifier(Variable var, Formula f) {
		this.var = var;
		this.f = f;
	}
}
