package naturalDeduction;

import syntax.Formula;
import syntax.Or;

public class OrI1 extends OrIs {
	
	public OrI1(Formula intro, Deduction from) {
		super(logicalConsequence(intro, from), fromToList(from));
	}
	
	public static final Formula logicalConsequence(Formula intro, Deduction from) {
		return new Or(intro, from.formula);
	}
	
	public OrI1(Formula conclusion) throws FormulaMismatch  {
		super(conclusion, applyBackwards(conclusion));
	}
	
	public static Goal applyBackwards(Formula conclusion) throws FormulaMismatch {
		if(formulaClass.isInstance(conclusion)) {
			Goal ret = new Goal();
			ret.directGoals.add(formulaClass.cast(conclusion).left());
			return ret;
		} else {
			throw new FormulaMismatch();
		}
	}
}
