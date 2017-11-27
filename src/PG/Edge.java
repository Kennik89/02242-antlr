package PG;

public class Edge {
	
	private Node from;
	private Node to;
	private String label;

	public Edge(Node from, Node to, String label)	{
		this.from = from;
		this.to = to;
		this.label = label;
	}

}
