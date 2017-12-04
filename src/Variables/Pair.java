package Variables;

import PG.Node;

public class Pair {
	
	private char variale;
	private String nodeLabel;

	public Pair(char variable, String nodeLabel)	{
		this.variale = variable;
		this.nodeLabel = nodeLabel;
	}
	
	public Pair(char variable)	{
		this.variale = variable;
		this.nodeLabel = "?";
	}

	public void setPairLabel(String nodeLabel)	{
		this.nodeLabel = nodeLabel;
	}
	
}
