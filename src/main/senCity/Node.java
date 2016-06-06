package main.senCity;

import java.util.ArrayList;

public class Node {
	
	private char data;
	private Node child;
	private Node brother;
	private ArrayList<Trace> elmts;
	
	public Node(char data) {
		this.data = data;
		this.child = null;
		this.brother = null;
		elmts = new ArrayList<Trace>();
	}
	
	public Node() {
		this.data = '\0';
		this.child = null;
		this.brother = null;
		elmts = new ArrayList<Trace>();
	}

	public char getData() {
		return data;
	}
	
	public void setData(char data) {
		this.data = data;
	}
	
	public Node getChild() {
		return child;
	}
	
	public void setChild(Node child) {
		this.child = child; 
	}
	
	public Node getBrother() {
		return brother;
	}
	
	public void setBrother(Node brother) {
		this.brother = brother; 
	}
	
	public ArrayList<Trace> getElements() {
		return elmts;
	}
	
	public void addElement(Trace elmt) {
		elmts.add(elmt);
	}
	
	
	public void ajouter(String ssid, int i, Trace trace) {
		if (this.data == '\0')
			this.data = ssid.charAt(i);
		
		if (this.data == ssid.charAt(i)) {
			if (i == ssid.length()-1)
				this.addElement(trace);
			else if (this.child != null)
				this.child.ajouter(ssid, i+1, trace);
			else {
				this.child = new Node();
				this.child.ajouter(ssid, i+1, trace);
			}
			
		} else {
			if (this.brother != null)
				this.brother.ajouter(ssid, i, trace);
			else {
				this.brother = new Node();
				this.brother.ajouter(ssid, i, trace);
			}
		}
	}
	
	
	public Node find(String ssid, int i) {
		if (ssid.charAt(i) == this.data)
			if (i == ssid.length()-1)
				return this;
			else if (this.child != null)
				return this.child.find(ssid, i+1);
			else 
				return null;
		
		else if (this.brother != null)
			return this.brother.find(ssid, i);
		
		return null;
	}
	
	
	public String toString() {
		String s = "";
		
		for (Trace t: this.elmts) 
			s += t.toString() + "\n";
		
		if (this.brother != null) 
			s += this.brother.toString();
			
		if (this.child != null) 
			s += this.child.toString();
		
		
		return s;
	}
}
