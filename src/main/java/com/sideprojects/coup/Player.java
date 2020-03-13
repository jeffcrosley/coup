package com.sideprojects.coup;

import java.util.Scanner;

public class Player {
	 
	// PRIVATE MEMBERS
	private String name;
	private int coins;
	private Card card1;
	private Card card2;
	private boolean isTurn;
	private boolean isAlive;

	// GETS AND SETS
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public Card getCard1() {
		return card1;
	}

	public void setCard1(Card card1) {
		this.card1 = card1;
	}

	public Card getCard2() {
		return card2;
	}

	public void setCard2(Card card2) {
		this.card2 = card2;
	}

	public boolean isTurn() {
		return isTurn;
	}

	public void setTurn(boolean isTurn) {
		this.isTurn = isTurn;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	// CTOR
	public Player(String name) {
			this.name = name;
			this.isAlive = true;
			this.coins = 2;
	}	

	// PUBLIC METHODS
	
	public void loseCard(Scanner input) {
		System.out.println(getName() + " has been hit!  Pick a card to lose:");

		System.out.println(CLI.separator);
		
		if (getCard1().isFaceDown()) {
			System.out.println("(1) " + getCard1());
		}
		
		if (getCard2().isFaceDown()) {
			System.out.println("(2) " + getCard2());
		}
		
		System.out.println(CLI.separator);
		
		String selectedCard = input.nextLine();
		
		if (selectedCard.equals("1")) {
			System.out.println(getName() + " has revealed " + getCard1() + "!");
			getCard1().flipOver();
		}
		
		if (selectedCard.equals("2")) {
			System.out.println(getName() + " has revealed " + getCard2() + "!");
			getCard2().flipOver();
		}
		
		System.out.println(CLI.separator);
		
		if (!getCard1().isFaceDown() && !getCard2().isFaceDown()) {
			setAlive(false);
			System.out.println(getName() + " has been eliminated!");
			System.out.println(CLI.separator);
		}
	}

	public void takeAction(String currentAction, Player targetPlayer, Deck drawDeck, Scanner input) {
		if (currentAction.equals("1")) {
			takeIncome();
		} else if (currentAction.equals("2")) {
			takeForeignAid();
		} else if (currentAction.equals("3")) {
			tax();
		} else if (currentAction.equals("4")) {
			steal(targetPlayer);
		} else if (currentAction.equals("5")) {
			assassinate(targetPlayer, input);
		} else if (currentAction.equals("6")) {
			exchangeCards(getCardsForExchange(drawDeck));
		} else if (currentAction.equals("7")) {
			coup(targetPlayer, input);
		}
	}
	
	// PRIVATE METHODS
	void takeIncome() {
		setCoins(getCoins() + 1);
	}
	
	void takeForeignAid() {
		setCoins(getCoins() + 2);
	}
	
	void tax() {
		setCoins(getCoins() + 3);
	}
	
	void steal(Player target) {
		this.setCoins(this.getCoins() + 2);
		target.setCoins(target.getCoins() - 2);
	}
	
	private void assassinate(Player target, Scanner input) {
		setCoins(getCoins() - 3);
		target.loseCard(input);
	}
	
	private void coup(Player target, Scanner input) {
		setCoins(getCoins() - 7);
		target.loseCard(input);
	}	
	
	Card[] getCardsForExchange(Deck deck) {
		Card[] output = null;
		
		if (getCard1().isFaceDown() && getCard2().isFaceDown()) {
			output = new Card[4];
		} else {
			output = new Card[3];
		}
		
		if (getCard1().isFaceDown()) {
			output[0] = getCard1();
			if (getCard2().isFaceDown() ) {
				output[1] = getCard2();
			}
		} else {
			output[0] = getCard2();
		}
		
		output[output.length - 2] = deck.deal();
		output[output.length - 1] = deck.deal();
		
		return output;
	}
	
	// TODO WRITE THIS METHOD
	private void exchangeCards(Card[] cardsForExchange) {
		
	}
	
	// OVERRIDES
	@Override
	public String toString() {
		return this.getName();
	}

}
