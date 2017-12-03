package PG;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import Variables.SingleVariable;
import Variables.Variables;

public class GraphTraversal {

	Variables var = new Variables();
	ScriptEngineManager SEM = new ScriptEngineManager();
	ScriptEngine calculator = SEM.getEngineByName("JavaScript");

	public void addVariable(Node node1, Node node2, Graph pg) throws NumberFormatException, ScriptException {
		Edge e = pg.getEdgeBetween(node1, node2);
		//der skal addes hvis kanten indeholde:
		//int x
		//int A[n];
		if(e.code.contains("int")) {
			String[] leftAndRight = e.code.split(" ");			
			var.addVariable(new SingleVariable(leftAndRight[1], 0));	
		}

		//der skal kun opdateres hvis kanten indeholder:
		//{+,-,*,/}
		// x := something
		// A[a1] := a2
		if(e.code.contains(":=")) {

			String[] leftAndRight = e.code.split(":=");
			String leftSide = leftAndRight[0].replaceAll(" ", "");
			String rightSide = leftAndRight[1].replaceAll(" ", "");
			//går igennem højre side og erstatter bogstaver med variabler
			for (int i = 0; i < rightSide.length(); i++) {
				if(Character.isLetter(rightSide.charAt(i))) {
					rightSide = rightSide.replaceAll(Character.toString(rightSide.charAt(i)), 
							Integer.toString(var.findValueOfVariable(Character.toString(rightSide.charAt(i)))));
				}
			}
			var.addValueToVariable(leftSide, Integer.parseInt(calculator.eval(rightSide).toString()));
		} 
	}

	public LinkedList<NodeAndVariable> graphWalker(Graph pg) throws NumberFormatException, ScriptException {
		LinkedList<NodeAndVariable> CompeteNodeSequence = new LinkedList<NodeAndVariable>();
		LinkedList<Node> finalNodes = pg.finalNodes;
		LinkedList<Node> nodeQueue = new LinkedList<Node>();
		Node s = pg.initialNode; 
		nodeQueue.add(s);
		int nodeSequenceIndex = 0;

		//Travel
		while(nodeQueue.size() != 0) {
			s = nodeQueue.poll();		
			CompeteNodeSequence.add(new NodeAndVariable(s, var));
			if(s != pg.initialNode) {
				addVariable(CompeteNodeSequence.get(nodeSequenceIndex-1).node,s, pg);
			}
			if(!finalNodes.contains(s)) {
				Node NextNode = evaluateEdge(s);
				nodeQueue.add(NextNode);
			}
			
			System.out.println(s.getLabel() + " : " + var.allVariables());
			nodeSequenceIndex++;
		}
		return CompeteNodeSequence;
	}

	public Node evaluateEdge(Node n) {
		Node nextNode = null;
		Edge[] edges = n.getEgdes();
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

		if(integerCheck(left)){
			leftSide = Integer.parseInt(left);
		}else {
			leftSide = var.findValueOfVariable(left);
		}
		if(integerCheck(right)){
			rightSide = Integer.parseInt(right);
		}else {
			rightSide = var.findValueOfVariable(right);
		}


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
