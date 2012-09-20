package syntax;

public class BoundVariable extends Variable {
	Quantifier scope;
	
	public BoundVariable(Integer id, Quantifier scope) {
		super(id);
		this.scope = scope;
	}
}