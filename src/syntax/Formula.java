package syntax;
import java.util.ArrayList;
import java.util.List;

import naturalDeduction.BackwardRule.Goal;
import naturalDeduction.Deduction;

public abstract class Formula implements Comparable<Formula>, Cloneable {
	
	protected Deduction from = null;
	public ArrayList<Goal> possibleGoals = new ArrayList<Goal>();
	
	public void setFrom(Deduction from) {
		this.from = from;
	}
	
	public Deduction getFrom() {
		return from;
	}
	
	public List<Deduction> deductionList() {
		List<Deduction> ds = new ArrayList<Deduction>();
		if(from != null) {
			ds.add(from);
			for(Formula premise : from.getPremises()) {
				ds.addAll(premise.deductionList());
			}
		}
		return ds;
	}
	
	public int compareTo(Formula o) {
		if(this.classWeight() - o.classWeight() != 0)
			return this.classWeight() - o.classWeight();
		else {
			Double complexity = this.weight() - o.weight(); 
			return complexity.intValue();
		}
	}
	
	public abstract Double weight();
	
	public abstract int classWeight();
	
	public Object clone() throws CloneNotSupportedException {
		Formula f = (Formula) super.clone();
		f.possibleGoals = new ArrayList<Goal>();
		f.from = null;
		return f;
	}
}