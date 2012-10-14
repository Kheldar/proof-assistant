package naturalDeduction;

import java.util.ArrayList;

import syntax.Formula;

public abstract class Deduction {
	public Formula formula;
	public final ArrayList<Formula> froms = new ArrayList<Formula>();
	public final ArrayList<Proof> fromProofs = new ArrayList<Proof>();
	protected Boolean correct = false;	// Has all of its requisite 'from' fields filled in, and you can infact derive this from those.
	protected Boolean complete = false;	// All deductions in this deduction's chain are correct, and there are no loops.
		
	public Deduction(Formula formula, ArrayList<Formula> froms) {
		this(formula);
		this.froms.clear();
		this.froms.addAll(froms);
	}
	
	public Deduction(Formula formula, Formula from) {
		this(formula);
		this.froms.add(from);
	}
	
	public Deduction(Formula formula) {
		this.formula = formula;
	}
	
	public Deduction(Formula formula, Proof p) {
		this(formula);
		fromProofs.add(p);
	}
	
	public static Boolean hasCheck() {
		return false;
	}
}