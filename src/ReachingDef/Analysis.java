package ReachingDef;

import java.util.LinkedList;

import PG.*;

public class Analysis {

	public void reachingDefinition(Graph graph)	{
		System.out.println("Reaching Definition analysis:");
		
		LinkedList<Node> currentNodes = graph.getNodes();
		LinkedList<Node> pendingNodes = new LinkedList<>();
		boolean flag = true;
		Results results = new Results(variablecollection(graph));
		
		Node thisNode = currentNodes.pop();
		System.out.print(thisNode.toString() + ": "); results.print();

		while (!currentNodes.isEmpty()) {
			thisNode = currentNodes.pop();
			if(!graph.getFinalNodes().contains(thisNode))	{
				results = nodeAnalyse(thisNode, results);
				
//				if(thisNode.hasSplit() & !pendingNodes.contains(thisNode)) {
//					pendingNodes
//				}
			}
			System.out.print(thisNode.toString() + ": "); results.print();


			if(currentNodes.isEmpty() & flag) {
				currentNodes = pendingNodes;
				pendingNodes.clear();
				flag = false;
			}
		}
		System.out.println("-RESULT:-");
		results.print();
	}


	private Results nodeAnalyse(Node node, Results results) {
		if(node.hasTwoEdges())	{
			Results temp1, temp2;
			temp1 = killGen(node.getEdges()[0], node.getLabel(), results);
			temp2 = killGen(node.getEdges()[1], node.getLabel(), results);
			return union(temp1, temp2);
		}else{
			return killGen(node.getPreEdges()[0], node.getLabel(), results);
		}	
	}

	private Results union(Results r1, Results r2) {
		Results temp = r1;
		return temp.uniqueAdd(r2);
	}


	private Results killGen(Edge edge, int label, Results results) {
		return results.killGen(getLeftside(edge), label);
	}


	private LinkedList<String> variablecollection(Graph graph) {
		LinkedList<String> allVariables = new LinkedList<String>();
		String input;
		for (Edge edge : graph.getEdges()) {
			input = getLeftside(edge);
			if (input != null & !allVariables.contains(input)) {
				allVariables.add(input);
			}
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
		}else if(code.matches("write (.*)")){
			String[] split = code.split(" ");
			return split[1]; // extract right side of write
		}else {
			return null;
		}
	}

}
