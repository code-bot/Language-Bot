package com.languagebot.sahaj;

public class DirectedEdge {
	private int pos, neg;
	private int connotationVal;
	private Node head, tail;
	
	public DirectedEdge() {
		connotationVal = 0;
		pos = 0;
		neg = 0;
		head = null;
		tail = null;
	}
	
	public DirectedEdge(Node head, Node tail) {
		connotationVal = 0;
		pos = 0;
		neg = 0;
		this.head = head;
		this.tail = tail;
	}
	
	public double getPos() {
		return pos;
	}
	
	public double getNeg() {
		return neg;
	}
	
	public Node getHead() {
		return head;
	}
	
	public Node getTail() {
		return tail;
	}
	
	public int getFreq() {
		return pos + neg;
	}
	
	public int getConnotationVal() {
		return connotationVal;
	}
	
	public void setPos(int val) {
		pos = val;
		connotationVal = pos - neg;
	}
	
	public void incrementPos(int val) {
		pos += val;
		connotationVal = pos - neg;
	}
	
	public void setNeg(int val) {
		neg = val;
		connotationVal = pos - neg;
	}
	
	public void incrementNeg(int val) {
		neg += val;
		connotationVal = pos - neg;
	}
	
	@Override
	public String toString() {
		String s = "";
		s += "[" + pos + ", " + tail + "]";
		return s;
	}
}
