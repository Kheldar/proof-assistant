package syntax;

import java.util.ArrayList;
import java.util.Collection;

public class Function extends Term {
	final Integer nary;
	
	final ArrayList<Term> terms;
	
	public Function(Collection<Term> terms) {
		this.nary = terms.size();
		this.terms = new ArrayList<Term>(this.nary);
		this.terms.addAll(terms);
	}
}
