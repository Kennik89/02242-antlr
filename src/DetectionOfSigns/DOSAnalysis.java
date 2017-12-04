package DetectionOfSigns;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import PG.NodeAndVariable;
import Variables.*;

public class DOSAnalysis {

	

	public void getAnalysisReportTable(LinkedList<NodeAndVariable> route) {
		Results analysisResults = new Results(route.get(route.size()-1).var.getAllVariables());
		System.out.println("\nDetection of sign analysis:");

		for (int i = 0; i < route.size(); i++) {
			for (int j = 0; j < route.get(i).var.getAllVariables().length; j++) {
				addResult(j,route.get(i).var.getVariable(j).value,route.get(i).var.getVariable(j).name,analysisResults);
			}
			
			System.out.print("Node: " +route.get(i).node.getLabel() +" : \t");
			for (int j = 0; j < analysisResults.results.size(); j++) {
				System.out.print( analysisResults.results.get(j).print());
			}
			System.out.println();
			
		}

		
	}


	public void addResult(int i, int value,String variableName, Results analysisResults) {

		if(variableName.contains("[")) {
			if(value > 0) {
				if(!Arrays.asList(analysisResults.results.get(i).signs).contains("+")) {
					analysisResults.results.get(i).signs[0] = ("+");
				}
			}else if(value == 0 ) {
				if(!Arrays.asList(analysisResults.results.get(i).signs).contains("0")) {
					analysisResults.results.get(i).signs[1] = ("0");
				}
			}else {
				if(!Arrays.asList(analysisResults.results.get(i).signs).contains("-")) {
					analysisResults.results.get(i).signs[2] = ("-");
				}
			}
		}else {
			if(value > 0) {
				if(!Arrays.asList(analysisResults.results.get(i).signs).contains("+")) {
					analysisResults.results.get(i).signs[0] = ("+");
					analysisResults.results.get(i).signs[1] = ("");
					analysisResults.results.get(i).signs[2] = ("");
				}
			}else if(value == 0 ) {
				if(!Arrays.asList(analysisResults.results.get(i).signs).contains("0")) {
					analysisResults.results.get(i).signs[1] = ("0");
					analysisResults.results.get(i).signs[0] = ("");
					analysisResults.results.get(i).signs[2] = ("");
				}
			}else {
				if(!Arrays.asList(analysisResults.results.get(i).signs).contains("-")) {
					analysisResults.results.get(i).signs[2] = ("-");
					analysisResults.results.get(i).signs[1] = ("");
					analysisResults.results.get(i).signs[0] = ("");
				}
			}



		}



	}


}
