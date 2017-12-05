package ReachingDef;

public class ResultVariable {
	public String variable;
	public String nodeID;
	
	public ResultVariable(String variable) {
		this.variable = variable;
		nodeID = "?";
	}
	
	public String print() {
		return "(" + variable + "," + nodeID + ")";
	}
}
