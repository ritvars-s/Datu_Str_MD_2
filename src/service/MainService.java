package service;

import java.util.ArrayList;

import datastr.MyBST;

public class MainService {

	public static void main(String[] args) {
		MyBST<String> bst1 = new MyBST<String>();
		try {
			//System.out.println(bst1.expressionToArray("2+3*5+4/2"));
			//System.out.println(a);
			ArrayList<String> a = bst1.correctExpression("6-8*3/2+5+4");
			//bst1.createMathTree(a);
			
			//bst1.print();
			//System.out.println("Calculation: " + bst1.calculateMathTree());
			for(int i =0; i < a.size(); i++) {
				System.out.print(a.get(i));
			}
			//System.out.println(bst1.isValid("(((22+4)*3)-8)"));
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
