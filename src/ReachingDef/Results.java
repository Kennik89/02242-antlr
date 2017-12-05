package ReachingDef;

import java.util.LinkedList;

public class Results {
	LinkedList<ResultVariable> results = new LinkedList<>();
	
	public Results(LinkedList<String> variables) {
		for (String var : variables) {
			results.add(new ResultVariable(var));
		}
	}
	
}
