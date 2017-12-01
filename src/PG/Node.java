package PG;

public class Node {
	
	private String label;

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


