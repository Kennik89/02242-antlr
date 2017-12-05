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
		Iterator<ResultVariable> iter = results.iterator();
		ResultVariable resultVariable;
		while (iter.hasNext()) {
			resultVariable = iter.next();
			if (resultVariable.variable.equals(leftside)) {
				iter.remove();
			} 
		}
		results.clear();
		while(iter.hasNext()) {
			results.add(iter.next());
		}
		results.add(new ResultVariable(leftside, label));
		return this;
	}
	
}
