package syntax;

public class False extends Formula {
	final Integer nary = 0;
	
	@Override
	public int classWeight() {
		// TODO Auto-generated method stub
		return 6;
	}
	
	@Override
	public Double weight() {
		return 0.0;
	}
	
	public String toString() {
		return "FALSE";
	}
	
	public boolean equals(Object o) {
		if(o.getClass().equals(this.getClass()))
			return true;
		return false;
	}
}
