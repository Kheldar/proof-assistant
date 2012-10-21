package naturalDeduction;

import java.util.List;

import syntax.And;
import syntax.Formula;

public class AndE2 extends AndEs {
	
	public AndE2(List<Formula> premises) throws BadPremises {
		super(applyForward(premises), premises);
	}
	
	public static Formula applyForward(List<Formula> premises) throws BadPremises {
		if(premises.size() != 1) {
			throw new BadPremises();
		} else {
			Formula premise = premises.get(0);
			if (!premise.getClass().equals(formulaClass())){
				throw new BadPremises();
			} else {
				And a = (And) premise;
				return a.right();
			}
		}
	}
}
