package datastr;

import java.util.ArrayList;

public class MyBST<Ttype> {
	private MyNode<Ttype> rootNode = null;
	private int howManyElements = 0;
	
	//beazargumenta konstruktors bus no object klases
	
	public int getHowManyElements() {
		return howManyElements;
	}
	
	public boolean isEmpty() {
		return (howManyElements == 0);
	}
	
	public boolean isFull() {
		try {
			new MyNode<Character>('A');
			return false;
		}
		catch(OutOfMemoryError error) {
			return true;
		}
	}
	
	public void add(Ttype elem) throws Exception{
		if(isFull()) {
			throw new Exception("Koks ir pilns un nevar vairs pievienot jaunus elementus");
		}
		//koks ir tuk'ss, tad ieliekam primo ka root
		if(isEmpty()) {
			MyNode<Ttype> newNode = new MyNode<Ttype>(elem);
			rootNode = newNode;
		}
		else {
			addHelper(rootNode, elem);
		}
		howManyElements++;
		
	}
	
	private void addHelper(MyNode<Ttype> nodeTemp, Ttype elem) {
		if(nodeTemp != null) {
			//parbaudam, uz kuru pusi elements jaāpārvieto
			//ja lielāks, tad pa labi
			if (((Comparable)elem).compareTo(nodeTemp.getElement()) > 0) { //novietosies pa labi
				//ja laba puse neka nav
				if(nodeTemp.getRightChNode() == null) {
					MyNode<Ttype> newNode = new MyNode<Ttype>(elem);
					newNode.setParentNode(nodeTemp);
					nodeTemp.setRightChNode(newNode);
				}
				else {
					addHelper(nodeTemp.getRightChNode(), elem);
				}
			}
			
			else {//novietosies pa kreisi
				//ja kreisa puse neka nav
				if(nodeTemp.getLeftChNode() == null) {
					MyNode<Ttype> newNode = new MyNode<Ttype>(elem);
					newNode.setParentNode(nodeTemp);
					nodeTemp.setLeftChNode(newNode);
				}
				else {
					addHelper(nodeTemp.getLeftChNode(), elem);
				}	
			}
		}
	}
	
	public void print() throws Exception {
		if (isEmpty()) {
			throw new Exception("Kaudze ir tukša un to nevar izprintēt");
		}

		printHelper(rootNode);
	}

	private void printHelper(MyNode<Ttype> nodeTemp) {
		if (nodeTemp != null) {
			System.out.println("P: " + nodeTemp.getElement());
			// noskaidrojam, vai eksiste kreisais bērns
			if (nodeTemp.getLeftChNode() != null) {
				System.out.println(
						"P: " + nodeTemp.getElement() + " Left child: " + nodeTemp.getLeftChNode().getElement());
				// izpildi so pasu funkciju uz kreiso bērnu
				printHelper(nodeTemp.getLeftChNode());
			}
			// noskaidrojam, vai eksistē labais bērns
			if (nodeTemp.getRightChNode() != null) {
				System.out.println(
						"P: " + nodeTemp.getElement() + " Right child: " + nodeTemp.getRightChNode().getElement());
				printHelper(nodeTemp.getRightChNode());
			}
		}
	}
	
	public boolean search(Ttype elem) throws Exception{
		if (isEmpty()) {
			throw new Exception("Kaudze ir tukša un to nevar izprintēt");
		}
		
		return searchHelper(rootNode, elem);
	}
	
	private boolean searchHelper(MyNode<Ttype> nodeTemp, Ttype elem) {
		if(nodeTemp != null) {
			//ja sakrit tad atgriezam ka ir atrasts
			if(nodeTemp.getElement().equals(elem)) {
				return true;
			}
			//turpina meklet
			else {
				//meklesana notiks pa labo pusi
				if(((Comparable)elem).compareTo(nodeTemp.getElement()) > 0) {
					//parbauda vai eksisste labais node
					if (nodeTemp.getRightChNode() == null) {
						return false;
					}
					// ja eksiste turpia na iteraciju
					else {
						return searchHelper(nodeTemp.getRightChNode(), elem);
					}
				}
				else {
					if (nodeTemp.getLeftChNode() == null) {
						return false;
					}
					else {
						return searchHelper(nodeTemp.getLeftChNode(), elem);
					}	
				}	
			}
		}
		return false;
	}
	
	public void remove(Ttype elem) throws Exception{
		if (isEmpty()) {
			throw new Exception("Kaudze ir tukša");
		}
	}
	
