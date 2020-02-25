package com.sideprojects.coup;

import java.util.List;
import java.util.Scanner;

public class CLI {
	
	static String separator = "--------------------";
	static String[] actions = new String[] {
			"(1) INCOME - Take 1 coin (can't be challenged",
			"(2) FOREIGN AID - Take 2 coins (blockable by DUKE, can't be challenged",
			"(3) TAX as DUKE - Take 3 coins",
			"(4) STEAL as CAPTAIN - Take 2 coins from another player (blockable by CAPTAIN or AMBASSADOR",
			"(5) ASSASSINATE as ASSASSIN - Pay 3 coins, choose player to lose a card (blockable by CONTESSA",
			"(6) EXCHANGE as AMBASSADOR - Take 2 cards, discard 2 cards"
	};
	
	public CLI() {}

	public static void welcomeToCoupHowManyPlayers() {
		System.out.println("Welcome to Coup!  How many are playing? (2-6 players): ");
	}
	
	public static void gameStatus(Player currentPlayer, List<Player> players) {
		System.out.println(currentPlayer.getName() + ", it's your turn.");
		System.out.println(separator + "CURRENT GAME STATUS" + separator);
		// SHOW PLAYER CARDS AND COINS
		System.out.println("Your Cards and Coins:");
		System.out.println(currentPlayer.getCard1());
		System.out.println(currentPlayer.getCard2());
		System.out.println(currentPlayer.getCoins() + " coins");
		System.out.println(separator);
		// SHOW OPPONENT CARDS
		for (Player player : players) {
			if (!player.isTurn()) {
				System.out.println(player.getName());
				if (player.getCard1().isFaceDown()) {
					System.out.println("UNKNOWN");
				} else {
					System.out.println(player.getCard1());
				}
				if (player.getCard2().isFaceDown()) {
					System.out.println("UNKNOWN");
				} else {
					System.out.println(player.getCard2());
				}
				System.out.println(player.getCoins() + " coins");
				System.out.println(separator);
			}			
		}
		
	}

	public static void availableActions() {
		for (int i = 0; i < actions.length; i++) {
			System.out.println(actions[i]);
		}
		System.out.println(separator);
	}

	public static Player getTarget(List<Player> players, Scanner input) {
		Player targetPlayer = null;
		
		System.out.println("Select your target:");
		for (Player player : players) {
			if (player.isAlive() && !player.isTurn()) {				
				System.out.println(player);				
			}		
		}	
		System.out.println(separator);
		
		String target = input.nextLine();
		
		for (Player player : players) {
			if (target.equals(String.valueOf(players.indexOf(player) + 1))) {
				targetPlayer = player;
			}
		}
					
		return targetPlayer;
	}
	
	public static Player solicitChallenges(Player currentPlayer, String currentAction, List<Player> players, Scanner input) {
		Player challengingPlayer = null;
		System.out.println(currentPlayer + " has chosen " + currentAction);
		for (Player player : players) {
			if (player.isAlive() && !player.isTurn()) {
				System.out.println(player + ", do you challenge? (Y/N)");
				String challenge = input.nextLine();
				if (challenge.equals("Y")) {
					challengingPlayer = player;
				}
			}			
		}		
		return challengingPlayer;
	}


}