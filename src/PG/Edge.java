package PG;

public class Edge {
	
	private Node from;
	private Node to;
	private String code;

	public Edge(Node from, Node to, String code)	{
		this.from = from;
		this.to = to;
		this.code = code;
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
		return super.toString() + " " + code;
	}
	
}
