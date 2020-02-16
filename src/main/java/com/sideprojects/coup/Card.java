package com.sideprojects.coup;

public class Card {
	
	private String suit;
	private boolean isFaceDown;
	
	public String getSuit() {
		return suit;
	}
	
	public String isFaceDown() {
		if (isFaceDown == true) {
			return "(Face Down)";
		} else {
			return "(Face Up)";
		}
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
		return suit;
	}
}
