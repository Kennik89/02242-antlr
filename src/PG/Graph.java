package PG;

import java.util.LinkedList;

import Variables.Pair;

public class Graph {

	LinkedList<Node> nodes = new LinkedList<Node>();
	LinkedList<Edge> edges = new LinkedList<Edge>();

	Node initialNode;
	LinkedList<Node> finalNodes = new LinkedList<Node>();

	public Node addNode()	{
		int count = nodes.size();
		Node node = new Node(count);
		nodes.add(node);

		return node;
	}	

	public Node addInitialNode()	{
		Node node = addNode();
//		nodes.add(node);
		initialNode = node;

		return node;
	}	

	public Node addFinalNode()	{
		Node node = addNode();
//		nodes.add(node);
		finalNodes.add(node);

		return node;
	}	

	public Edge addEdge(Node from, Node to, String code)	{
		Edge edge = new Edge(from, to, code);
		edges.add(edge);

		return edge;

	}
/*
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
	}*/

	public Node getInitialNode() {
		return initialNode;
	}
	
	public void setInitialNode(Node node)	{
		initialNode = node;
	}

	public void setInitialNode(String label)	{
		for(Node node : nodes)
			if(node.getLabel() == Integer.parseInt(label))	{
				initialNode = node;
				return;
			}
	}

	public LinkedList<Node> getFinalNodes()	{
		return finalNodes;
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
	
	public LinkedList<Edge> getEdges() {
		return edges;
	}

	public void graphCheck()	{
		if(!(initialNode.equals(null)))	{
			LinkedList<Node> unvisitedNodes = nodes;
			LinkedList<Edge> unvisitedEdges = edges; 
			LinkedList<Node> postNodes = new LinkedList<Node>();
			postNodes.add(initialNode);
			Node thisNode;

			while(!postNodes.isEmpty()) {
				thisNode = postNodes.pop();
				unvisitedNodes.remove(thisNode);
				for(Edge edge : edges)	{
					if(unvisitedNodes.contains(edge.getTo()) & !postNodes.contains(edge.getTo()))	{
						postNodes.add(edge.getTo());
					}
				}
			}
			System.out.println("Unvisited nodes: " + unvisitedNodes.size());
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

	public LinkedList<Edge> getPostEdgeIn(Node node) {
		LinkedList<Edge> collection = new LinkedList<Edge>();
		for(Edge edge : edges) {
			if (edge.from.equals(node)){
				collection.add(edge);
			}
		}
		return collection;
	}

	public LinkedList<Pair> getVariableCollection()	{
		LinkedList<Pair> collection = new LinkedList<Pair>();
		LinkedList<Pair> UnsortedCollection = new LinkedList<Pair>();

		for (Edge edge : edges) {
			UnsortedCollection.addAll(getPairs(edge));
		}

		for (Pair thisVar : UnsortedCollection) {
			if(newVariableIn(thisVar.getVariable(), collection))	{
				System.out.println("Var added" + thisVar.toString());
				collection.add(thisVar);
			}
		}

		return collection;

	}

	public LinkedList<Pair> getPairs(Edge thisEdge) {
		LinkedList<Pair> collection = new LinkedList<Pair>();

		if(thisEdge.getCode().matches("int(.*)]"))	{
			String[] split = thisEdge.getCode().split(" ");
			collection.add(new Pair(split[1].substring(0, split[1].length()-2)+"]")); // extract A[]
			
		}else if(thisEdge.getCode().matches("int(.*)")) {
			String[] split = thisEdge.getCode().split(" ");
			collection.add(new Pair(split[1]));	// extract x

			
		}else if(thisEdge.getCode().matches("(.*):=(.*)")) {
			String[] split = thisEdge.getCode().split(" := ");
			collection.add(new Pair(split[0])); // extract left side

			String[] subsplit = split[1].split("[+-/&|\\*]");
			for (String string : subsplit) {
				collection.add(new Pair(string.replaceAll("\\s+",""))); // extract right side for all variables
			}
		}
		return collection;
	}

	private boolean newVariableIn(String var, LinkedList<Pair> collection)	{
		if(isInteger(var))	{
			return false;
		}
		for (Pair thisPair : collection) {
			if(thisPair.getVariable().equals(var))	{
				return false;
			}
		}

		return true;

	}

	private boolean isInteger(String var) {
		try {
			Integer.parseInt(var); 
			return true;
		}catch(NumberFormatException  e) {
			return false;
		}
	}

	public void printGraph() {
		for (Node node : nodes) {
			System.out.println(node.toString());
		}
		for (Edge edge : edges) {
			System.out.println(edge.toString());
		}

	}
	
}
