package syntax;

import java.util.ArrayList;

public abstract class LogicalSymbol extends Formula {
	public Integer nary;
	final ArrayList<Formula> subFormulas = new ArrayList<Formula>(nary);
}