package com.sideprojects.coup;

public class Card {
	
	private String suit;
	private boolean isFaceDown;
	
	public String getSuit() {
		if (this.isFaceDown()) {
			return "Unknown";
		} else {
			return suit;	
		}
	}
	
	public boolean isFaceDown() {
		return isFaceDown;
	}
	
	public Card(String suit) {
		this.suit = suit;
		this.isFaceDown = true;
	}
	
	public void flipOver() {
		this.isFaceDown = !this.isFaceDown;
	}
	
	@Override
	public String toString() {
		if (isFaceDown) {
			return "Unknown";
		} else {
			return suit;
		}
	}
}
