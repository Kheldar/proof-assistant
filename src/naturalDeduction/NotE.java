package naturalDeduction;

import java.util.Collection;
import java.util.List;

import syntax.False;
import syntax.Formula;
import syntax.LogicalSymbol;
import syntax.Not;

public class NotE extends ForwardRule {
	
	protected static final Class<Not> formulaClass = Not.class;
	
	public NotE(List<Formula> premises) throws BadPremises  {
		super(applyForward(premises), premises);
	}
	
	public static Boolean hasCheck() {
		return true;
	}
	
	public static final Formula check(Collection<Formula> proofs, Formula toCheck) {
		Not n = (Not) toCheck;
		if(proofs.contains(n.subFormula()))
			return n.subFormula();
		return null;
	}
	
	public static Formula applyForward(List<Formula> premises) throws BadPremises {
		if(premises.size() != 2) {
			throw new BadPremises();
		} else {
			Formula p1 = premises.get(0);
			Formula p2 = premises.get(1);
			Not not;
			Formula norm;
			if (p1.getClass().equals(Not.class)) {
				not = (Not) p1;
				norm = p2;
			} else if(p2.getClass().equals(Not.class)) {
				not = (Not) p2;
				norm = p1;
			} else
				throw new BadPremises();
			
			if(not.subFormula().equals(norm))
				return new False();
			else
				throw new BadPremises();
		}
	}
	
	public static final Class<? extends LogicalSymbol> formulaClass() {
		return formulaClass;
	}
}