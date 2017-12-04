package Variables;

import PG.Node;

public class Pair {
	
	private String variable;
	private String label;

	public Pair(String variable, String label)	{
		this.variable = variable;
		this.label = label;
	}
	
	public Pair(String split)	{
		this.variable = split;
		this.label = "?";
	}

	public String getVariable() {
		return variable;
	}
	
	public void setPairLabel(String label)	{
		this.label = label;
	}
	
	@Override
	public String toString() {
		return "(" + variable + "," + label + ")";
	}
	
}
