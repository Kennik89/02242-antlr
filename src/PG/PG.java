package PG;

import java.util.LinkedList;

import javax.script.ScriptException;

public class PG {



	public static void main(String args[] ) throws NumberFormatException, ScriptException {
		Graph pg = new Graph();
//		//If-loop example
//		Node q0 = pg.addInitialNode(); // q0
//		Node q1 = pg.addNode(); 
//		Node q2 = pg.addNode();
//		Node q3 = pg.addNode(); 
//		Node q4 = pg.addNode();
//		Node q5 = pg.addNode(); 
//		Node q6 = pg.addNode(); 
//		Node q7 = pg.addFinalNode();
//
//		pg.addEdge(q0, q1, "int x");
//		pg.addEdge(q1, q2, "int y");
//		pg.addEdge(q2, q3, "x := 5 + 5");
//		pg.addEdge(q3, q4, "y := 3");
//		pg.addEdge(q4, q5, "x == y");	//if skal altid have et else
//		pg.addEdge(q5, q6, "y := 200");
//		pg.addEdge(q4, q6, "y != x");	// else
//		pg.addEdge(q6, q7, "x := 20");

		//while-loop example
		Node q0 = pg.addInitialNode(); // q0
		Node q1 = pg.addNode(); 
		Node q2 = pg.addNode();
		Node q3 = pg.addNode(); 
		Node q4 = pg.addFinalNode();
		
		pg.addEdge(q0, q1, "int x");
		pg.addEdge(q1, q2, "x := 2*5");  
		pg.addEdge(q2, q3, "3 < x");		//while laves som et if loop og skal altid stadig have else
		pg.addEdge(q3, q2, "x := x - 1");
		pg.addEdge(q2, q4, "x <= 3");	//else

		GraphTraversal gt = new GraphTraversal();

		LinkedList<NodeAndVariable> route = gt.graphWalker(pg); 
		//inderholder sekvensen af noder der også bliver print og pr node er der en liste over alle variables værdier
		//så man kan se hvordan variablerne ændres udervejs.


	}
}
