package PG;

public class Edge {
	
	public Node from;
	public Node to;
	public String code;

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
