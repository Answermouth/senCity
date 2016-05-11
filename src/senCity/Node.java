package senCity;

import java.util.ArrayList;

public class Node<T, E> {
	
	private T data;
	private Node<T, E> child;
	private Node<T, E> brother;
	private ArrayList<E> elmts;
	
	public Node(T data) {
		this.data = data;
		this.child = null;
		this.brother = null;
		elmts = new ArrayList<E>();
	}
	
	public Node() {
		this.data = null;
		this.child = null;
		this.brother = null;
		elmts = new ArrayList<E>();
	}

	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public Node<T, E> getChild() {
		return child;
	}
	
	public void setChild(Node<T, E> child) {
		this.child = child; 
	}
	
	public Node<T, E> getBrother() {
		return brother;
	}
	
	public void setBrother(Node<T, E> brother) {
		this.brother = brother; 
	}
	
	public ArrayList<E> getElements() {
		return elmts;
	}
	
	public void addElement(E elmt) {
		elmts.add(elmt);
	}

}
