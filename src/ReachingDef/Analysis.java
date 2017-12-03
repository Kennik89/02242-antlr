package ReachingDef;

import java.util.LinkedList;

import PG.*;

public class Analysis {
	
	public static void main(String[] args) {
		Graph graph = PG.pg;
		reachingDefinition(graph);
	}

	private static void reachingDefinition(Graph g)	{
		LinkedList<Node> currentNodes = new LinkedList<Node>();
		LinkedList<Node> pendingNodes = new LinkedList<Node>();
		LinkedList<Node> visitedNodes = new LinkedList<Node>();
		Node thisNode;
		
		currentNodes.addAll(g.getNodes());
		
		while(!currentNodes.isEmpty())	{
			thisNode = currentNodes.pop();
			System.out.println(thisNode.getLabel());
			visitedNodes.add(thisNode);
			
			
			//TODO analyse this node
			
			//TODO add new label in pendingNodes
			
			if(currentNodes.isEmpty())	{
				currentNodes.addAll(pendingNodes);
				pendingNodes.clear();
			}
		}
	}
	
}
