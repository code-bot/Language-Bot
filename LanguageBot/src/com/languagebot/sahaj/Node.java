package com.languagebot.sahaj;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private String word;
	private int generalCon;
	private List<DirectedEdge> edges;
	private boolean visited;
	
	public Node() {
		word = "";
		generalCon = 0;
		visited = false;
		edges = new ArrayList<DirectedEdge>();
	}
	
	public Node(String msg) {
		word = msg;
		generalCon = 0;
		visited = false;
		edges = new ArrayList<DirectedEdge>();
	}
	
	public void setGeneralCon(int val) {
		generalCon = val;
	}
	
	public int getGeneralCon() {
		return generalCon;
	}
	
	public String getWord() {
		return word;
	}
	
	public void setWord(String msg) {
		word = msg;
	}
	
	public void setVisited(boolean b) {
		visited = b;
	}
	
	public boolean getVisited() {
		return visited;
	}
	
	public void addEdge(DirectedEdge e) {
		edges.add(e);
	}
	
	public List<DirectedEdge> getEdges() {
		return edges;
	}
	
	public DirectedEdge connectedTo(String word) {
		if(!edges.isEmpty()) {
			for(DirectedEdge e: edges) {
				if(e.getTail().getWord().equals(word)) {
					return e;
				}
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return word;
	}
}
