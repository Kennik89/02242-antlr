package ReachingDef;

import java.util.LinkedList;

import PG.*;

public class Analysis {

	public void reachingDefinition(Graph graph, boolean print)	{
		LinkedList<Node> currentNodes = graph.getNodes();
		LinkedList<Node> pendingNodes = new LinkedList<>();
		Results results = new Results(variablecollection(graph));
		Node thisNode = currentNodes.pop();
		System.out.print(thisNode.toString() + ": "); results.print();
		
		while (!currentNodes.isEmpty()) {
			thisNode = currentNodes.pop();
			
			
		}
		
		
	}


	private Results nodeAnalyse(Node node, Results results) {
		
		if(node.hasTwoEdges())	{
			Results temp1, temp2;
			temp1 = killGen(node.getEdges()[0], results);
			temp2 = killGen(node.getEdges()[1], results);
			return union(temp1, temp2);
		}else{
			return killGen(node.getEdges()[0], results);
		}
			
	}
	
	
	
	private Results union(Results r1, Results r2) {
		Results temp = r1;
		return temp.uniqueAdd(r2);
	}


	private Results killGen(Edge edge, Results results) {
		return results.killGen(getLeftside(edge));
	}


	private LinkedList<String> variablecollection(Graph graph) {
		LinkedList<String> allVariables = new LinkedList<String>();
		for (Edge edge : graph.getEdges()) {
			allVariables.add(getLeftside(edge));
		}
		
		return allVariables;
	}
	
	private String getLeftside(Edge edge) {
		String code = edge.getCode();

		if(code.matches("int(.*)]"))	{
			String[] split = code.split(" ");
			return split[1].substring(0, split[1].length()-3); // extract A

		}else if(code.matches("int(.*)")) {
			String[] split = code.split(" ");
			return split[1];	// extract x

		}else if(code.matches("(.*)] :=(.*)")) {
			String[] split = code.split("[");
			return split[0]; // extract left side of A

		}else if(code.matches("(.*) :=(.*)")) {
			String[] split = code.split(" := ");
			return split[0]; // extract left side of x
		}else {
			return null;
		}
	}

}
