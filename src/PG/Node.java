package PG;

public class Node {
	
	private int label;
	private Edge edgeIn_1 = null;
	private Edge edgeIn_2 = null;
	private Edge edgeOut_1 = null;
	private Edge edgeOut_2 = null;
	

	public void addEdgeIn(Edge edge) {
		if(edgeIn_1 == null) {
			edgeIn_1 = edge;
		}else if(edgeIn_2 == null){
			edgeIn_2 = edge;
		}
	}
	
	public void addEdgeOut(Edge edge) {
		if(edgeOut_1 == null) {
			edgeOut_1 = edge;
		}
		else if(edgeOut_2 == null)
		{
			edgeOut_2 = edge;
		}
	}
	
	public Edge[] getEdges() {
		Edge[] edges = new Edge[2];
		edges[0] = edgeOut_1;
		edges[1] = edgeOut_2;
		return edges;
	}
	
	public Edge[] getPreEdges()	{
		return new Edge[]{edgeIn_1, edgeIn_2};
	}
	
	public Node(int count)	{
		this.label = count;
	}

	
	public int getLabel() {
		return label;
	}

	public boolean hasTwoEdges()	{
		if(edgeIn_2 == null)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "q" + label;
	}
	

}


