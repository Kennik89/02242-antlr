package PG;

import java.util.LinkedList;

class Node {
	
	private String label;
	private LinkedList<Edge> from;
	private LinkedList<Edge> to;

	public Node(String label)	{
		this.label = label;
	}

	public void addFrom(Node node)	{
		
	}
	
	public LinkedList<Node> getPreNodes(){
		LinkedList<Node> tempNodes;
		LinkedList<Edge> edges = from;
		
		
		return null;
	}
	
}
