package naturalDeduction;

import java.util.ArrayList;

import syntax.And;
import syntax.Formula;
import syntax.LogicalSymbol;

public abstract class AndEs extends ForwardRule {

	protected static final Class<And> formulaClass = And.class;
	
	public AndEs(Formula formula, Formula from) {
		super(formula, fromToList(from));
	}
	
	private static ArrayList<Formula> fromToList(Formula from) {
		ArrayList<Formula> list = new ArrayList<Formula>();
		list.add(from);
		return list;
	}
	
	public final Formula from() {
		return froms.get(0);
	}
	
	public static final Class<? extends LogicalSymbol> formulaClass() {
		return formulaClass;
	}
}