package naturalDeduction;

import java.util.ArrayList;
import java.util.Collection;

import syntax.Formula;


public class Proof extends Theorem {
	private Theorem parent;
		
	public Proof(Formula toProve, Assumption a, Theorem parent) {
		super(toProve, a);
		this.parent = parent;
		parent.subproofs.add(this);
	}
	
	public Collection<Deduction> getDeductions() {
		ArrayList<Deduction> ret = new ArrayList<Deduction>();
		ret.addAll(deductions);
		ret.addAll(parent.getDeductions());
		return ret;
	}
	
	public Theorem getParent() {
		return parent;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Formula> getKnowledge() {
		Collection<Formula> c = (Collection<Formula>) known.clone();
		c.addAll(parent.getKnowledge());
		return c;
	}
}