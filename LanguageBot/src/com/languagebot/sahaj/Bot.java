package com.languagebot.sahaj;

import java.util.Random;
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
	
	public void teach() {
		String[] baseMessages = {
								"I will feed you",
								"I will not feed you",
								"I will not not feed you",
								"I do not want to give you food",
								"I want to give you food",
								"No I will not give you food",
								"Yes I will give you food",
								};
		Random rand = new Random();
		String choice = "";
		int msgRand;
		for(int j = 0; j < 100; j++) {
			msgRand = rand.nextInt(baseMessages.length);
			String msg = baseMessages[msgRand];
			if(msgRand % 2 == 0) {
				choice = "y";
			}
			else {
				choice = "n";
			}
			listen(msg, choice);
		}
	}
	
	public void ask(String msg) {
		System.out.println("Do you want to feed the bot?");
		String choice = in.nextLine();
		listen(msg, choice);
	}
	
	public void listen(String msg, String choice) {
		int finalCV;
		boolean flag = false;
		msg = msg.toLowerCase();
		String[] words = msg.split(" ");
		finalCV = predict(words);
		if(choice.equals("y")) {
			this.feed();
		}
		else if(choice.equals("n")) {
			this.ignore();
		}
		
		Node n = vocab.contains(words[0]);
		if(n == null) {
			n = new Node(words[0]);
			vocab.addNode(n);
		}
		for(int i = 0; i < words.length-1; i++) {
			n = vocab.contains(words[i]);
			DirectedEdge e = n.connectedTo(words[i+1]);
			if(e == null) {
				Node to = vocab.contains(words[i+1]);
				if(to == null) {
					to = new Node(words[i+1]);
					vocab.addNode(to);
				}
				e = new DirectedEdge(n, to);
				n.addEdge(e);
				vocab.addEdge(e);
			}
			if(choice.equals("y")) {
				if(prediction.equals("no")) {
					if(e.getConnotationVal() < 0) {
						e.incrementPos(Math.abs(e.getConnotationVal()));
					}
					else if(e.getConnotationVal() > 0) {
						e.incrementPos(Math.abs(finalCV));
					}
					else {
						e.incrementPos(Math.abs(finalCV)/2);
					}
				}
				else {
					e.incrementPos(1);
				}
			}
			else if(choice.equals("n")) {
				if(prediction.equals("yes")) {
					if(e.getConnotationVal() > 0) {
						e.incrementNeg(Math.abs(e.getConnotationVal()));
					}
					else if(e.getConnotationVal() < 0) {
						e.incrementNeg(Math.abs(finalCV));
					}
					else {
						e.incrementNeg(Math.abs(finalCV)/2);
					}
				}
				else {
					e.incrementNeg(1);
				}
			}
		}
		/*
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
		}*/
	}
	
	private int predict(String[] input) {
		Node n = vocab.contains(input[0]);
		int total = 0;
		boolean flag;
		for(int i = 1; i < input.length; i++) {
			flag = true;
			if(n != null) {
				for(DirectedEdge e : n.getEdges()) {
					if(e.getTail().getWord().equals(input[i])) {
						total += e.getConnotationVal();
						n = e.getTail();
						flag = false;
						break;
					}
				}
			}
			if(flag) {
				n = vocab.contains(input[i]);
			}
		}
		/*
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
		}*/
		System.out.println(total);
		if(total >= 0) {
			prediction = "yes";
			System.out.println("positive");
		}
		else if(total < 0) {
			prediction = "no";
			System.out.println("negative");
		}
		return total;
	}
	
	public void brainDump() {
		System.out.println(vocab);
	}
}
