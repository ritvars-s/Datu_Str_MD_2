package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import datastr.MyBST;

public class MainService {

	public static void main(String[] args) {
		MyBST<String> bst0 = new MyBST<String>();
		MyBST<String> bst1 = new MyBST<String>();
		MyBST<String> bst2 = new MyBST<String>();
		MyBST<String> bst3 = new MyBST<String>();
		MyBST<String> bst4 = new MyBST<String>();
		ArrayList<String> expressions = new ArrayList<String>();
		try {
			
			expressions = readTextFile("C:\\Users\\kysmc\\Desktop\\expressions.txt");//C:\Users\kysmc\Desktop\expressions.txt
			for(int i =0; i < expressions.size(); i++) {
				System.out.println(expressions.get(i));
			}
			//-136
			
			
			//System.out.println(bst1.expressionToArray("2+3*5+4/2"));
			//System.out.println(a);
			ArrayList<String> a = bst0.correctExpression(expressions.get(0));//6-8*3/2+5+4 //(((9*6)+5)+sqrt4)
			ArrayList<String> b = bst1.correctExpression(expressions.get(1));
			ArrayList<String> c = bst2.correctExpression(expressions.get(2));
			ArrayList<String> d = bst3.correctExpression(expressions.get(3));
			ArrayList<String> e = bst4.correctExpression("(((9*6)+5)+sqrt4)");
			bst0.createMathTree(a);
			bst1.createMathTree(b);
			bst2.createMathTree(c);
			bst3.createMathTree(d);
			bst4.createMathTree(e);
			
			//bst3.print();
			System.out.println("Calculation pirmā rinda: " + bst0.calculateMathTree());//Calculation pirmā rinda: 20
			System.out.println("Calculation otrā rinda: " + bst1.calculateMathTree());//			Calculation otrā rinda: 10
			System.out.println("Calculation trešā rinda: " + bst2.calculateMathTree());//			Calculation trešā rinda: 24
			System.out.println("Calculation ceturtā rinda: " + bst3.calculateMathTree());//			Calculation ceturtā rinda: -136
			System.out.println("Sqrt tests: " + bst4.calculateMathTree()); //61 pareiza
			//bst3.print();
			
//			for(int i =0; i < a.size(); i++) {
//				System.out.print(a.get(i));
//			}
//			System.out.println("");
//			for(int i =0; i < b.size(); i++) {
//				System.out.print(b.get(i));
//			}
//			System.out.println("");
//			for(int i =0; i < c.size(); i++) {
//				System.out.print(c.get(i));
//			}
			System.out.println("");
//			for(int i =0; i < d.size(); i++) {
//				System.out.println(d.get(i));
//			}
//			for(int i =0; i < e.size(); i++) {
//				System.out.println(e.get(i));
//			}
			
			//System.out.println(bst1.isValid("(((22+4)*3)-8)"));
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	public static ArrayList<String> readTextFile(String filePath) throws FileNotFoundException{
		ArrayList<String> result = new ArrayList<String>();
		File file = new File(filePath);
		Scanner scan = new Scanner(file);
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			result.add(line);
		}
		return result;
	}

}
