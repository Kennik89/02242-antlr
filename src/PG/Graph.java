package PG;

import java.util.ArrayList;
import java.util.LinkedList;

import Variables.Pair;

public class Graph {
	
	LinkedList<Node> nodes = new LinkedList<Node>();
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
		from.addEdge(edge);
		
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
	
	public int numberOfEdges() {
		return edges.size();
	}
	
	public void setFinalNode(Node node)	{
		finalNodes.add(node);
	}
	
	public LinkedList<Node> getNodes() {
		return nodes;
	}
	
	public void graphCheck()	{
		if(!initialNode.equals(null))	{
			LinkedList<Node> unvisitedNodes = nodes;
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
	
	public Edge getEdgeBetween(Node q1, Node q2) {
		for (int i = 0; i < edges.size(); i++) {
			if(edges.get(i).from.equals(q1) && edges.get(i).to.equals(q2)){
				return edges.get(i);
			}
		}
		return null;
	}
	
	public LinkedList<Edge> getPreEdgeIn(Node node) {
		LinkedList<Edge> collection = null;
		for(Edge edge : edges) {
			if (edge.to.equals(node)){
				collection.add(edge);
			}
		}
		return collection;
	}
	
	public ArrayList<Pair> getVariableCollection()	{
		ArrayList<Pair> collection = new ArrayList<Pair>();
		LinkedList<Edge> edgesToCheck = null;
		LinkedList<Node> nonVisitedNodes = nodes;
		for(Node node : finalNodes)
			edgesToCheck.addAll(getPreEdgeIn(node));
		
		Edge thisEdge;
		while(!edgesToCheck.isEmpty())	{
			thisEdge = edgesToCheck.pop();
			if(thisEdge.getCode().matches("int(*.)"))	{
				
			}
			collection.add(new Pair(getVariable(thisEdge)));
		}
		
		
		return null;
		
	}

	private char getVariable(Edge thisEdge) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
