package com.sideprojects.coup;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Coup {

	public static void main(String[] args) {
		
		// CREATE INPUT SCANNER
		Scanner input = new Scanner(System.in);
		
		// CREATE THE DECK
		Deck deck = buildDeck();
		
		// GET NUMBER OF PLAYERS AND NAMES FROM USER AND CREATE PLAYERS
		List<Player> players = createPlayers(input);
				
		// DEAL TWO CARDS TO EACH PLAYER
		dealCards(players, deck);
		
		// SET PLAYER ONE AS ACTIVE PLAYER
		setPlayerAsActive(players.get(0));;
		
		// MAIN GAME LOOP
		boolean game = true;
		while (game == true) {
			
			// DETERMINE AND SET ACTIVE PLAYER
			Player activePlayer = identifyActivePlayer(players);
			
			// DISPLAY ACTIVE PLAYER CARDS AND COINS
			displayPlayerStatus(activePlayer);
			
			// DISPLAY OTHER PLAYER CARDS
			displayOpponentStatus(players);
			
			// DISPLAY AVAILABLE ACTIONS (DECLARE MANDATORY COUP IF 10+ COINS)
			displayAvailableActions(activePlayer);
						
			// ASK PLAYER FOR ACTION AND TARGET
			String action = input.nextLine();
			Player target = null;
			if (action.equals("3") || action.equals("4") || action.equals("6")) {
				System.out.println("Who is your target? (input number of target player)");
				target = players.get(Integer.parseInt(input.nextLine()));				
			}
						
			// TODO GIVE OTHER PLAYERS OPPORTUNITY TO CHALLENGE
			
			// TODO IF CHALLENGED, DETERMINE WHO WINS AND LOSER TAKES PENALTY
			
			// IF STILL APPLICABLE, TAKE PLAYER ACTION
			// TODO MAKE THIS CONDITIONAL ON WHETHER THE ACTIVE PLAYER IS DEAD OR THE ACTION WAS SUCCESSFULLY CHALLENGED
			// TODO ADD A MECHANISM FOR GETTING NEW CARDS AFTER CHALLENGES AND RESHUFFLING THE DECK WHEN IT'S EMPTY
			if (action.equals("1")) {
				// TAKE INCOME
				activePlayer.setCoins(activePlayer.getCoins() + 1);
			} else if (action.equals("2")) {
				// TAKE FOREIGN AID
				activePlayer.setCoins(activePlayer.getCoins() + 2);
				// TODO ADD AN OPPORTUNITY TO BLOCK AS DUKE
				// TODO ADD AN OPPORTUNITY TO CHALLENGE
			} else if (action.equals("3")) {
				// STEAL
				activePlayer.stealAsCaptain(target);
				// TODO ADD AN OPPORTUNITY TO BLOCK AS CAPTAIN				
				// TODO ADD AN OPPORTUNITY TO CHALLENGE
			} else if (action.equals("4")) {
				// ASSASSINATE
				killCard(target, input);
				// TODO ADD AN OPPORTUNITY TO BLOCK AS CONTESSA
				// TODO ADD AN OPPORTUNITY TO CHALLENGE
				announceIfDead(target);				
			} else if (action.equals("5")) {
				// EXCHANGE
				// TODO EXCHANGE
			} else if (action.equals("6")) {
				// COUP
				killCard(target, input);
				announceIfDead(target);
			}
			
			// DECLARE IF GAME IS OVER
			game = checkGameOver(players, activePlayer);
			
			// ROTATE ACTIVE PLAYER
			rotateActivePlayer(players);
		}
		
		input.close();
		
		// TODO DECLARE WHETHER USER WON OR LOST (MAKE SURE THIS SQUARES WITH THE "ROTATE ACTIVE PLAYER" BEHAVIOR ABOVE)
		System.out.println("Game Over!");
	}
	
	
	public static Deck buildDeck() {
		Deck deck = new Deck();
		deck.shuffle();
		return deck;
	}
	
	public static List<Player> createPlayers(Scanner input) {
		System.out.println("Welcome to Coup!  How many are playing? (2-6 players): ");
		List<Player> players = new ArrayList<Player>();
		int numberOfPlayers = Integer.parseInt(input.nextLine());		
		
		for (int i = 0; i < numberOfPlayers; i++) {
			players.add(new Player("Player " + Integer.toString(i + 1)));
		}
		
		return players;
	}

	public static void dealCards(List<Player> players, Deck deck) {
		
		for (Player player : players) {
			player.setCard1(deck.deal());
			player.setCard2(deck.deal());
		}
		
	}

	public static void setPlayerAsActive(Player player) {
		player.setTurn(true);
	}

	public static Player identifyActivePlayer(List<Player> players) {
		for (Player player : players) {
			if (player.isTurn()) {
				return player;
			}
		}
		return null;
	}

	public static void displayPlayerStatus(Player player) {
		System.out.println("\n" + player + ", it's your turn.\n"
				+ "");
		System.out.println("**********YOUR STATUS**********\"\nCard One: " 
				+ player.getCard1() + " " + player.getCard1().isFaceDown() 
				+ "\nCard Two: " + player.getCard2() + " " 
				+ player.getCard2().isFaceDown() 
				+ "\nCoins: " + player.getCoins());
	}

	public static void displayOpponentStatus(List<Player> players) {
		System.out.println("\n**********OPPONENT STATUS**********");
		for (Player player : players) {
			if (!player.isTurn() && player.isAlive()) {
				System.out.println(player);					
				
				if (player.getCard1().isFaceDown().equals("(Face Down)")) {
					System.out.println("UNKNOWN");
				} else {
					System.out.println(player.getCard1());
				}
				
				if (player.getCard2().isFaceDown().equals("(Face Down)")) {
					System.out.println("UNKNOWN\n");
				} else {
					System.out.println(player.getCard2() + "\n");
				}
				
				System.out.println("Coins: " + player.getCoins());
			}
		}
	}

	public static void displayAvailableActions(Player player) {
		System.out.println("**********AVAILABLE ACTIONS**********");
		if (player.getCoins() >= 10) {
			System.out.println("You have 10 or more coins and must Coup!");
			System.out.println("6. Coup (-7 coins, unblockable, can't be challenged)");
		} else {				
			System.out.println("1. Take Income (+1 coin, unblockable)");
			System.out.println("2. Take Foreign Aid (+2 coins, blockable by Duke)");
			System.out.println("3. Steal as Captain (+2 coins, blockable by Captain or Ambassador)");
			System.out.println("4. Assassinate as Assassin (-3 coins, blockable by Contessa");
			System.out.println("5. Exchange Cards as Ambassador (unblockable)");
			System.out.println("6. Coup (-7 coins, unblockable, can't be challenged)");
		}
		System.out.println("\nWhat action would you like to take? (input number)");
	}
	
	public static int countAlivePlayers(List<Player> players) {
		int alivePlayers = 0;
		for (Player player : players) {
			if (player.isAlive() == true) {
				alivePlayers++;
			}
		}
		return alivePlayers;
	}
	
	public static void rotateActivePlayer(List<Player> players) {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).isTurn() == true && players.get(i) != players.get(players.size() - 1)) {
				players.get(i).setTurn(false);
				players.get(i + 1).setTurn(true);
				break;
			} else if (players.get(i).isTurn() == true) {
				players.get(i).setTurn(false);
				players.get(0).setTurn(true);
			}
		}
	}

	public static boolean checkGameOver(List<Player> players, Player player) {
		if (player.getCard1().isFaceDown() == "(Face Down)" && player.getCard2().isFaceDown() == "(Face Down") {
			return false;
		} else if (countAlivePlayers(players) == 0) {
			return false;
		} else {
			return true;
		}
	}

	public static void killCard(Player target, Scanner input) {
		System.out.println(target + ", you have been hit! Pick a card to lose: ");
		
		if (target.getCard1().isFaceDown() == "(Face Down)") {
			System.out.println("1: " + target.getCard1());
		}
		
		if (target.getCard2().isFaceDown() == "(Face Down)") {
			System.out.println("2: " + target.getCard2());
		}
		
		String deadCard = input.nextLine();
		
		if (deadCard.equals("1")) {
			target.getCard1().flipOver();
		} else if (deadCard.equals("2")) {
			target.getCard2().flipOver();
		}
	}

	public static void announceIfDead(Player player) {
		if (player.getCard1().isFaceDown() == "(Face Up)" && player.getCard2().isFaceDown() == "(Face Up)") {
			System.out.println(player + " has been killed!");
			player.setAlive(false);
		}
	};



}
