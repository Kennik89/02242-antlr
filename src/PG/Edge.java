package PG;

class Edge {
	
<<<<<<< HEAD
	public Node from;
	public Node to;
	public String label;
=======
	private Node from;
	private Node to;
	private String code;
>>>>>>> origin/master

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

	@Override
	public String toString() {
		return super.toString() + " " + code;
	}
	
}
