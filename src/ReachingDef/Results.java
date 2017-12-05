package ReachingDef;

import java.util.LinkedList;

public class Results {
	LinkedList<ResultVariable> results = new LinkedList<>();
	
	public Results(LinkedList<String> variables) {
		for (String var : variables) {
			results.add(new ResultVariable(var));
		}
	}
	
	public Results uniqueAdd(Results RV) {
		for (ResultVariable resultVariable : RV.getVariables()) {
			if(!results.contains(resultVariable))	{
				results.add(resultVariable);
			}
		}
		return this;
	}
	
	public LinkedList<ResultVariable> getVariables() {
		return results;

	}
	
	public void print() {
		for (ResultVariable resultVariable : results) {
			System.out.print(" " + resultVariable.toString());
		}
	}

	public Results killGen(String leftside) {
		for (ResultVariable resultVariable : results) {
			if (resultVariable.variable.equals(leftside)) {
				results.remove(resultVariable);
			} 
		}
		results.add(new ResultVariable(leftside));
		return this;
	}
	
}
