package PG;

class Node {
	
	private String label;
	private Edge edgeOut_1 = null;
	private Edge edgeOut_2 = null;
	
	public void addEdge(Edge edge) {
		if(edgeOut_1 == null) {
			edgeOut_1 = edge;
		}
		else if(edgeOut_2 == null)
		{
			edgeOut_2 = edge;
		}
		
	}
	
	public Edge[] getEgdes() {
		Edge[] edges = new Edge[2];
		edges[0] = edgeOut_1;
		edges[1] = edgeOut_2;
		return edges;
	}
	
	public Node(String label)	{
		this.label = label;
	}

	
	public String getLabel() {
		return label;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + " " + label;
	}
	

}


