package naturalDeduction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;
import java.util.TreeSet;
import syntax.Formula;

public class Theorem {

	public Assumption assumption;
	protected TreeSet<Deduction> deductions = new TreeSet<Deduction>();
	public ArrayList<Proof> subproofs = new ArrayList<Proof>();
	public Stack<Formula> goals = new Stack<Formula>();
	
	public Theorem(Formula toProve) {	
		goals.add(toProve);
	}
	
	public Theorem(Assumption a) {
		this.assumption = a;
	}
	
	public void add(Deduction d) {
		deductions.add(d);
	}
	
	public void addAll(Collection<Deduction> c) {
		deductions.addAll(c);
	}
	
	public Collection<Deduction> getDeductions() {
		ArrayList<Deduction> ret = new ArrayList<Deduction>();
		ret.addAll(deductions);
		return ret;
	}
	
	public Deduction inDeductions(Formula f) {
		for(Deduction d : deductions) {
			if(d.formula.equals(f)) {
				return d;
			}
		}
		
		return null;
	}
	
	public Boolean noDeductions() {
		return deductions.isEmpty();
	}
	
	public Integer numDeductions() {
		return deductions.size();
	}
}