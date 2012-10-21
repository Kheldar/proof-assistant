package naturalDeduction;

import java.util.List;
import java.util.Collection;
import syntax.Implies;
import syntax.Formula;
import syntax.LogicalSymbol;

public class ImpliesE extends ForwardRule {
	
	protected static final Class<Implies> formulaClass = Implies.class;
	
	public ImpliesE(List<Formula> premises) throws BadPremises {
		super(applyForward(premises), premises);
	}
	
	public static Formula applyForward(List<Formula> premises) throws BadPremises {
		if(premises.size() != 2) {
			throw new BadPremises();
		} else {
			Formula a = premises.get(0);
			Formula b = premises.get(1);
			Implies pIq;
			Formula p;
			
			if(a.getClass().equals(Implies.class)) {
				pIq = (Implies) a;
				p = b;
			} else if(b.getClass().equals(Implies.class)) {
				pIq = (Implies) b;
				p = a;
			} else {
				throw new BadPremises();
			}
			
			if(pIq.left().equals(p)) {
				return pIq.right();
			} else {
				throw new BadPremises();
			}
		}
	}
	
	public static Boolean hasCheck() {
		return true;
	}
	
	//Need to make sure we have p;
	public static final Formula check(Collection<Formula> proofs, Formula lookingFor) {
		Implies temp = (Implies) lookingFor;
		if(proofs.contains(temp.left()))
			return temp.left();
		else
			return null;
	}
	
	public static final Class<? extends LogicalSymbol> formulaClass() {
		return formulaClass;
	}
}
