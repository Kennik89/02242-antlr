package Variables;

import PG.Node;

public class Pair {
	
	private String variable;
	private String nodeLabel;

	public Pair(String variable, String nodeLabel)	{
		this.variable = variable;
		this.nodeLabel = nodeLabel;
	}
	
	public Pair(String split)	{
		this.variable = split;
		this.nodeLabel = "?";
	}

	public String getVariable() {
		return variable;
	}
	
	public void setPairLabel(String nodeLabel)	{
		this.nodeLabel = nodeLabel;
	}
	
	@Override
	public String toString() {
		return "(" + variable + "," + nodeLabel + ")";
	}
	
}
