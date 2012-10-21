package syntax;
import java.util.ArrayList;
import java.util.List;

import naturalDeduction.BackwardRule.Goal;
import naturalDeduction.Deduction;

public abstract class Formula implements Comparable<Formula> {
	//TODO: Implement 'equals()'
	
	public abstract int compareTo(Formula o);
	
	public Deduction from = null;
	public ArrayList<Goal> possibleGoals = new ArrayList<Goal>();
	
	public List<Deduction> deductionList() {
		List<Deduction> ds = new ArrayList<Deduction>();
		if(from != null) {
			ds.add(from);
//			System.err.println(from.getPremises());
			for(Formula premise : from.getPremises()) {
				ds.addAll(premise.deductionList());
			}
		}
		return ds;
	}
}