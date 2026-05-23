package datastr;

public class MyNode<Ttype> {
	//1.mainigie
	private Ttype element;
	private MyNode<Ttype> parentNode = null;
	private MyNode<Ttype> leftChNode = null;
	private MyNode<Ttype> rightChNode = null;
	
	public Ttype getElement() {
		return element;
	}
	public MyNode<Ttype> getParentNode() {
		return parentNode;
	}
	public MyNode<Ttype> getLeftChNode() {
		return leftChNode;
	}
	public MyNode<Ttype> getRightChNode() {
		return rightChNode;
	}
	
	public void setElement(Ttype newElement){
	    if (newElement != null) {
	        element = newElement;
	    } else {
	        element = null; // or keep old value, or throw, depending on your design
	    }
	}
	public void setParentNode(MyNode<Ttype> newParentNode) {
		parentNode = newParentNode;
	}
	public void setLeftChNode(MyNode<Ttype> newLeftChNode) {
		leftChNode = newLeftChNode;
	}
	public void setRightChNode(MyNode<Ttype> newRightChNode) {
		rightChNode = newRightChNode;
	}
	public MyNode(Ttype newElement){
		setElement(newElement);
	}
	public String toString() {
		return "" + element;
	}
}