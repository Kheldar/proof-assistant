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
		StringBuffer s = new StringBuffer();
		s.append(this.getClass().getSimpleName());
		if(!premises.isEmpty()) {
			s.append("(");
			Boolean b = false;
			for(Formula p : premises) {
				if(b)
					s.append(", ");
				s.append(p.getFrom().lineNumber);
				b = true;
			}
			s.append(")");
		} else if (proofPremise != null) {
			s.append("(");
			s.append(proofPremise.assumption.lineNumber.toString() 
					+ "-" 
					+ proofPremise.endGoal.getFrom().lineNumber);
			s.append(")");
		}
		
		return s.toString();
	}
}