package PG;

import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {
	
	ArrayList<Node> nodes = new ArrayList<Node>();
	ArrayList<Edge> edges = new ArrayList<Edge>();
	Node initialNode;
	LinkedList<Node> finalNodes = new LinkedList<Node>();

	public void addNode()	{
		int count = nodes.size();
		nodes.add(new Node("q" + count));
	}	
	
	public void addEdge(Node from, Node to, String code) {
		edges.add(new Edge(from, to, code));
	}
	
	public void addInitialNode(Node node)	{
		initialNode = node;
	}
	
	public void addFinalNode(Node node)	{
		finalNodes.add(node);
	}
	
	public void graphCheck()	{
		if(!initialNode.equals(null))	{
			ArrayList<Node> unvisitedNodes = nodes;
			LinkedList<Node> postNodes = null;
			postNodes.add(initialNode);
			Node thisNode;
			
			while(!postNodes.isEmpty()) {
				thisNode = postNodes.pop();
				unvisitedNodes.remove(thisNode);
				for(Edge findEdges : edges.)	{
					
				}
			}
		}else {
			System.out.println("Missing an initial node");
		}
	}

	LinkedList<Node> getNodes(Node currentNode)	{
		currentNode.
	}
	
	
	
}
