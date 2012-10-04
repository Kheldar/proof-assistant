package syntax;

import java.util.ArrayList;

public abstract class LogicalSymbol extends Formula {
	protected final ArrayList<Formula> subFormulas = new ArrayList<Formula>(nary());
	
	protected abstract Integer nary();
	
}