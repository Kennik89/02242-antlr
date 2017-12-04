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
		System.out.println("STATE: qs");
		for (Pair pair : results) {
			System.out.println(pair.toString());
		}
		Node thisNode;
		
		
		
		currentNodes.addAll(graph.getNodes());
		
		
		while(!currentNodes.isEmpty())	{
			/* Update the working list */
			thisNode = currentNodes.pop();
			System.out.println(thisNode.getLabel());
			
			
			
			//TODO analysis this node
			
			//TODO add new label in pendingNodes
			
			if(currentNodes.isEmpty())	{
				currentNodes.addAll(pendingNodes);
				pendingNodes.clear();
			}
		}
	}
	
}
