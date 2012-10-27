package naturalDeduction;

import java.util.ArrayList;
import java.util.List;

import syntax.Formula;

public abstract class Deduction {
	public Formula consequent;
	private final ArrayList<Formula> premises = new ArrayList<Formula>();
	public Proof proofPremise;
	public Integer lineNumber;
	
	public Deduction(Formula consequent) {
		this.consequent = consequent;
	}
	
	public Deduction(Formula consequent, List<Formula> premises) {
		this(consequent);
		this.premises.addAll(premises);
	}
	
	public void addPremise(Formula f) {
		premises.add(f);
	}
	
	@SuppressWarnings("unchecked")
	public List<Formula> getPremises() {
		return (List<Formula>) premises.clone();
	}
	
	public Deduction(Formula consequent, Proof premise) {
		this(consequent);
		this.proofPremise = premise;
	}
	
	public static Boolean hasCheck() {
		return false;
	}
	
	public String toString() {
		//TODO: Include Rule;
		return consequent.toString();
	}
}