package com.sideprojects.coup;

public class Card {
	
	private String suit = this.suit;
	private boolean isFaceDown = this.isFaceDown;

	public String getSuit() {
		return this.suit;
	}
	
	public boolean isFaceDown() {
		return this.isFaceDown;
	}
	
	public Card(String suit) {
		this.suit = suit;
		this.isFaceDown = true;
	}
	
	private void flip() {
		this.isFaceDown = !this.isFaceDown;
	}
	
	@Override
	public String toString() {
		String myselfAsString = this.suit;
		return myselfAsString;
	}
	
}