	private void removeHelper(MyNode<Ttype> nodeTemp, Ttype elem) throws Exception{
		if(nodeTemp != null) {
			//ja sakrit tad atgriezam ka ir atrasts
			if(nodeTemp.getElement().equals(elem)) {
				//varianti
				//ja nav berni
				if(nodeTemp.getLeftChNode() == null && nodeTemp.getRightChNode() == null) {
					MyNode<Ttype> parentNode = nodeTemp.getParentNode();
					//ja dzesamais elem ir lielaks par savu vecaku, tad tas ir labais berns
					if(((Comparable)nodeTemp.getElement()).compareTo(parentNode.getElement()) > 0) {
						parentNode.setRightChNode(null);
					}
					//ja ne tad tad ir kreisais
					else {
						parentNode.setLeftChNode(null);
					}
				}
				//ja ir berni
				//ja ir tikai kreisais berns
				else if(nodeTemp.getLeftChNode() != null && nodeTemp.getRightChNode() == null) {
					MyNode<Ttype> parentNode = nodeTemp.getParentNode();
					MyNode<Ttype> leftChNode = nodeTemp.getRightChNode();
					//ja dzesamais elem ir lielaks par savu vecaku, tad tas ir labais berns
					if(((Comparable)nodeTemp.getElement()).compareTo(parentNode.getElement()) > 0) {
						parentNode.setRightChNode(leftChNode);
						leftChNode.setParentNode(parentNode);
					}
					//ja ne tad tad ir kreisais
					else {
						parentNode.setLeftChNode(leftChNode);
						leftChNode.setParentNode(parentNode);
					}
				}
				//ja ir tikai labais berns
				else if(nodeTemp.getLeftChNode() == null && nodeTemp.getRightChNode() != null) {
					MyNode<Ttype> parentNode = nodeTemp.getParentNode();
					MyNode<Ttype> rightChNode = nodeTemp.getRightChNode();
					//ja dzesamais elem ir lielaks par savu vecaku, tad tas ir labais berns
					if(((Comparable)nodeTemp.getElement()).compareTo(parentNode.getElement()) > 0) {
						parentNode.setRightChNode(rightChNode);
						rightChNode.setParentNode(parentNode);
					}
					//ja ne tad tad ir kreisais
					else {
						parentNode.setLeftChNode(rightChNode);
						rightChNode.setParentNode(parentNode);
					}
				}
				//ir abi berni
				else {
					//TODO uztaisit tuvaka elementa atrasanas algoritmu lai to ievietotu dzesamaja elementa
					//TODO notestet dzesanu mainservice
					
				}
				
			}
				
				
			//turpina meklet
		else {
			//meklesana notiks pa labo pusi
			if(((Comparable)elem).compareTo(nodeTemp.getElement()) > 0) {
				//parbauda vai eksisste labais node
				if (nodeTemp.getRightChNode() != null) {
					removeHelper(nodeTemp.getRightChNode(), elem);
				}
			}
				// ja eksiste turpina pa kreiso pusi
			else {
					if (nodeTemp.getLeftChNode() != null) {
						removeHelper(nodeTemp.getLeftChNode(), elem);
					}
			}
		}
		}
	}
	//====================================================
	
	//padoto izteiksmē ieliek iekavas //TODO japartaisa isak vai saprotamak
	public ArrayList<String> correctExpression(String expression) {

	    expression = expression.replace(" ", "");
	    ArrayList<String> expression1 = new ArrayList<>();
	    String temp = "";

	    for (int i = 0; i < expression.length(); i++) {
	        char c = expression.charAt(i);

	        // --- UNARY MINUS FIX ---
	        if (c == '-' && i + 1 < expression.length() && Character.isDigit(expression.charAt(i + 1)) &&
	            (i == 0 || expression.charAt(i - 1) == '(' || "+-*/^".contains("" + expression.charAt(i - 1)))) {

	            if (!temp.isEmpty()) {
	                expression1.add(temp);
	                temp = "";
	            }

	            expression1.add("-");  // minus is its own token
	            continue;
	        }

	        // Numbers and variables
	        if (Character.isDigit(c) || Character.isLetter(c)) {
	            temp += c;
	        }
	        else {
	            if (!temp.isEmpty()) {
	                expression1.add(temp);
	                temp = "";
	            }
	            expression1.add("" + c);
	        }
	    }

	    if (!temp.isEmpty()) {
	        expression1.add(temp);
	    }

	    // --- Parentheses balancing logic ---
	    int open = 0, close = 0;
	    for (String t : expression1) {
	        if (t.equals("(")) open++;
	        if (t.equals(")")) close++;
	    }

	    boolean balanced = open == close;
	    boolean startsCorrect = expression.startsWith("(");
	    boolean endsCorrect = expression.endsWith(")");

	    if (balanced && startsCorrect && endsCorrect) {
	        return expression1;
	    }

	    // Rebuild and correct expression
	    StringBuilder sb = new StringBuilder();
	    for (String t : expression1) sb.append(t);

	    String expr = sb.toString();
	    expr = correctExpressionHelper(expr, "*/");
	    expr = correctExpressionHelper(expr, "+-");

	    if (!expr.startsWith("(")) expr = "(" + expr;
	    if (!expr.endsWith(")")) expr = expr + ")";

	    return correctExpression(expr);
	}

