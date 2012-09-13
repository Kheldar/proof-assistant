package syntax;

import java.util.Collection;
import java.util.ArrayList;

public class Predicate extends Formula {
	final Integer nary;
	
	final ArrayList<Term> terms;
	
	public Predicate(Collection<Term> terms) {
		this.nary = terms.size();
		this.terms = new ArrayList<Term>(this.nary);
		this.terms.addAll(terms);
	}
}