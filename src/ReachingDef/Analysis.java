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
		
		String thisCode = postEdges.pop().getCode();
		
		if(thisCode.matches("int (.*)"))	{	// int x;
			
		}else if(thisCode.matches("int (.*)[(.*)]"))	{	// int A[n];
			
		}else if(thisCode.matches(""))	{	// Epsilon;
			
		}else if(thisCode.matches("true"))	{	// true
			
		}else if(thisCode.matches("false"))	{	// false
			
		}else if(thisCode.matches("skip"))	{	// skip;
			
		}else if(thisCode.matches("(.*) + (.*)"))	{	// a1 + a2
			
		}else if(thisCode.matches("(.*) - (.*)"))	{	// a1 - a2
			
		}else if(thisCode.matches("(.*) * (.*)"))	{	// a1 * a2
			
		}else if(thisCode.matches("(.*) / (.*)"))	{	// a1 / a2
			
		}else if(thisCode.matches("(.*) < (.*)"))	{	// b1 < b2
			
		}else if(thisCode.matches("(.*) > (.*)"))	{	// b1 > b2
			
		}else if(thisCode.matches("(.*) <= (.*)"))	{	// b1 <= b2
			
		}else if(thisCode.matches("(.*) >= (.*)"))	{	// b1 >= b2
			
		}else if(thisCode.matches("(.*) == (.*)"))	{	// b1 == b2
			
		}else if(thisCode.matches("(.*) != (.*)"))	{	// b1 != b2
			
		}else if(thisCode.matches("(.*) & (.*)"))	{	// b1 & b2
			
		}else if(thisCode.matches("(.*) | (.*)"))	{	// b1 | b2
			
		}else if(thisCode.matches("(.*) := (.*)"))	{	// x := a;
			
		}else if(thisCode.matches("(.*)] := (.*)"))	{	// A[a1] := a2;
			
		}else if(thisCode.matches("break"))	{	// break;
			
		}else if(thisCode.matches("continue"))	{	// continue;
			
		}else if(thisCode.matches("read (.*)"))	{	// read x;
			
		}else if(thisCode.matches("read (.*)[(.*)]"))	{	// read A[a];
			
		}else if(thisCode.matches("write (.*)"))	{	// read A[a];
			
		}else {
			System.out.println("ERROR - No string matches");
		}
	}

}
