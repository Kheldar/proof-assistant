package naturalDeduction;

import java.util.ArrayList;

import syntax.Formula;

public abstract class Deduction {
	public Formula formula;
	final ArrayList<Deduction> froms = new ArrayList<Deduction>();
	protected Boolean correct = false;	// Has all of its requisite 'from' fields filled in, and you can infact derive this from those.
	protected Boolean complete = false;	// All deductions in this deduction's chain are correct, and there are no loops.
		
	public Deduction(Formula formula, ArrayList<Deduction> froms) {
		this.formula = formula;
		this.froms.clear();
		this.froms.addAll(froms);
	}
	
	public Deduction(Formula formula, Deduction from) {
		this.formula = formula;
		this.froms.add(from);
	}
	
	public Deduction(Formula formula) {
		this.formula = formula;
	}
	
	public static Boolean hasCheck() {
		return false;
	}
}