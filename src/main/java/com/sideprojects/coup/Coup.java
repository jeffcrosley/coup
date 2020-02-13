package com.sideprojects.coup;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Coup {

	public static void main(String[] args) {
		
		// CREATE THE DECK
		Deck deck = new Deck();
		
		// SHUFFLE THE DECK
		deck.shuffle();
		
		// DETERMINE NUMBER OF PLAYERS AND INSTANTIATE WITH TOP CARDS FROM DECK
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to Coup!  How many are playing? (2-4 players): ");
		int numberOfPlayers = Integer.parseInt(input.nextLine());
		
		List<Player> players = new ArrayList<Player>();
		for (int i = 0; i < numberOfPlayers; i++) {
			Card card1 = deck.deal();
			Card card2 = deck.deal();			
			players.add(new Player("player", card1, card2));
		}

		input.close();
	}
}
