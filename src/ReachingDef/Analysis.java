package ReachingDef;

import java.util.LinkedList;

import PG.*;
import Variables.Pair;

public class Analysis {

	public void reachingDefinition(Graph graph)	{
		System.out.println("--- RD Analysis initialised ---");
		LinkedList<Node> currentNodes = new LinkedList<Node>();
		LinkedList<Node> pendingNodes = new LinkedList<Node>();
		LinkedList<Pair> results = new LinkedList<Pair>();
		
		results = graph.getVariableCollection();
		for (Node node : graph.getNodes()) {
			System.out.println("TEST: " + node.getLabel());
		}
		
		System.out.println("STATE:");
		Node thisNode;
		currentNodes.addAll(graph.getNodes());
		
		while(!(currentNodes.isEmpty()))	{
			/* Update the working list and print the initial result*/
			thisNode = currentNodes.pop();
			System.out.print(thisNode.getLabel() + ": ");
			for (Pair pair : results) {
				System.out.print(pair.toString() + " ");
			}
			System.out.println();
			
			//TODO analysis this node
			
			
			//TODO add new label in pendingNodes
			
			if(currentNodes.isEmpty())	{
				currentNodes.addAll(pendingNodes);
				pendingNodes.clear();
			}
		}
	}
	
}
