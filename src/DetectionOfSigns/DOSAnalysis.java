package DetectionOfSigns;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import javax.script.ScriptException;

import PG.*;
import Variables.*;

public class DOSAnalysis {

	LinkedList<ResultVariable> detectionOfSignAnalysis = new LinkedList<ResultVariable>();
	VisitedNodes LastNodeAndVariables = new VisitedNodes();
	LinkedList<InitVariable> initialVariables = new LinkedList<InitVariable> ();

	public VisitedNodes workListAlgorith(Graph W,LinkedList<InitVariable> IV) throws NumberFormatException, ScriptException {
		initialVariables = IV;

		// instantiate all vars ^

		System.out.println("Detection of signs analysis:");


		LinkedList<Node> finalNodes = W.getFinalNodes();
		LinkedList<Node> nodeQueue = new LinkedList<Node>();
		Node s = W.getInitialNode(); 
		nodeQueue.add(s);
		int nodeSequenceIndex = 0;

		//Travel
		while(nodeQueue.size() != 0) {
			s = nodeQueue.poll();		
			LastNodeAndVariables.addNode(s);
			if(s != W.getInitialNode()) {
				addSignVariable(LastNodeAndVariables.LastNode,s, W,nodeSequenceIndex);
			}
			if(!finalNodes.contains(s)) {
				Node NextNode = evaluateEdge(s);
				nodeQueue.add(NextNode);
			}


			nodeSequenceIndex++;
		}
		return LastNodeAndVariables;
	}



