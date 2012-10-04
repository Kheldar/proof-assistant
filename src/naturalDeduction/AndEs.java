package naturalDeduction;

import java.util.ArrayList;

import syntax.And;
import syntax.Formula;
import syntax.LogicalSymbol;

public abstract class AndEs extends ForwardRule {

	protected static final Class<And> formulaClass = And.class;
	
	public AndEs(Formula formula, Deduction from) {
		super(formula, fromToList(from));
	}
	
	private static ArrayList<Deduction> fromToList(Deduction from) {
		ArrayList<Deduction> list = new ArrayList<Deduction>();
		list.add(from);
		return list;
	}
	
	public final Deduction from() {
		return froms.get(0);
	}
	
	public static final Class<? extends LogicalSymbol> formulaClass() {
		return formulaClass;
	}
}