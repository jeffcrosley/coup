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
		CLI.welcomeToCoupHowManyPlayers();
		int numberOfPlayers = Integer.parseInt(input.nextLine());
		List<Player> players = createPlayers(numberOfPlayers);
		initialDeal(drawDeck, players);
		
		// SET PLAYER 1 AS ACTIVE
		players.get(0).setTurn(true);
		
		// MAIN GAME LOOP
		boolean exit = false;
		do {
			// SET CURRENT PLAYER
			Player currentPlayer = setCurrentPlayer(players);
			// DISPLAY GAME STATUS AND AVAILABLE ACTIONS
			CLI.gameStatus(currentPlayer, players);
			CLI.availableActions();
			// GET ACTION AND TARGET
			String currentAction = input.nextLine();
			// TODO GET TARGET
			Player targetPlayer = CLI.getTarget(players, input);
			System.out.println("Target: " + targetPlayer);
			// SOLICIT CHALLENGES
			// TODO FIX THIS TO APPLY ONLY TO RELEVANT ACTIONS
			Player challengingPlayer = CLI.solicitChallenges(currentPlayer, currentAction, players, input);
			System.out.println("Challenging Player: " + challengingPlayer);
			// TODO RESOLVE CHALLENGES
			
			// TODO SOLICIT BLOCK
			
			// TODO SOLICIT CHALLENGES TO BLOCK
			
			// TODO RESOLVE CHALLENGES
			
			// TODO RESOLVE ACTION/BLOCK
			
			// TODO DISPLAY ANY PLAYER ELIMINATIONS
			
			// CHECK FOR GAME END
			exit = checkForGameEnd(players);
			// TODO ROTATE PLAYER
			
		} while (exit);//exit == false);
		
		// TODO CHECK FOR WINNING PLAYER AND DISPLAY
		
		System.out.println("Game over!");
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
	
	public static Player setCurrentPlayer(List<Player> players) {
		Player currentPlayer = null;
		for (Player player : players) {
			if (player.isTurn()) {
				currentPlayer = player;
			}
		}
		return currentPlayer;
	}
	
	public static boolean checkForGameEnd(List<Player> players) {
		boolean exit;
		int alivePlayers = 0;
		for (Player player : players) {
			if (player.isAlive()) {
				alivePlayers++;
			}
		}
		if (alivePlayers > 1) {
			exit = false;
		} else {
			exit = true;
		}
		return exit;
	}
}
