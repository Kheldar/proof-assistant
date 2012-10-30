package naturalDeduction;

import syntax.Formula;
import tools.SpecHashSet;


public class Proof extends Theorem {
	private Theorem parent;
		
	public Proof(Formula toProve, Assumption a, Theorem parent) {
		super(toProve, a);
		this.parent = parent;
	}
	
	public Theorem getParent() {
		return parent;
	}
	
	public String toString() {
		String s = "";
		for(int i = 1; i <= depth(); i++) {
			s = s + "\t";
		}
		s = s+ known.toString() + "\n";
		if(!subproofs.isEmpty()) {
			for(Proof p : subproofs) {
				s = s+p.toString();
			}
		}
		return s;
	}
	
	protected String context() {
		return parent.context() + " --> " + assumption.toString();
	}
	
	protected Integer depth() {
		return 1 + parent.depth();
	}
	
	@SuppressWarnings("unchecked")
	public SpecHashSet getKnowledge() {
		SpecHashSet c = (SpecHashSet) known.clone();
		c.addAll(parent.getKnowledge());
		return c;
	}
}