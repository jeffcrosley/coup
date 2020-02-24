package com.sideprojects.coup;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Coup {

	public Coup() {}

	public static void main(String[] args) {
		// SCANNERS AND RESOURCES
		Scanner input = new Scanner(System.in);
		
		// CREATE DRAW AND DISCARD DECKS AND SHUFFLE
		Deck drawDeck = new Deck();
		Deck discardDeck = new Deck();
		drawDeck.buildDeck();
		drawDeck.shuffle();

		// DISPLAY WELCOME MESSAGE, GET NUMBER OF PLAYERS, INSTANTIATE, AND DEAL CARDS TO PLAYERS
		Display.welcomeToCoupHowManyPlayers();
		int numberOfPlayers = Integer.parseInt(input.nextLine());
		List<Player> players = createPlayers(numberOfPlayers);
		initialDeal(drawDeck, players);
		
		// SET PLAYER 1 AS ACTIVE
		players.get(1).setTurn(true);
		
		// MAIN GAME LOOP
		boolean exit = false;
		do {
			
			// TODO DISPLAY GAME STATUS
			
			// TODO GET ACTION/TARGET
			
			// TODO SOLICIT CHALLENGES
			
			// TODO RESOLVE CHALLENGES
			
			// TODO SOLICIT BLOCK
			
			// TODO SOLICIT CHALLENGES TO BLOCK
			
			// TODO RESOLVE CHALLENGES
			
			// TODO RESOLVE ACTION/BLOCK
			
			// TODO DISPLAY ANY PLAYER ELIMINATIONS
			
			// TODO CHECK FOR GAME END
			
			// TODO ROTATE PLAYER
			
		} while (exit = false);
		
		input.close();
	}
	
	public static List<Player> createPlayers(int numberOfPlayers) {
		List<Player> players = new ArrayList<Player>();
		for (int i = 0; i < numberOfPlayers; i++) {
			players.add(new Player("Player " + Integer.toString(i + 1)));
		}		
		return players;
	}

	public static void initialDeal(Deck drawDeck, List<Player> players) {
		for (Player player : players) {
			player.setCard1(drawDeck.deal());
			player.setCard2(drawDeck.deal());
		}
	}
}
