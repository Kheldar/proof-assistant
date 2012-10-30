package naturalDeduction;

import java.util.ArrayList;
import java.util.List;

import syntax.False;
import syntax.Formula;
import syntax.Implies;
import syntax.Not;

public class NotE extends BackwardRule {
	
	protected static final Class<False> formulaClass = False.class;
	
	public NotE(Formula conclucsion) throws BadPremises  {
		super(conclucsion);
	}
	
	public static Boolean manyGoals() {
		return true;
	}
	
	public static List<Goal> applyBackwards(Formula conclusion, Theorem t) {
		ArrayList<Goal> list = new ArrayList<Goal>();
		if(!conclusion.getClass().equals(Implies.class)) {
			for(Formula f : t.getKnowledge()) {
				if(f.getClass().equals(Not.class)) {
					Not n = (Not) f;
					Goal g = new Goal();
					g.rule = NotE.class;
					g.directGoals.add(n.subFormula());
					list.add(g);
				}
			}
		}
		
		return list;
	}
	
	public static final Class<? extends Formula> formulaClass() {
		return formulaClass;
	}
}