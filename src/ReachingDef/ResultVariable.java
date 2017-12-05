package ReachingDef;

public class ResultVariable {
	public String variable;
	public String nodeID;
	
	public ResultVariable(String variable) {
		this.variable = variable;
		nodeID = "?";
	}
	
	public ResultVariable(String variable, int nodeID) {
		this.variable = variable;
		this.nodeID = Integer.toString(nodeID);
	}
	
	public String toString() {
		return "(" + variable + "," + nodeID + ")";
	}
}
