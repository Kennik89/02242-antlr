package ReachingDef;

import java.util.LinkedList;

import PG.*;
import Variables.Pair;

public class Analysis {

	public void reachingDefinition(Graph graph)	{
		System.out.println("--- RD Analysis initialised ---");
		LinkedList<Edge> currentEdges = new LinkedList<Edge>();
		LinkedList<Edge> pendingEdges = new LinkedList<Edge>();
		LinkedList<Pair> results = new LinkedList<Pair>();
		
		results = graph.getVariableCollection();
		
		System.out.println("STATE:");
		Node thisNode = graph.getInitialNode();
		currentEdges.addAll(graph.getedges());
		
		while(!(currentEdges.isEmpty()))	{
			/* Update the working list and print the initial result*/
			thisNode = currentEdges.pop();
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
