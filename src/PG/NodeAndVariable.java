package PG;

import java.util.LinkedList;

import Variables.Variables;

public class NodeAndVariable {
	public Node node;
	public Variables var = new Variables();
	
	public NodeAndVariable(Node nodeNew, Variables varNew) {
		this.node = nodeNew;
		this.var = varNew;
	}
	
}
