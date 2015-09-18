package com.languagebot.sahaj;

public class DirectedEdge {
	int weight;
	Node head, tail;
	
	public DirectedEdge() {
		weight = 0;
		head = null;
		tail = null;
	}
	
	public DirectedEdge(int w, Node h, Node t) {
		weight = w;
		head = h;
		tail = t;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public Node getHead() {
		return head;
	}
	
	public Node getTail() {
		return tail;
	}
	
	public void setWeight(int w) {
		weight = w;
	}
	
	@Override
	public String toString() {
		String s = "";
		s += "[" + weight + tail + "]";
		return s;
	}
}
