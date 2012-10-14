package syntax;

import java.util.Collection;
import java.util.ArrayList;

public class Predicate extends Formula {
	final Integer nary;
	
	final ArrayList<Term> terms;
	
	String id = "";
	Integer idNo;
	
	static Integer classIDs = 0;
	{
		idNo = classIDs;
		classIDs = classIDs + 1;
	}
	
	public Predicate(Collection<Term> terms) {
		this.nary = terms.size();
		this.terms = new ArrayList<Term>(this.nary);
		this.terms.addAll(terms);
	}
	
	public Predicate() {
		this.nary = 0;
		this.terms = null;
	}
	
	public Predicate(String id) {
		this();
		this.id = id;
	}
	
	public String toString() {
		if(!id.equals("")) {
			return id;
		} else {
			return "X"+idNo.toString();
		}
	}
	
	@Override
	public int compareTo(Formula o) {
		// TODO Auto-generated method stub
		return 3;
	}
}