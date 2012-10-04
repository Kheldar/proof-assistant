package naturalDeduction;

import java.util.ArrayList;
import java.util.Collection;


public class Proof extends Theorem {
	private Theorem parent;
		
	public Proof(Assumption a, Theorem parent) {
		super(a);
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
}