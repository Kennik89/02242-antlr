package PG;

import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {
	
	ArrayList<Node> nodes = new ArrayList<Node>();
	LinkedList<Edge> edges = new LinkedList<Edge>();
	Node initialNode;
	LinkedList<Node> finalNodes = new LinkedList<Node>();

	public Node addNode()	{
		int count = nodes.size();
		Node node = new Node("q" + count);
		nodes.add(node);
		
		return node;
	}	
	
	public Node addInitialNode()	{
		Node node = addNode();
		initialNode = node;
		
		return node;
	}	
	
	public Node addFinalNode()	{
		Node node = addNode();
		finalNodes.add(node);
		
		return node;
	}	

	public Edge addEdge(Node from, Node to, String code)	{
		Edge edge = new Edge(from, to, code);
		edges.add(edge);
		return edge;
		
	}
	
	public Edge addEdge(String _from, String _to, String code)	{
		Node from = null;
		Node to = null;
		for(Node node : nodes) {
			if(node.getLabel().equals(_from))
				from = node;
			else if(node.getLabel().equals(_to))
				to = node;
			if(from != null & to != null)
				break;
		}
		return addEdge(from, to, code);
	}
	
	public void setInitialNode(Node node)	{
		initialNode = node;
	}

	public void setInitialNode(String label)	{
		for(Node node : nodes)
			if(node.getLabel().equals(label))	{
				initialNode = node;
				return;
			}
	}
	
	
	
	public void setFinalNode(Node node)	{
		finalNodes.add(node);
	}
	
	public ArrayList<Node> getNodes() {
		return nodes;
	}
	
	public LinkedList<Edge> getEdges() {
		return edges;
	}
	
	public Node getInitialNode()	{
		return initialNode;
	}
	
	public LinkedList<Node> getFinalNodes()	{
		return finalNodes;
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
				for(Edge edge : edges)	{
					if(unvisitedNodes.contains(edge.getTo()) & !postNodes.contains(edge.getTo()))	{
						postNodes.add(edge.getTo());
						System.out.println(thisNode.toString() + " " + edge.toString());
					}
						
				}
			}
		}else {
			System.out.println("Missing an initial node");
		}
	}	
	
}
