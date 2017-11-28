package ReachingDef;

import java.util.LinkedList;

import PG.*;

public class Analysis {
	
	public void ReachingDefinition(Graph g)	{
		LinkedList<Edge> postEdges = g.getEdges();
		Node initNode = null;
		
		for( Edge thisEdge : g.getEdges() )	{
			if(thisEdge.getFrom() == g.getInitialNode())	{
				postEdges.add(thisEdge);
			}
		}
		
		Edge thisEdge = postEdges.pop();
		
		switch (thisEdge.getCode()) {
		case value:
			
			break;

		default:
			break;
		}
	}

}
