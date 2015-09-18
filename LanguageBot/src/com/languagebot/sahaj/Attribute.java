package com.languagebot.sahaj;

public class Attribute {
	private int level;
	
	public Attribute() {
		level = 50;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void addLevel(int increment) {
		level = level + increment;
	}
}
