package tools;

import java.util.HashSet;
import syntax.Formula;

public class SpecHashSet extends HashSet<Formula> {

	@Override
	public boolean contains(Object o) {
		return super.contains(o) || actuallyContains(o);
	}
	
	protected boolean actuallyContains(Object o) {
		for (Formula f : this) {
			if (f.equals(o))
				return true;

		}
		return false;
	}
}
