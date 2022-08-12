package main;

import java.util.Scanner;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Main {

	private static Scanner sc = new Scanner(System.in);
	
	private static char[][] createGraph(int size, String function) {
		size = (size % 2 != 0) ? size : size + 1;
		
		// Create graph
		char[][] result = new char[size][size];
		
		for (int i = 0; i < size; i++) for (int k = 0; k < size; k++) {
			if (i == size - 1) for (int j = 0; j < size; j++) result[i][j] = '-';
			if (k == 0) for (int j = 0; j < size; j++) result[j][k] = '|';
			if (i == size - 1 && k == 0) result[i][k] = '+';
		}
		
		for (int i = 0; i < size ; i++) {
			Expression e = new ExpressionBuilder(function.split(" = ")[1].replaceAll(" x ", " -x ")).variables("x").build().setVariable("x", i);
			result[size - i - 1][(int) ((e.evaluate() > size - 1) ? size - 1 : e.evaluate())] = '#';
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println("Please enter the function: ");
		String func = sc.nextLine();
		System.out.println("Please enter the size of graph: ");
		int size = sc.nextInt();
		
		char[][] graph = createGraph(size, func);
		for (char[] str : graph) { for (char c : str) { System.out.print(c); } System.out.println(); } 
	}
	
}