	public void addSignVariable(Node node1, Node node2, Graph pg, int nodeSequenceIndex)
			throws NumberFormatException, ScriptException {
		Edge e = pg.getEdgeBetween(node1, node2);
		//der skal addes hvis kanten indeholde:
		//int x
		//int A[n];
		if(e.code.contains("int")) {
			String[] leftAndRight = e.code.split(" ");			
			String sign = " ";
			for (int i = 0; i < initialVariables.size(); i++) {
				if(initialVariables.get(i).name.equals(leftAndRight[1])) {
					sign = initialVariables.get(i).sign;
				}
			}
			LastNodeAndVariables.variables.add(new ResultVariable(leftAndRight[1],sign));	
		}

		if(e.code.contains(":=")) {
			String[] leftAndRight = e.code.split(":=");
			String leftSide = leftAndRight[0].replaceAll(" ", "");
			String rightSide = leftAndRight[1].replaceAll(" ", "");
			//går igennem højre side og erstatter bogstaver med variabler

			boolean containsVars = false;
			for (int i = 0; i < LastNodeAndVariables.variables.size(); i++) {
				if(rightSide.contains(LastNodeAndVariables.variables.get(i).name)) {
					containsVars = true;
					String replaceVar = LastNodeAndVariables.variables.get(i).name;
					if(rightSide.contains("[")) {
						replaceVar = replaceVar.replace("[", "");
						replaceVar = replaceVar.replace("]", "");
						rightSide = rightSide.replace("[", "");
						rightSide = rightSide.replace("]", "");
					}

					rightSide = rightSide.replaceAll(replaceVar,LastNodeAndVariables.variables.get(i).signToString());

				}
			}

			for (int i = 0; i < rightSide.length(); i++) {
				if(!Character.toString(rightSide.charAt(i)).equals("0")){
					if(integerCheck(Character.toString(rightSide.charAt(i)))){
						StringBuilder newS = new StringBuilder(rightSide);
						newS.setCharAt(i, '+');
						rightSide = newS.toString();
					}
				}
			}



			//lav signs om til blokke i højre side

			boolean isNextAnOperator = false;

			for (int i = 0; i < rightSide.length(); i++) {
				if(Character.toString(rightSide.charAt(i)).equals("{")) {
					i = i+6;
					isNextAnOperator =true;
					continue;
				}
				if(isNextAnOperator) {
					if(Character.toString(rightSide.charAt(i)).equals("*") || Character.toString(rightSide.charAt(i)).equals("/") ||
							Character.toString(rightSide.charAt(i)).equals("-") || Character.toString(rightSide.charAt(i)).equals("+")) {
						isNextAnOperator = false;
						continue;
					}
				}
				if(!isNextAnOperator) {
					if(Character.toString(rightSide.charAt(i)).equals("-")) {
						StringBuffer replace = new StringBuffer(rightSide);
						int start = i;
						int end = i+1;
						replace.replace(start, end,"{-, , }"); 
						isNextAnOperator = true;
						rightSide = replace.toString();
						i--;
						continue;
					}
					if(Character.toString(rightSide.charAt(i)).equals("0")) {
						StringBuffer replace = new StringBuffer(rightSide);
						int start = i;
						int end = i+1;
						replace.replace(start, end,"{ ,0, }"); 
						isNextAnOperator = true;
						rightSide = replace.toString();
						i--;
						continue;
					}
					if(Character.toString(rightSide.charAt(i)).equals("+")) {
						StringBuffer replace = new StringBuffer(rightSide);
						int start = i;
						int end = i+1;
						replace.replace(start, end,"{ , ,+}"); 
						rightSide = replace.toString();
						isNextAnOperator = true;
						i--;
						continue;
					}
				}

			}





			while(rightSide.contains("*")) {
				String first = rightSide.substring(rightSide.indexOf("*")-7, rightSide.indexOf("*"));
				String second = rightSide.substring(rightSide.indexOf("*")+1, rightSide.indexOf("*")+8);
				String newSigns = evaluateSigns(first,second,"*");

				StringBuffer replace = new StringBuffer(rightSide);

				int start = rightSide.indexOf("*")-7;
				int end =rightSide.indexOf("*")+8;
				replace.replace(start, end,newSigns); 
				rightSide = replace.toString();

			}
			while(rightSide.contains("/")) {
				String first = rightSide.substring(rightSide.indexOf("/")-7, rightSide.indexOf("/"));
				String second = rightSide.substring(rightSide.indexOf("/")+1, rightSide.indexOf("/")+8);
				String newSigns = evaluateSigns(first,second,"/");

				StringBuffer replace = new StringBuffer(rightSide);

				int start = rightSide.indexOf("/")-7;
				int end = rightSide.indexOf("/")+6;
				replace.replace(start, end,newSigns); 
				rightSide = replace.toString();
			}
			while(rightSide.contains("}+{")) {
				String first = rightSide.substring(rightSide.indexOf("}+{")-6, rightSide.indexOf("}+{")+1);
				String second = rightSide.substring(rightSide.indexOf("}+{")+2, rightSide.indexOf("}+{")+9);
				String newSigns = evaluateSigns(first,second,"+");

				StringBuffer replace = new StringBuffer(rightSide);

				int start = rightSide.indexOf("}+{")-6;
				int end = rightSide.indexOf("}+{")+9;
				replace.replace(start, end,newSigns); 
				rightSide = replace.toString();
			}
			while(rightSide.contains("}-{")) {
				String first = rightSide.substring(rightSide.indexOf("}-{")-6, rightSide.indexOf("}-{")+1);
				String second = rightSide.substring(rightSide.indexOf("}-{")+2, rightSide.indexOf("}-{")+9);
				String newSigns = evaluateSigns(first,second,"-");

				StringBuffer replace = new StringBuffer(rightSide);

				int start =rightSide.indexOf("}-{")-6;
				int end = rightSide.indexOf("}-{")+9;
				replace.replace(start, end,newSigns); 
				rightSide = replace.toString();
			}


			// adding 
			for (int i = 0; i < LastNodeAndVariables.variables.size(); i++) {
				if(LastNodeAndVariables.variables.get(i).name.contains(leftSide)) {
					LastNodeAndVariables.variables.get(i).signs[0] = Character.toString(rightSide.charAt(1));
					LastNodeAndVariables.variables.get(i).signs[1] = Character.toString(rightSide.charAt(3));
					LastNodeAndVariables.variables.get(i).signs[2] = Character.toString(rightSide.charAt(5));

					//System.out.println(LastNodeAndVariables.variables.get(i).name + ": " + LastNodeAndVariables.variables.get(i).signToString());
				}
			}



			//evaluate sign:
			//evaluate using tables from report. find * then / then + then -
			// evaluate by taking the substring on left with {} and on right{} 

			System.out.println(LastNodeAndVariables.printAllVariables());

		}


	}

	public String evaluateSigns(String first, String second, String operation) {
		String[] res = new String[3];
		String[] temp = new String[3];
		res[0] = " ";
		res[1] = " ";
		res[2] = " ";
		temp[0] = " ";
		temp[1] = " ";
		temp[2] = " ";
		for (int i = 1; i < first.length(); i = i+2) {
			for (int j = 1; j < second.length(); j= j+2) {
				temp = eval(Character.toString(first.charAt(i)),Character.toString(second.charAt(j)),operation);

				for (int j2 = 0; j2 < temp.length; j2++) {
					if(!temp[j2].equals(" ")) {
						res[j2] = temp[j2];
					}
				}
			}
		}

		return ("{"+res[0]+","+res[1]+","+res[2]+"}");
	}

