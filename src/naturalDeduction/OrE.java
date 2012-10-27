package naturalDeduction;

import java.util.ArrayList;
import java.util.List;

import syntax.Formula;
import syntax.Implies;
import syntax.Or;

public class OrE extends BackwardRule {
	
	protected static final Class<Formula> formulaClass = Formula.class;
	
	public OrE(Formula conclusion) {
		super(conclusion);
	}
	
	public static List<Goal> applyBackwards(Formula conclusion, Theorem t) {
		ArrayList<Goal> list = new ArrayList<Goal>();
		for(Formula k : t.getKnowledge()) {
			if(Or.class.isInstance(k)) {
				Or f = (Or) k; 
				if(!(t.inKnowledge(f.left()) || t.inKnowledge(f.right()))) {
					Goal g = new Goal();
					g.rule = OrE.class;
					g.directGoals.add(k);
					g.directGoals.add(new Implies(f.left(), conclusion));
					g.directGoals.add(new Implies(f.right(), conclusion));
					list.add(g);
				}
			}
		}
		return list;
	}
	
	public static Boolean manyGoals() {
		return true;
	}
	
	public static final Class<? extends Formula> formulaClass() {
		return formulaClass;
	}
}