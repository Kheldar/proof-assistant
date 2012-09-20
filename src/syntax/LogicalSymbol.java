package syntax;

import java.util.ArrayList;

public abstract class LogicalSymbol extends Formula {
	public Integer nary;
	protected final ArrayList<Formula> subFormulas = new ArrayList<Formula>(nary);
}