	public String[] eval(String firstSign, String secondSign, String operator) {
		String[] res = new String[3];
		res[0] = " ";
		res[1] = " ";
		res[2] = " ";
		switch (operator) {
		case "*":
			if(firstSign.equals("0") || secondSign.equals("0")) {
				res[1] = "0";
			}else if((firstSign.equals("+") && secondSign.equals("+")) || (firstSign.equals("-") && secondSign.equals("-")) ) {
				res[2] = "+";
			}else if((firstSign.equals("+") && secondSign.equals("-")) || (firstSign.equals("-") && secondSign.equals("+"))){
				res[0] = "-";
			}
			break;
		case "/":
			if(firstSign.equals("0")) {
				res[0] = " ";
			}else if((firstSign.equals("+") && secondSign.equals("+")) || (firstSign.equals("-") && secondSign.equals("-")) ) {
				res[2] = "+";
			}else if(secondSign.equals("0")){
				res[1] = "0";
			}else if((firstSign.equals("+") && secondSign.equals("-")) || (firstSign.equals("-") && secondSign.equals("+"))){
				res[0] = "-";
			}
			break;
		case "-":
			if(firstSign.equals("0") && secondSign.equals("0")) {
				res[1] = "0";
			}else if((firstSign.equals("+") && secondSign.equals("+")) || (firstSign.equals("-") && secondSign.equals("-")) ) {
				res[0] = "-";
				res[1] = "0";
				res[2] = "+";
			}else if(firstSign.equals("-") && (secondSign.equals("0") || secondSign.equals("+"))){
				res[0] = "-";
			}else if(firstSign.equals("0") && secondSign.equals("-")){
				res[0] = "-";
			}else if(firstSign.equals("+") && (secondSign.equals("0") || secondSign.equals("-"))){
				res[2] = "+";
			}else if(firstSign.equals("0") && secondSign.equals("+")){
				res[2] = "+";
			}
			break;
		case "+":
			if(firstSign.equals("0") && secondSign.equals("0")) {
				res[1] = "0";
			}else if((firstSign.equals("+") && secondSign.equals("-")) || (firstSign.equals("-") && secondSign.equals("+")) ) {
				res[0] = "-";
				res[1] = "0";
				res[2] = "+";
			}else if(firstSign.equals("+") && (secondSign.equals("+") || secondSign.equals("0"))){
				res[2]= "+";
			}else if(firstSign.equals("0") && secondSign.equals("+")){
				res[2] = "+";
			}else if(firstSign.equals("-") && (secondSign.equals("0") || secondSign.equals("-"))){
				res[0] = "-";
			}else if(firstSign.equals("0") && secondSign.equals("-")){
				res[0] = "-";
			}
			break;

		default:
			break;
		}


		return res;
	}


	public Node evaluateEdge(Node n) {
		Node nextNode = null;
		Edge[] edges = n.getPreEdges();
		boolean evaluationOfEdge1 = false;
		boolean evaluationOfEdge2 = false;

		if(edges[1] != null) {
			if(edges[0] != null) {
				evaluationOfEdge1 = evaluateSingleEdge(edges[0]);
				if(evaluationOfEdge1) {
					return edges[0].getTo();
				}
			}
			if(edges[1] != null) {
				evaluationOfEdge2 = evaluateSingleEdge(edges[1]);
				return edges[1].getTo();
			}
		}else {
			return edges[0].getTo();
		}
		return nextNode;
	}


