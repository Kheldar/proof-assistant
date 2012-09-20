package naturalDeduction;

import java.util.ArrayList;

import syntax.Formula;

public abstract class Deduction {
	Formula formula;
	final ArrayList<Deduction> froms;
		
	public Deduction(Formula formula, ArrayList<Deduction> froms) {
		this.formula = formula;
		this.froms = froms;
	}
}