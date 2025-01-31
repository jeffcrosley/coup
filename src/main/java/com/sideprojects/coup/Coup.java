package com.sideprojects.coup;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Coup {

	public Coup() {}

	public static void main(String[] args) {
		// CREATE SCANNERS/RESOURCES
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
			Player targetPlayer = CLI.getTarget(players, currentAction, input);
			boolean actionActive = true;
			
			// SOLICIT CHALLENGES
			Player challengingPlayer = null;
			if (currentAction.equals("3") || currentAction.equals("4") || currentAction.equals("5") || currentAction.equals("6")) {
				challengingPlayer = CLI.solicitChallenges(currentPlayer, currentAction, players, input);
				System.out.println(CLI.separator);
			}			
			
			// RESOLVE ACTION CHALLENGES
			if (challengingPlayer != null) {
				actionActive = CLI.resolveChallenge(currentPlayer, challengingPlayer, currentAction, drawDeck, discardDeck, input);
			}
			
			// SOLICIT BLOCK
			Player blockingPlayer = null;
			boolean blockActive = true;
			if ((currentAction.equals("2") || currentAction.equals("4") || currentAction.contentEquals("5")) && actionActive) {
				blockingPlayer = CLI.solicitBlock(currentPlayer, currentAction, targetPlayer, input);
			}
			
			// SOLICIT CHALLENGES TO BLOCK
			Player blockChallengingPlayer = null;
			String blockingAction = "";
			if (blockingPlayer != null) {
				blockingAction = CLI.determineBlockingAction(currentAction, input);
				blockChallengingPlayer = CLI.solicitBlockChallenges(blockingPlayer, blockingAction, players, input);
			}
			
			// RESOLVE BLOCK CHALLENGES
			// TODO THIS DIDN'T WORK WHEN A DUKE BLOCKING FOREIGN AID WAS CHALLENGED (CHALLENGE SHOULD HAVE FAILED)
			if (blockChallengingPlayer != null) {
				blockActive = CLI.resolveChallenge(blockingPlayer, blockChallengingPlayer, blockingAction, drawDeck, discardDeck, input);
			}
			
			// RESOLVE ACTION/BLOCK IF STILL RELEVANT
			// TODO FIX AMBASSADOR METHOD
			if (currentPlayer.isAlive() && actionActive && !blockActive && (targetPlayer != null && targetPlayer.isAlive())) {
				currentPlayer.takeAction(currentAction, targetPlayer, drawDeck, input);
			} else {
				// DO NOTHING
			}
			
			// CHECK FOR GAME END
			exit = checkForGameEnd(players);
			
			// ROTATE PLAYER
			rotatePlayers(players, currentPlayer);
			
		} while (exit == false);
		
		// ANNOUNCE THE WINNER
		announceWinner(players);
		
		// CLOSE INPUT SCANNER
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

	public static void rotatePlayers(List<Player> players, Player currentPlayer) {
		players.get(0).setTurn(true);

		int currentPlayerIndex = players.indexOf(currentPlayer);
		
		for (Player player : players) {
			if (player.isAlive() && players.indexOf(player) > currentPlayerIndex) {
				players.get(0).setTurn(false);
				player.setTurn(true);
				break;
			}			
		}
		
		currentPlayer.setTurn(false);	
	}

	public static void announceWinner(List<Player> players) {
		for (Player player : players) {
			if (player.isAlive()) {
				System.out.println(player + " is the winner!");
			}
		}
	}
}
