package com.sideprojects.coup;

public class Player {
	 
	private String name;
	private Card card1;
	private Card card2;
	private boolean isTurn;
	
	
	public String getName() {
		return name;
	}
	
	public Card getCard1() {
		return card1;
	}
	
	public Card getCard2() {
		return card2;
	}
	
	public void setCard1(Card card1) {
		this.card1 = card1;
	}

	public void setCard2(Card card2) {
		this.card2 = card2;
	}
	
	public boolean isTurn() {
		return isTurn;
	}
	
	public Player(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		String card1 = this.card1.getSuit();
		String card2 = this.card2.getSuit();
		return this.getName() + " " + card1 + " " + card2;
	}
}
