package Variables;

import java.util.ArrayList;

public class Variables {
	ArrayList<SingleVariable> variables = new ArrayList<SingleVariable>();
	
	public void addVariable(SingleVariable var) {
		variables.add(var);
	}
	
	public int size() {
		return variables.size();
	}
	
	public SingleVariable getVariable(int i) {
		return variables.get(i);
	}
	
	
	
	public int findValueOfVariable(String name) {
		for (int i = 0; i < variables.size(); i++) {
			if(variables.get(i).name.equals(name)) {
				return variables.get(i).value;
			}
		}
		return 0;
	}
	
	public void addValueToVariable(String name, int value) {
		for (int i = 0; i < variables.size(); i++) {
			if(variables.get(i).name.equals(name)) {
				variables.get(i).value = value;
			}
		}
	}
	
	public String allVariables() {
		String vars = "";
		
		for (int i = 0; i < variables.size(); i++) {
			vars += variables.get(i).name;
			vars += ": ";
			vars += variables.get(i).value;
			vars += "   ";
		}
		
		return vars;
	}
	
	public String[] getAllVariables() {
		String[] res = new String[variables.size()];
		for (int i = 0; i < variables.size(); i++) {
			res[i] = variables.get(i).name;
		}
		return res;
	}
	
}