	public boolean evaluateSingleEdge(Edge e) {
		boolean result = false;
		String edge = e.getCode();
		if(edge.contains("<=")) {
			result = op(edge, "<=");

		}else if(edge.contains("<")) {
			result = op(edge, "<");
		}
		if(edge.contains("==")) {
			result = op(edge, "==");
		}
		if(edge.contains("!=")) {
			result = op(edge, "!=");

		}
		if(edge.contains("&&")) {
			boolean leftVal = false;
			boolean rightVal = false;
			String[] leftAndRight = edge.split("&&");
			String left = leftAndRight[0].replaceAll(" ", "");
			String right = leftAndRight[1].replaceAll(" ", "");

			if(left == "true") {
				leftVal = true;
			}
			if(right == "true") {
				rightVal = true;
			}
			return (leftVal && rightVal);

		}
		if(edge.contains("||")) {
			boolean leftVal = false;
			boolean rightVal = false;
			String[] leftAndRight = edge.split("||");
			String left = leftAndRight[0].replaceAll(" ", "");
			String right = leftAndRight[1].replaceAll(" ", "");

			if(left == "true") {
				leftVal = true;
			}
			if(right == "true") {
				rightVal = true;
			}
			return (leftVal || rightVal);

		}
		return result;
	}
	public static boolean integerCheck(String variable) {
		try { 
			Integer.parseInt(variable); 
		} catch(NumberFormatException e) { 
			return false; 
		} catch(NullPointerException e) {
			return false;
		}
		return true;
	}
	public boolean op(String e, String operation) {
		String[] leftAndRight = e.split(operation);
		String left = leftAndRight[0].replaceAll(" ", "");
		String right = leftAndRight[1].replaceAll(" ", "");
		int leftSide = 0;
		int rightSide = 0;
		int[] leftSideArray = new int[3];
		int[] rightSideArray = new int[3];
		boolean multipleChecksLeft = false;
		boolean multipleChecksRight = false;
		boolean[] leftSideBoolArray = new boolean[3];
		boolean[] rightSideBoolArray = new boolean[3];

		for (int i = 0; i < rightSideBoolArray.length; i++) {
			leftSideBoolArray[i] =false;
			rightSideBoolArray[i] =false;
		}

		if(integerCheck(left)){
			leftSide = Integer.parseInt(left);
			if(leftSide> 0) {
				leftSide = 1;
			}else if(leftSide ==0) {
				leftSide =0;
			}else {
				leftSide = -1;
			}

		}else {
			for (int i = 0; i < LastNodeAndVariables.variables.size(); i++) {
				if(LastNodeAndVariables.variables.get(i).name.equals(left)) {
					if(!LastNodeAndVariables.variables.get(i).signs[0].equals(" ")) {
						leftSideArray[0] = -1;
						leftSideBoolArray[0] = true;
					}
					if(!LastNodeAndVariables.variables.get(i).signs[1].equals(" ")) {
						leftSideArray[1] = 0;
						leftSideBoolArray[1] = true;
					}
					if(!LastNodeAndVariables.variables.get(i).signs[2].equals(" ")) {
						leftSideArray[2] = 1;
						leftSideBoolArray[2] = true;
					}
					multipleChecksLeft = true;
				}
			}
		}
		if(integerCheck(right)){
			rightSide = Integer.parseInt(right);
			if(rightSide> 0) {
				rightSide = 1;
			}else if(rightSide ==0) {
				rightSide =0;
			}else {
				rightSide = -1;
			}
		}else {
			for (int i = 0; i < LastNodeAndVariables.variables.size(); i++) {
				if(LastNodeAndVariables.variables.get(i).name.equals(right)) {
					if(!LastNodeAndVariables.variables.get(i).signs[0].equals(" ")) {
						rightSideArray[0] = -1;
						rightSideBoolArray[0] = true;
					}
					if(!LastNodeAndVariables.variables.get(i).signs[1].equals(" ")) {
						rightSideArray[1] = 0;
						rightSideBoolArray[1] = true;
					}
					if(!LastNodeAndVariables.variables.get(i).signs[2].equals(" ")) {
						rightSideArray[2] = 1;
						rightSideBoolArray[2] = true;
					}
					multipleChecksRight = true;
				}
			}
		}


		if(multipleChecksRight && multipleChecksLeft) {
			boolean allIsTrue = true;
			for (int i = 0; i < leftSideArray.length; i++) {
				for (int j = 0; j < rightSideArray.length; j++) {
					if(leftSideBoolArray[i] && rightSideBoolArray[j]) {
						if(!operationCheck(leftSideArray[i],rightSideArray[j],operation)) {
							allIsTrue = false;
						}
					}
				}
			}
			return allIsTrue;
		}else if(!multipleChecksRight && multipleChecksLeft){
			boolean allIsTrue = true;
			for (int i = 0; i < leftSideArray.length; i++) {
				if(leftSideBoolArray[i]) {
					if(!operationCheck(leftSideArray[i],rightSide,operation)) {
						allIsTrue = false;
					}
				}
			}
			return allIsTrue;
		}else if(multipleChecksRight && !multipleChecksLeft) {
			boolean allIsTrue = true;
			for (int i = 0; i < leftSideArray.length; i++) {
				if(rightSideBoolArray[i]) {
					if(!operationCheck(leftSide,rightSideArray[i],operation)) {
						allIsTrue = false;
					}
				}
			}
			return allIsTrue;
		}else {
			return operationCheck(leftSide,rightSide,operation);
		}



	}

	public boolean operationCheck(int leftSide, int rightSide, String operation) {
		if(operation.equals("<=")) {
			return (leftSide <= rightSide);
		}
		if(operation.equals("<")) {
			return (leftSide < rightSide);
		}
		if(operation.equals("!=")) {
			return (leftSide != rightSide);
		}
		if(operation.equals("==")) {
			return (leftSide == rightSide);
		}
		return false;
	}

}
