package ReachingDef;

import java.util.Iterator;
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
		System.out.print("{");
		for (ResultVariable resultVariable : results) {
			System.out.print(" " + resultVariable.toString());
		}
		System.out.println(" }");
	}

	public Results killGen(String leftside, int label) {
		LinkedList<ResultVariable> temp = new LinkedList<>();
		for (ResultVariable resultVariable : results) {
			if(!resultVariable.variable.equals(leftside)) {
				temp.add(resultVariable);
			}
		}
		temp.add(new ResultVariable(leftside, label));
		results.clear();
		results.addAll(temp);
		return this;
	}
	
}
