package com.languagebot.sahaj;

import java.util.List;
import java.util.ArrayList;

public class DirectedGraph {
	List<Node> nodes;
	List<DirectedEdge> edges;
	
	public DirectedGraph() {
		nodes = new ArrayList<Node>();
		edges = new ArrayList<DirectedEdge>();
	}
	
	public boolean addNode(Node n) {
		if(!nodes.contains(n)){
			return nodes.add(n);
		}
		return false;
	}
	
	public void addEdge(Node from, Node to, int weight){
		DirectedEdge e = new DirectedEdge(weight,from,to);
		edges.add(e);
		from.addEdge(e);
	}
	
	public void addEdge(DirectedEdge e) {
		edges.add(e);
	}
	
	public List<Node> getNodes() {
		return nodes;
	}
	
	public List<DirectedEdge> getEdges() {
		return edges;
	}
	
	public Node contains(String word) {
		for(Node n : nodes) {
			if(n.getWord().equals(word)) {
				return n;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		String s = "";
		for(Node n: nodes) {
			s += n + ":";
			for(DirectedEdge e: n.getEdges()) {
				s += e + ", ";
			}
			s += "\n";
		}
		return s;
	}
}
