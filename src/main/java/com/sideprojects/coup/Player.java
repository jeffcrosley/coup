package com.sideprojects.coup;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
	 
	// PRIVATE MEMBERS
	private String name;
	private int coins;
	private Card card1;
	private Card card2;
	private boolean isTurn;
	private String currentAction;
	private boolean isAlive;

	// GETS AND SETS
	public String getName() {
		return name;
	}
	
	public int getCoins() {
		return coins;
	}

	public Card getCard1() {
		return card1;
	}
	
	public Card getCard2() {
		return card2;
	}
	
	public boolean isTurn() {
		return isTurn;
	}
	
	public String getCurrentAction() {
		return currentAction;
	}	
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void setCoins(int coins) {
		this.coins = coins;
	}
	
	public void setCard1(Card card1) {
		this.card1 = card1;
	}

	public void setCard2(Card card2) {
		this.card2 = card2;
	}
		
	public void setTurn(boolean isTurn) {
		this.isTurn = isTurn;
	}

	public void setCurrentAction(String currentAction) {
		this.currentAction = currentAction;
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
		// TESTED
	public void takeIncome() {
		this.setCoins(this.getCoins() + 1);
	}
		// TESTED
	public void takeForeignAid() {
		this.setCoins(this.getCoins() + 2);
	}
		// TESTED
	public void taxAsDuke() {
		this.setCoins(this.getCoins() + 3);
	}
		// TESTED
	public void stealAsCaptain(Player player) {
		this.setCoins(this.getCoins() + 2);
		if (player.getCoins() <= 2) {
			player.setCoins(0);
		} else {
			player.setCoins(player.getCoins() - 2);
		}
	}
	
	public void killAsAssassin() {
		// TODO CREATE ASSASSIN METHOD
	}
	
	public void blockAssassinationAsContessa() {
		// TODO CREATE CONTESSA METHOD
	}
	
	public List<Card> determineAvailableCardsForExchange(Deck deck) {
		List<Card> availableCards = new ArrayList<Card>();
		
		if (getCard1().isFaceDown()) {
			availableCards.add(getCard1());
		}
		
		if (getCard2().isFaceDown()) {
			availableCards.add(getCard2());
		}
		
		availableCards.add(deck.deal());
		availableCards.add(deck.deal());
		
		return availableCards;
	}
	
	
	public void coup(Player player) {
		// TODO CREATE COUP METHOD
	}
		
	
	public void challenge() {
		// TODO CREATE CHALLENGE METHOD
	}
	
	@Override
	public String toString() {
		return this.getName();
	}

}
