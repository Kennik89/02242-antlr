package ReachingDef;

import java.util.LinkedList;
import PG.*;
import Variables.Pair;

public class Analysis {

	Graph graph;
	LinkedList<Pair>[] results;

	public void reachingDefinition(Graph graph, boolean print)	{
		Results analysisResults = new Results(variablecollection(graph));
		
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

	private void printState(Node thisNode) {
		System.out.print(thisNode.getLabel() + ": ");
		for (Pair pair : results[thisNode.getLabel()]) {
			System.out.print(pair.toString() + " ");
		}
		System.out.println();
	}

}
