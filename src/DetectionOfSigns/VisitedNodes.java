package DetectionOfSigns;

import java.util.LinkedList;

import PG.Node;

public class VisitedNodes {
	public Node CurrentNode;
	public Node LastNode;
	public LinkedList<ResultVariable> variables = new LinkedList<ResultVariable>();
	
	public void addNode(Node nodeNew) {
		LastNode = CurrentNode;
		this.CurrentNode = nodeNew;

	}
	
	public String printAllVariables() {
		String res = CurrentNode.getLabel() + ":  ";
		
		for (int i = 0; i < variables.size(); i++) {
			res += variables.get(i).name + ": " + variables.get(i).signToString() + "   ";
		}
		
		return res;
		
	}
	
}
