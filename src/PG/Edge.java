package PG;

public class Edge {
	
	public Node from;
	public Node to;
	public String code;

	public Edge(Node from, Node to, String code)	{
		this.from = from;
		this.to = to;
		this.code = code;
		from.addEdgeIn(this);
		to.addEdgeOut(this);
	}
	
	public Node getTo()	{
		return to;
	}
	
	public Node getFrom()	{
		return from;
	}
	
	public String getCode()	{
		return code;
	}

	@Override
	public String toString() {
		return  "From node: " + from.getLabel() + " " + code + " To Node: " + to.getLabel();
	}
	
}
