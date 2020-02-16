package com.sideprojects.coup;

public class Player {
	 
	private String name;
	private int coins;
	private Card card1;
	private Card card2;
	private boolean isTurn;
	private boolean isAlive;

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
	
	public void setCard1(Card card1) {
		this.card1 = card1;
	}

	public void setCard2(Card card2) {
		this.card2 = card2;
	}
	
	public void setCoins(int coins) {
		this.coins = coins;
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
	
	public void takeIncome() {
		this.setCoins(this.getCoins() + 1);
	}
	
	public void takeForeignAid() {
		this.setCoins(this.getCoins() + 2);
	}
	
	public void taxAsDuke() {
		this.setCoins(this.getCoins() + 3);
	}
	
	public void stealAsCaptain(Player player) {
		this.setCoins(this.getCoins() + 2);
		if (player.getCoins() <= 2) {
			player.setCoins(0);
		} else {
			player.setCoins(player.getCoins() - 2);
		}
	}
	
	public void killAsAssassin(Card card) {
		card.flipOver();
	}
	
	public void blockAssassinationAsContessa() {
		
	}
	
	public void exchangeCardsAsAmbassador() {
		
	}
	
	public void coup(Player player) {
		
	}
	
	// CTOR
	public Player(String name) {
		this.name = name;
		this.isAlive = true;
		this.coins = 2;
	}
	
	@Override
	public String toString() {
		return this.getName();
	}

}
