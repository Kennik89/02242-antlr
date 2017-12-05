package ReachingDef;

import java.util.LinkedList;
import PG.*;

public class Analysis {

	public void reachingDefinition(Graph graph, boolean print)	{
		LinkedList<Node> currentNodes = graph.getNodes();
		LinkedList<Node> pendingNodes = new LinkedList<>();
		Results analysisResults = new Results(variablecollection(graph));
		Node thisNode = currentNodes.pop();
		System.out.print(thisNode.toString() + ": "); analysisResults.print();
		
		while (!currentNodes.isEmpty()) {
			thisNode = currentNodes.pop();
			
			
		}
		
		
	}


	private void nodeAnalyse(Node node) {
		// TODO Auto-generated method stub
		// Kill each var in each edge and generate new var
		// Union all input edges
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
