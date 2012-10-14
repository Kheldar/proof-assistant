package syntax;
import java.util.ArrayList;

import naturalDeduction.Deduction;

public abstract class Formula implements Comparable<Formula> {
	//TODO: Implement 'equals()'
	
	public abstract int compareTo(Formula o);
	
	public Deduction from = null;
	public ArrayList<Deduction> possibleFroms = new ArrayList<Deduction>();
}
