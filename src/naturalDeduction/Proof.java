package naturalDeduction;

import java.util.Collection;

import syntax.Formula;


public class Proof extends Theorem {
	private Theorem parent;
		
	public Proof(Formula toProve, Assumption a, Theorem parent) {
		super(toProve, a);
		this.parent = parent;
		parent.subproofs.add(this);
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
	
	protected Integer depth() {
		return 1 + parent.depth();
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Formula> getKnowledge() {
		Collection<Formula> c = (Collection<Formula>) known.clone();
		c.addAll(parent.getKnowledge());
		return c;
	}
}