package com.languagebot.sahaj;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Bot bot = new Bot();
		Scanner in = new Scanner(System.in);
		String input = "";
		bot.teach();
		do {
			System.out.println("Bot hunger level: " + bot.getHunger());
			System.out.print("Message:");
			input = in.nextLine();
			System.out.println(input);
			if(input != "quit")
			{
				bot.listen(input);
				bot.brainDump();
			}
		}while(!input.equals("quit"));
	}
}
