package com.sideprojects.coup;

import java.util.List;
import java.util.Scanner;

public class CLI {
	
	// PUBLIC VARIABLES
	static String separator = "--------------------";
	static String[] actions = new String[] {
			"(1) INCOME - Take 1 coin (can't be challenged)",
			"(2) FOREIGN AID - Take 2 coins (blockable by DUKE, can't be challenged)",
			"(3) TAX as DUKE - Take 3 coins",
			"(4) STEAL as CAPTAIN - Take 2 coins from another player (blockable by CAPTAIN or AMBASSADOR)",
			"(5) ASSASSINATE as ASSASSIN - Pay 3 coins, choose player to lose a card (blockable by CONTESSA)",
			"(6) EXCHANGE as AMBASSADOR - Take 2 cards, discard 2 cards",
			"(7) COUP - Pay 7 coins, choose player to lose a card (unblockable, can't be challenged)"
	};
	
	// CTR
	public CLI() {}

	// PUBLIC METHODS
	public static void welcomeToCoupHowManyPlayers() {
		System.out.println("Welcome to Coup!  How many are playing? (2-6 players): ");
	}
	
	public static void gameStatus(Player currentPlayer, List<Player> players) {
		System.out.println(separator + "CURRENT GAME STATUS" + separator);
		System.out.println(currentPlayer.getName() + ", it's your turn.");
		// SHOW PLAYER CARDS AND COINS
		System.out.println("Your Cards and Coins:");
		String card1Status = "";
		if (!currentPlayer.getCard1().isFaceDown()) {
			card1Status = " (REVEALED)";
		}
		String card2Status = "";
		if (!currentPlayer.getCard2().isFaceDown()) {
			card2Status = " (REVEALED)";
		}
		System.out.println(currentPlayer.getCard1() + card1Status);
		System.out.println(currentPlayer.getCard2() + card2Status);
		System.out.println(currentPlayer.getCoins() + " coins");
		System.out.println(separator);
		// SHOW OPPONENT CARDS
		for (Player player : players) {
			if (!player.isTurn() && player.isAlive()) {
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

	public static Player getTarget(List<Player> players, String currentAction, Scanner input) {
		Player targetPlayer = null;
		
		if (currentAction.equals("4") || currentAction.equals("5") || currentAction.equals("7")) {
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
		}		
					
		return targetPlayer;
	}
	
	public static Player solicitChallenges(Player currentPlayer, String currentAction, List<Player> players, Scanner input) {
		Player challengingPlayer = null;
		System.out.println(currentPlayer + " has chosen " + actions[Integer.parseInt(currentAction) - 1]);

		for (Player player : players) {
			if (player.isAlive() && !player.isTurn()) {
				System.out.println(separator);
				System.out.println(player + ", do you challenge? (Y/N)");
				System.out.println(separator);
				String challenge = input.nextLine();
				if (challenge.toUpperCase().equals("Y")) {
					challengingPlayer = player;
					break;
				}
			}			
		}	
			
		return challengingPlayer;
	}

	public static void resolveActionChallenge(Player currentPlayer, Player challengingPlayer, String currentAction, Deck drawDeck, Deck discardDeck, Scanner input) {
		Player losingPlayer = null;
		
		if (identifyAssertedActionCard(currentAction).equals(currentPlayer.getCard1().getSuit()) || identifyAssertedActionCard(currentAction).equals(currentPlayer.getCard2().getSuit())) {
			losingPlayer = challengingPlayer;
			System.out.println(currentPlayer + " has drawn a new card.");
			Card discardedCard = null;
			if (identifyAssertedActionCard(currentAction).equals(currentPlayer.getCard1().getSuit())) {
				System.out.println("Challenge failed!");
				discardDeck.getCardsInDeck().add(discardedCard);
				currentPlayer.setCard1(drawDeck.deal());
			} else {
				System.out.println("Challenge failed!");
				discardDeck.getCardsInDeck().add(discardedCard);
				currentPlayer.setCard2(drawDeck.deal());
			}
			
			
		} else {
			System.out.println("Challenge successful!");
			losingPlayer = currentPlayer;
		}
		
		losingPlayer.loseCard(input);
	}

	public static Player solicitBlocks(Player currentPlayer, String currentAction, List<Player> players, Scanner input) {
		Player blockChallengingPlayer = null;
		System.out.println(currentPlayer + " has chosen " + actions[Integer.parseInt(currentAction) - 1]);
		
		for (Player player : players) {
			if (player.isAlive() && !player.isTurn()) {
				System.out.println(separator);
				System.out.println(player + ", do you block? (Y/N)");
				System.out.println(separator);
				String block = input.nextLine();
				if (block.toUpperCase().equals("Y")) {
					blockChallengingPlayer = player;
					break;
				}
			}			
		}	
			
		return blockChallengingPlayer;
	}
	
	public static String determineBlockingAction(String currentAction, Scanner input) {
		String blockingAction = "";
		
		if (currentAction.equals("2")) {
			blockingAction = "DUKE";
		} else if (currentAction.equals("4")) {
			System.out.println("Block as (1) CAPTAIN or (2) AMBASSADOR?");
			String selection = input.nextLine();
			if (selection.equals("CAPTAIN")) {
				blockingAction = "CAPTAIN";
			} else {
				blockingAction = "AMBASSADOR";
			}
			
		} else if (currentAction.equals("5") ) {
			blockingAction = "CONTESSA";
		}
		
		return blockingAction;
	}
	
	public static Player solicitBlockChallenges(Player blockingPlayer, String blockingAction, List<Player> players, Scanner input) {
		Player blockChallengingPlayer = null;
		System.out.println(blockingPlayer + " has chosen to block as " + blockingAction);

		for (Player player : players) {
			if (player.isAlive() && !player.getName().equals(blockingPlayer.getName())) {
				System.out.println(separator);
				System.out.println(player + ", do you challenge? (Y/N)");
				System.out.println(separator);
				String challenge = input.nextLine();
				if (challenge.toUpperCase().equals("Y")) {
					blockChallengingPlayer = player;
					break;
				}
			}			
		}	
			
		return blockChallengingPlayer;
	}
	
	// PRIVATE METHODS
	private static String identifyAssertedActionCard(String currentAction) {
		String assertedCard = "";
		
		if (currentAction.equals("3")) {
			assertedCard = "DUKE";
		} else if (currentAction.equals("4")) {
			assertedCard = "CAPTAIN";
		} else if (currentAction.equals("5")) {
			assertedCard = "ASSASSIN";
		} else if (currentAction.equals("6")) {
			assertedCard = "AMBASSADOR";
		}
		
		return assertedCard;
	}
}