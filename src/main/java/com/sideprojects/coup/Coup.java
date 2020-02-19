package com.sideprojects.coup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Coup {
	/*
	public static void main(String[] args) {
	
		// CREATE INPUT SCANNER
		Scanner input = new Scanner(System.in);		
		// CREATE THE DECK AND DISCARD
		Deck deck = buildDeck();
		Deck discard = new Deck();		
		// CREATE PLAYERS
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
			Player target = identifyTarget(action, players, input);
						
			// GIVE OTHER PLAYERS OPPORTUNITY TO CHALLENGE
			Player challengingPlayer = askForChallenge(players, input);
			// ANNOUNCE AND ADJUDICATE CHALLENGE
			announceChallenge(challengingPlayer);
			adjudicateChallenge(activePlayer, challengingPlayer, action, input);
			
			// IF STILL APPLICABLE, TAKE PLAYER ACTION
			// TODO MAKE THIS CONDITIONAL ON WHETHER THE ACTIVE PLAYER IS DEAD OR THE ACTION WAS SUCCESSFULLY CHALLENGED
			// TODO ADD A MECHANISM FOR GETTING NEW CARDS AFTER CHALLENGES AND RESHUFFLING THE DECK WHEN IT'S EMPTY
			// TODO GET THIS INTO AN EXTERNAL FUNCTION OR SOMETHING(?)
			if (action.equals("1")) {
				// TAKE INCOME
				activePlayer.setCoins(activePlayer.getCoins() + 1);
			} else if (action.equals("2")) {
				// TAKE FOREIGN AID
				activePlayer.setCoins(activePlayer.getCoins() + 2);
				// TODO ADD AN OPPORTUNITY TO BLOCK AS DUKE
				// TODO ADD AN OPPORTUNITY TO CHALLENGE
			} else if (action.equals("3")) {
				activePlayer.taxAsDuke();
				// TODO ADD AN OPPORTUNITY TO CHALLENGE
			} else if (action.equals("4")) {
				// STEAL
				activePlayer.stealAsCaptain(target);
				// TODO ADD AN OPPORTUNITY TO BLOCK AS CAPTAIN				
				// TODO ADD AN OPPORTUNITY TO CHALLENGE
			} else if (action.equals("5")) {
				// ASSASSINATE
				// TODO ADD AN OPPORTUNITY TO BLOCK AS CONTESSA
				activePlayer.killAsAssassin();
				killCard(target, input);
				// TODO ADD AN OPPORTUNITY TO CHALLENGE
				announceIfDead(target);				
			} else if (action.equals("6")) {
				// EXCHANGE
				// TODO EXCHANGE
			} else if (action.equals("7")) {
				// COUP
				// TODO USE PLAYER METHOD
				// TODO PAY FOR THE COUP
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


	public static void adjudicateChallenge(Player activePlayer, Player challengingPlayer, String action, Scanner input) {
		if (challengingPlayer != null) {
			
			String nameOfCardOfAction = "";
			
			if (action.equals("3")) {
				nameOfCardOfAction = "Duke";
			} else if (action.equals("4")) {
				nameOfCardOfAction = "Captain";
			} else if (action.equals("5")) {
				nameOfCardOfAction = "Assassin";
			} else if (action.equals("4")) {
				nameOfCardOfAction = "Captain";
			} else if (action.contentEquals("5")) {
				nameOfCardOfAction = "Ambassador";
			}
			
			if ((activePlayer.getCard1().getSuit().equals(nameOfCardOfAction) && 
					activePlayer.getCard1().isFaceDown()) || 
					(activePlayer.getCard2().getSuit().equals(nameOfCardOfAction) &&
					activePlayer.getCard2().isFaceDown())) {
				killCard(challengingPlayer, input);
			} else {
				killCard(activePlayer, input);
			}
		}
	}
	
	public static void announceChallenge(Player challengingPlayer) {
		if (challengingPlayer != null) {
			System.out.println(challengingPlayer.getName() + " has challenged!");
		} else {
			System.out.println("No challenges");
		}
	}
	
	public static void announceIfDead(Player player) {
		if (!player.getCard1().isFaceDown() && !player.getCard2().isFaceDown()) {
			System.out.println(player + " has been killed!");
			player.setAlive(false);
		}
	};

	public static Player askForChallenge(List<Player> players, Scanner input) {
		Player challengingPlayer = null;
		for (Player player : players) {
				if (player.isTurn() == false) {
				System.out.println(player.getName() + ", do you challenge? (y or n)");
				String answer = input.nextLine();
				if (answer.equals("y")) {
					challengingPlayer = player;
				}
			}
		} 
		return challengingPlayer;
	}
	
	public static Deck buildDeck() {
		Deck deck = new Deck();
		
		List<String> suits = new ArrayList<String>(Arrays.asList("Duke", "Captain", "Assassin", "Contessa", "Ambassador"));
		for (String suit : suits) {
			for (int i = 0; i < 3; i++) {
				deck.getCardsInDeck().add(new Card(suit));
			}
		}
		deck.shuffle();
		return deck;
	}

	public static boolean checkGameOver(List<Player> players, Player player) {
		if (player.getCard1().isFaceDown() && player.getCard2().isFaceDown()) {
			return false;
		} else if (countAlivePlayers(players) == 0) {
			return false;
		} else {
			return true;
		}
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
				
				System.out.println("Coins: " + player.getCoins() + "\n");
			}
		}
	}

	public static void displayAvailableActions(Player player) {
		System.out.println("**********AVAILABLE ACTIONS**********");
		if (player.getCoins() >= 10) {
			System.out.println("You have 10 or more coins and must Coup!");
			System.out.println("7. Coup (-7 coins, can't be challenged)");
		} else {				
			System.out.println("1. Take Income (+1 coin)");
			System.out.println("2. Take Foreign Aid (+2 coins, blockable by Duke)");
			System.out.println("3. Tax as Duke (+3 coins)");
			System.out.println("4. Steal as Captain (+2 coins, blockable by Captain or Ambassador)");
			System.out.println("5. Assassinate as Assassin (-3 coins, blockable by Contessa");
			System.out.println("6. Exchange Cards as Ambassador");
			System.out.println("7. Coup (-7 coins, can't be challenged)");
		}
		System.out.println("\nWhat action would you like to take? (input number)");
	}
	
	public static Player identifyActivePlayer(List<Player> players) {
		for (Player player : players) {
			if (player.isTurn()) {
				return player;
			}
		}
		return null;
	}

	public static Player identifyTarget(String action, List<Player> players, Scanner input) {
		if (action.equals("4") || action.equals("5") || action.equals("7")) {
			System.out.println("Who is your target? (input number of target player)");
			Player target = players.get(Integer.parseInt(input.nextLine()));
			return target;
		} else {
			return null;
		}
	}

	public static void killCard(Player target, Scanner input) {
		System.out.println(target + ", you have been hit! Pick a card to lose: ");
		
		if (target.getCard1().isFaceDown()) {
			System.out.println("1: " + target.getCard1());
		}
		
		if (target.getCard2().isFaceDown()) {
			System.out.println("2: " + target.getCard2());
		}
		
		String deadCard = input.nextLine();
		
		if (deadCard.equals("1")) {
			target.getCard1().flipOver();
		} else if (deadCard.equals("2")) {
			target.getCard2().flipOver();
		}
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

	public static void setPlayerAsActive(Player player) {
		player.setTurn(true);
	}
	

*/
	
}