	private String correctExpressionHelper(String expr, String ops) {
	    StringBuilder sb = new StringBuilder(expr);

	    for (int i = 0; i < sb.length(); i++) {
	        char c = sb.charAt(i);

	        if (ops.indexOf(c) >= 0) {

	            int leftStart = i - 1;

	            // Left side
	            if (sb.charAt(leftStart) == ')') {
	                int depth = 1;
	                leftStart--;
	                while (leftStart >= 0 && depth > 0) {
	                    if (sb.charAt(leftStart) == ')') depth++;
	                    if (sb.charAt(leftStart) == '(') depth--;
	                    leftStart--;
	                }
	            } else {
	                while (leftStart >= 0 && Character.isDigit(sb.charAt(leftStart))) {
	                    leftStart--;
	                }
	            }

	            // Right side
	            int rightEnd = i + 1;

	            if (sb.charAt(rightEnd) == '(') {
	                int depth = 1;
	                rightEnd++;
	                while (rightEnd < sb.length() && depth > 0) {
	                    if (sb.charAt(rightEnd) == '(') depth++;
	                    if (sb.charAt(rightEnd) == ')') depth--;
	                    rightEnd++;
	                }
	            } else {
	                while (rightEnd < sb.length() && Character.isDigit(sb.charAt(rightEnd))) {
	                    rightEnd++;
	                }
	            }

	            sb.insert(leftStart + 1, "(");
	            sb.insert(rightEnd + 1, ")");
	            i = rightEnd + 1;
	        }
	    }

	    return sb.toString();
	}





	//pavers parbauda vai string ir skatilis
//	private boolean isNumber(String str) {
//	    if (str == null || str.isEmpty()) {
//	        return false;
//	    }
//	    if (str.charAt(0) == '-' && str.length() > 1) {
//	        for (int i = 1; i < str.length(); i++) {
//
//	            if (!Character.isDigit(str.charAt(i))) {
//	                return false;
//	            }
//	        }
//	        return true;
//	    }
//	    for (int i = 0; i < str.length(); i++) {
//
//	        if (!Character.isDigit(str.charAt(i))) {
//	            return false;
//	        }
//	    }
//	    return true;
//	}
	
	//parverš string numura
	private int stringToNumber(String num) {
		int number = Integer.parseInt(num);
		return number;
	}
	
