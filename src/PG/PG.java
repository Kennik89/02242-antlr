package PG;


public class PG {
	static Graph pg1, pg2, pg3;
	
	
	public static void main(String args[] ) {
		test1();
		test2();
		test3();
	}

	private static void test3() {
		pg1 = new Graph();
		
		Node q0 = pg1.addInitialNode(); // q0
		Node q1 = pg1.addNode(); // q1
		Node q2 = pg1.addFinalNode(); // q2
		
		pg1.addEdge(q0, q1, "int x");
		pg1.addEdge(q1, q2, "x := 5");
	}

	private static void test2() {
		pg2 = new Graph();
		
		Node q0 = pg2.addInitialNode();
		Node q1 = pg2.addNode();
		Node q2 = pg2.addNode();
		Node q3 = pg2.addNode();
	}

	private static void test1() {
		Graph pg1 = new Graph();
	
		Node q0 = pg1.addInitialNode(); // q0
		Node q1 = pg1.addNode(); // q1
		Node q2 = pg1.addFinalNode(); // q2
	}
}
