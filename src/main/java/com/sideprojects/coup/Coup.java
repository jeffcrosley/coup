package com.sideprojects.coup;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Coup {

	public static void main(String[] args) {
		
		// CREATE THE DECK
		Deck deck = buildDeck();
		
		// GET NUMBER OF PLAYERS AND NAMES FROM USER AND CREATE PLAYERS
		List<Player> players = createPlayers();
				
		// DEAL TWO CARDS TO EACH PLAYER
		dealCards(players, deck);
		
		// MAIN GAME LOOP
		boolean game = true;
		while (game = true) {
			
			
			
			
			
			
			game = false;
		}
		
		
	}
	
	
	public static Deck buildDeck() {
		Deck deck = new Deck();
		deck.shuffle();
		return deck;
	}
	
	public static List<Player> createPlayers() {
		
		List<Player> players = new ArrayList<Player>();
		
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to Coup!  How many are playing? (2-4 players): ");
		int numberOfPlayers = Integer.parseInt(input.nextLine());
		
		for (int i = 0; i < numberOfPlayers; i++) {
			System.out.println("Player " + (i + 1) + ", what is your name?:");
			String name = input.nextLine();
			players.add(new Player(name));
		}
		
		input.close();
		return players;
	}

	public static void dealCards(List<Player> players, Deck deck) {
		
		for (Player player : players) {
			player.setCard1(deck.deal());
			player.setCard2(deck.deal());
		}
		
	}




}
