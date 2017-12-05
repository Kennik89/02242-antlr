package PG;

import java.util.LinkedList;

import javax.script.ScriptException;

import DetectionOfSigns.DOSAnalysis;
import DetectionOfSigns.InitVariable;
import ReachingDef.Analysis;

public class PG {
	public static Graph pg = new Graph();
	public static LinkedList<InitVariable> initialVariables = new LinkedList<InitVariable> ();
	
			
	public static void main(String args[] ) throws NumberFormatException, ScriptException {
		
		/* v�lg kun en nedenst�ende methode, resten skal udkommenteres */
		//test1();
		test2();
		//test2();
		//test3();
		//graph1();
		//graph2();

		//pg.graphCheck();
		//pg.printGraph();

//		GraphTraversal gt = new GraphTraversal();
//		LinkedList<NodeAndVariable> route = gt.graphWalker(pg); 


		DOSAnalysis DOS = new DOSAnalysis();
		DOS.workListAlgorith(pg,initialVariables);
		
//		Analysis RD = new Analysis();
//		RD.reachingDefinition(pg);

	}


	private static void test1() {
		Node q0 = pg.addInitialNode();
		Node q1 = pg.addNode();
		Node q2 = pg.addNode();
		Node q3 = pg.addFinalNode();
		
		pg.addEdge(q0, q1, "int x");
		pg.addEdge(q1, q2, "int A[n]");
		pg.addEdge(q2, q3, "z := x + 1");
		
	}
	
	private static void test2() {
		Node q0 = pg.addInitialNode();
		Node q1 = pg.addNode();
		Node q2 = pg.addNode();
		Node q3 = pg.addNode();
		Node q4 = pg.addNode();
		Node q5 = pg.addNode();
		Node q6 = pg.addNode();
		Node q7 = pg.addFinalNode();
		
		pg.addEdge(q0, q1, "int x");
		pg.addEdge(q1, q2, "int a");
		pg.addEdge(q2, q3, "int b");
		pg.addEdge(q3, q4, "int c");
		pg.addEdge(q4, q5, "c := 1");
		pg.addEdge(q5, q6, "int z");
		pg.addEdge(q6, q7, "z := x - a * b / c");
		
	}
	
	private static void test3() {
		initialVariables.add(new InitVariable("x", " "));
		Node q0 = pg.addInitialNode();
		Node q1 = pg.addNode();
		Node q2 = pg.addNode();
		Node q3 = pg.addNode();
		Node q4 = pg.addNode();
		Node q5 = pg.addNode();
		Node q6 = pg.addFinalNode();
		
		pg.addEdge(q0, q1, "int x");
		pg.addEdge(q1, q2, "x := 3");
		pg.addEdge(q2, q3, "x := x*-1");
		pg.addEdge(q3, q4, "x := x* -1+1");
		pg.addEdge(q4, q5, "x := x - -1");
		pg.addEdge(q5, q6, "x := x -1");
		
	}
	

	private static void graph1() {
		initialVariables.add(new InitVariable("x", " "));
		initialVariables.add(new InitVariable("y", " "));
		//If-loop example
		Node q0 = pg.addInitialNode(); // q0
		Node q1 = pg.addNode(); 
		Node q2 = pg.addNode();
		Node q3 = pg.addNode(); 
		Node q4 = pg.addNode();
		Node q5 = pg.addNode(); 
		Node q6 = pg.addNode(); 
		Node q7 = pg.addFinalNode();

		pg.addEdge(q0, q1, "int x");
		pg.addEdge(q1, q2, "int y");
		pg.addEdge(q2, q3, "x := 5 + 5");
		pg.addEdge(q3, q4, "y := 3");
		pg.addEdge(q4, q5, "x == y");	//if skal altid have et else
		pg.addEdge(q5, q6, "y := 200");
		pg.addEdge(q4, q6, "y != x");	// else
		pg.addEdge(q6, q7, "x := 20");
	}
	
	private static void graph2() {
		initialVariables.add(new InitVariable("x", " "));
		//while-loop example
		Node q0 = pg.addInitialNode(); // q0
		Node q1 = pg.addNode(); 
		Node q2 = pg.addNode();
		Node q3 = pg.addNode(); 
		Node q4 = pg.addFinalNode();
		
		pg.addEdge(q0, q1, "int x");
		pg.addEdge(q1, q2, "x := 2*5");  
<<<<<<< HEAD
		pg.addEdge(q2, q3, "3 < x");		//while laves som et if loop og skal altid stadig have else
		pg.addEdge(q3, q2, "write x");
		pg.addEdge(q2, q4, "x <= 3");	//else
=======
		pg.addEdge(q2, q3, "3 <= x");		//while laves som et if loop og skal altid stadig have else
		pg.addEdge(q3, q2, "x := x - 1");
		pg.addEdge(q2, q4, "x < -1");	//else
>>>>>>> refs/remotes/origin/master
	}


	
}
