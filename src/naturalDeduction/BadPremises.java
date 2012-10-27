package naturalDeduction;

import java.util.List;
import syntax.Formula;

public class BadPremises extends Exception {

	List<Formula> premises;
	
	public BadPremises() {
		super();
	}
	
	public BadPremises(List<Formula> premises) {
		super();
		this.premises = premises;
	}
	
}