	//izveido matematisko koku
	public void createMathTree(ArrayList<String> exp) throws Exception {
		if(isFull()) {
			throw new Exception("Koks ir pilns un nevar vairs pievienot jaunus elementus");
		}
		//koks ir tuk'ss, tad ieliekam primo ka root
		if(exp == null || exp.isEmpty()) {
			  throw new Exception("Izteiksme ir tukša");
		}
		MyNode<String> newNode = new MyNode<String>("");
		rootNode = (MyNode<Ttype>) newNode;
		MyNode<String> newNode1 = new MyNode<String>("");
		rootNode.setLeftChNode((MyNode<Ttype>) newNode1);
		newNode1.setParentNode((MyNode<String>) rootNode);
		int index = 1;
		createMathTreeHelper((MyNode<Ttype>) newNode1, exp, index);
		howManyElements++;
	}
	private void createMathTreeHelper(MyNode<Ttype> nodeTemp, ArrayList<String> exp, int index) {
		if (nodeTemp == null) {
	        return;
	    }
		int index1 = ++index;
		if(exp.size() <= index1) {
			return;
		}
		else if (exp.get(index1).equals("(")) {
			
			MyNode<String> newNode = new MyNode<String>("");
			nodeTemp.setLeftChNode((MyNode<Ttype>) newNode);
			newNode.setParentNode((MyNode<String>) nodeTemp);
			howManyElements++;
			createMathTreeHelper((MyNode<Ttype>) newNode, exp, index1);
		}
		else if (exp.get(index1).equals(")")) {
			MyNode<String> newNode = (MyNode<String>) nodeTemp.getParentNode();
			createMathTreeHelper((MyNode<Ttype>) newNode, exp, index1);
		}
		else if (!Character.isDigit(exp.get(index1).charAt(0))) {
			//ja ir sqrt
			if(exp.get(index1).equals("sqrt")) {
				nodeTemp.setElement((Ttype) exp.get(index1));
				MyNode<String> newNode = new MyNode<String>("");
				nodeTemp.setLeftChNode((MyNode<Ttype>) newNode);
				newNode.setParentNode((MyNode<String>) nodeTemp);
				createMathTreeHelper((MyNode<Ttype>) newNode, exp, index1);
			}
			
//			else if(exp.get(index1).equals("-") && exp.get(index1-1).equals("(") || exp.get(index1-1).equals(")")){
//				nodeTemp.setElement((Ttype) exp.get(index1));
//				MyNode<String> newNode = new MyNode<String>("0");
//				nodeTemp.setLeftChNode((MyNode<Ttype>) newNode);
//				newNode.setParentNode((MyNode<String>) nodeTemp);
//				createMathTreeHelper((MyNode<Ttype>) newNode, exp, index1);
//			}
			else {
				nodeTemp.setElement((Ttype) exp.get(index1));
				MyNode<String> newNode = new MyNode<String>("");
				nodeTemp.setRightChNode((MyNode<Ttype>) newNode);
				newNode.setParentNode((MyNode<String>) nodeTemp);
				createMathTreeHelper((MyNode<Ttype>) newNode, exp, index1);
			}
	
		}
		else {
//			if (exp.get(index1).charAt(0) == '-') {
//				nodeTemp.setElement((Ttype) (exp.get(index1).charAt(0) + ""));
//				MyNode<String> leftNewNode = new MyNode<String>("0");
//				MyNode<String> rightNewNode = new MyNode<String>(exp.get(index1).substring(1));
//				nodeTemp.setRightChNode((MyNode<Ttype>) rightNewNode);
//				nodeTemp.setLeftChNode((MyNode<Ttype>) leftNewNode);
//				leftNewNode.setParentNode((MyNode<String>) nodeTemp);
//				rightNewNode.setParentNode((MyNode<String>) nodeTemp);
//				MyNode<String> parent = (MyNode<String>) nodeTemp.getParentNode();
//				createMathTreeHelper((MyNode<Ttype>) parent, exp, index1);
//			}
	//		else {
			nodeTemp.setElement((Ttype) exp.get(index1));
			MyNode<String> newNode = (MyNode<String>) nodeTemp.getParentNode();
			howManyElements++;
			createMathTreeHelper((MyNode<Ttype>) newNode, exp, index1);
			//}
		}
		
	}
	
	//parbauda vai padotais String ir vienads ar koka izteiksmi
	public boolean isValid(String expression) {
	    String newExpression = "";

	    for (int i = 0; i < expression.length(); i++) {
	        char c = expression.charAt(i);
	        if (c != '(' && c != ')') {
	            newExpression += c;
	        }
	    }

	    String treeExpression = isValidHelper(rootNode);

	    return treeExpression.equals(newExpression);
	}
	public String isValidHelper(MyNode<Ttype> nodeTemp) {
	    if (nodeTemp == null) {
	        return "";
	    }

	    String treeExp1 = "";

	    if (nodeTemp.getLeftChNode() != null) {
	        treeExp1 += isValidHelper(nodeTemp.getLeftChNode());
	    }
	    treeExp1 += nodeTemp.getElement();

	    if (nodeTemp.getRightChNode() != null) {
	        treeExp1 += isValidHelper(nodeTemp.getRightChNode());
	    }

	    return treeExp1;
	}
	
	//aprekina izteiksmi
	private int calculation(int leftNum, String symbol, int rightNum) {
		if(symbol.equals("+")) {
			return leftNum + rightNum;
		}
		if(symbol.equals("-")) {
			return leftNum - rightNum;
		}
		if(symbol.equals("*")) {
			return leftNum * rightNum;
		}
		if(symbol.equals("/")) {
			return leftNum / rightNum;
		}
		if(symbol.equals("^")) {
			return (int) Math.pow(leftNum, rightNum);
		}
		if(symbol.equals("sqrt")) {
			return (int) Math.sqrt(leftNum);
		}
		return 0;
	}
	
	public int calculateMathTree() throws Exception {
		if(isEmpty()) {
			throw new Exception("Kokā nav neviena elementa ko aprēķināt");
		}
		MyNode<String> newNode = (MyNode<String>) rootNode;
		int result = calculateMathTreeHelper(newNode);
		return result;
		
	}
	private int calculateMathTreeHelper(MyNode<String> node) {
	    if (node == null) {
	        return 0;
	    }
	    String element = node.getElement();
	    if (Character.isDigit(element.charAt(0))) { //========================================
	        return stringToNumber(element);
	    }
	    if (element.equals("sqrt")) {
	        return (int)Math.sqrt(calculateMathTreeHelper(node.getLeftChNode()));
	    }
	    int left = calculateMathTreeHelper(node.getLeftChNode());
	    int right = calculateMathTreeHelper(node.getRightChNode());

	    return calculation(left, element, right);
	}
}