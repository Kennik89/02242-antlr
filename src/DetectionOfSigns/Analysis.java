package DetectionOfSigns;

import PG.Edge;
import Variables.*;

public class Analysis {
	Variables variables = new Variables();
	String variableName = null;
	public void computeAnalysisResult(Edge e) {
		String activity = null;

		
		//a
		
		//b
		
		//d
		if(e.getCode().contains("int ")) {
			variableName = e.getCode().substring(4, 5);
			activity = "int";
			if(e.getCode().contains("[")) {
				activity = "array";
			}
		}
		
		switch(activity) {
		case "int":
			variables.addVariable(new SingleVariable(variableName, 0));
		case "array":
			variables.addVariable(new SingleVariable(variableName, 0));
		}
		



	}


}
