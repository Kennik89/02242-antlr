package PG;

public class Edge {
	
	public Node from;
	public Node to;
	public String label;

	public Edge(Node from, Node to, String label)	{
		this.from = from;
		this.to = to;
		this.label = label;
	}

}
