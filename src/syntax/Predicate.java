package syntax;

public class Predicate extends Formula {
	final Integer nary = 0;
	
	public String id = "";
	public Integer idNo;
	
	public Double weight(){
		return 0.0;
	}
	
	static Integer classIDs = 0;
	
	public Predicate(String id) {
		this();
		this.id = id;
	}
	
	public Predicate() {
		idNo = classIDs;
		classIDs = classIDs + 1;
	}
	
	public boolean equals(Object o) {
		if(o.getClass().equals(Predicate.class)) {
			Predicate p = (Predicate) o;
			return this.idNo == p.idNo || this.id.equals(p.id);
		}
		
		return false;
		
	}
	
	public String toString() {
		if(!id.equals("")) {
			return id;
		} else {
			return "X"+idNo.toString();
		}
	}
	
	@Override
	public int classWeight() {
		// TODO Auto-generated method stub
		return 4;
	}
}