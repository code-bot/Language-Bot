package com.languagebot.sahaj;

import java.util.HashMap;
import java.util.Scanner;

public class Bot {
	private int hunger;
	private Scanner in = new Scanner(System.in);
	private String prediction = "";
	private DirectedGraph vocab;
	
	public Bot() {
		hunger = 50;
		vocab = new DirectedGraph();
	}
	
	public void feed() {
		hunger += 1;
	}
	
	public void ignore() {
		hunger -= 1;
	}
	
	public int getHunger() {
		return hunger;
	}
	
	public void listen(String msg) {
		int trust = 0;
		boolean flag = false;
		msg = msg.toLowerCase();
		String[] words = msg.split(" ");
		predict(words);
		System.out.println("Do you want to feed the bot?");
		String choice = in.nextLine();
		if(choice.equals("y")) {
			this.feed();
			if(prediction.equals("no")) {
				trust = 2;
			}
		}
		else if(choice.equals("n")) {
			this.ignore();
			if(prediction.equals("yes")) {
				trust = -2;
			}
		}
		
		for(int i = 0; i < words.length; i++) {
			Node n = vocab.contains(words[i]);
			flag = false;
			if(n == null) {
				n = new Node(words[i]);
				n.setFreq(1);
				vocab.addNode(n);
				flag = true;
			}
			if(i != words.length-1) {
				DirectedEdge e = n.connectedTo(words[i+1]);
				if(e == null) {
					Node to = vocab.contains(words[i+1]);
					flag = true;
					if(to == null) {
						to = new Node(words[i+1]);
					}
					e = new DirectedEdge(0, n, to);
					n.setFreq(n.getFreq()+1);
					n.addEdge(e);
					vocab.addNode(to);
					vocab.addEdge(e);
				}
				if(choice.equals("y")) {
					e.setWeight(e.getWeight()+1);
					if(flag) {
						e.setWeight(e.getWeight()+trust);
					}
				}
				else if(choice.equals("n")) {
					e.setWeight(e.getWeight()-1);
					if(flag) {
						e.setWeight(e.getWeight()+trust);
					}
				}
			}
		}
	}
	
	private void predict(String[] input) {
		//Check full sentence
		double total = 50;
		int connotation = 0;
		boolean flag;
		Node n = vocab.contains(input[0]);
		for(int i = 1; i < input.length; i++) {
			flag = true;
			connotation = 0;
			if(n != null) {
				for(DirectedEdge e: n.getEdges()) {
					System.out.println(e.getTail());
					if(e.getTail().getWord().equals(input[i])) {
						total += (100.0/(double)input.length)*((double)e.getWeight()/(double)n.getFreq());
						System.out.println(total);
						n = e.getTail();
						flag = false;
						break;
						//get weight
					}
					connotation += ((double)e.getWeight()/(double)n.getFreq());
					System.out.println(connotation);
				}
			}
			if(flag) {
				connotation *= (100.0/(double)input.length);
				total += connotation;
				do {
					if(i+1 < input.length){
						n = vocab.contains(input[i+1]);
						System.out.println(n);
						i++;
					}
					else break;
				}while(n == null);
			}
		}
		System.out.println(total);
		if(total >= 50.0) {
			prediction = "yes";
			System.out.println("I will get fed");
		}
		else {
			prediction = "no";
			System.out.println("I will not get fed");
		}
	}
	
	public void brainDump() {
		System.out.println(vocab);
	}
}
