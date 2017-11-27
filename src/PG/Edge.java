package PG;

import java.util.LinkedList;

class Edge {
	
	private Node from;
	private Node to;
	private String code;

	public Edge(Node from, Node to, String code)	{
		this.from = from;
		this.to = to;
		this.code = code;
	}

}
