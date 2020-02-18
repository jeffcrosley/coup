package com.sideprojects.coup;

public class Card {
	
	// PRIVATE MEMBERS
	private String suit;
	private boolean isFaceDown;
	
	// GETS AND SETS
	public String getSuit() {
		return suit;
	}
	
	public boolean isFaceDown() {
		return isFaceDown;
	}
	
	// CTOR
	public Card(String suit) {
		this.suit = suit;
		this.isFaceDown = true;
	}
	
	// PUBLIC METHODS
		// TESTED
	public void flipOver() {
		isFaceDown = !isFaceDown;
	}
	
	@Override
	public String toString() {
		return suit;
	}
}
