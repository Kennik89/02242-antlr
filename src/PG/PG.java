package PG;


public class PG {
	
	
	public static void main(String args[] ) {
		Graph pg = new Graph();
	
		Node q0 = pg.addInitialNode(); // q0
		Node q1 = pg.addNode(); // q1
		Node q2 = pg.addFinalNode(); // q2
		
		pg.addEdge(q0, q1, "int x");
		pg.addEdge(q1, q2, "x := 5");
	}
}
