package com.sideprojects.coup;

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
	public void takeIncome() {
		setCoins(getCoins() + 1);
	}
	
	public void takeForeignAid() {
		setCoins(getCoins() + 2);
	}
	
	public void blockForeignAid(Player target) {
		target.setCoins(target.getCoins() - 2);
	}
	
	public void tax() {
		setCoins(getCoins() + 3);
	}
	
	public void steal(Player target) {
		this.setCoins(this.getCoins() + 2);
		target.setCoins(target.getCoins() - 2);
	}
	
	public void blockSteal(Player target) {
		this.setCoins(this.getCoins() + 2);
		target.setCoins(target.getCoins() - 2);
	}
	
	public void assassinateCard(Card card) {
		setCoins(getCoins() - 3);
		card.flipOver();
	}
	
	public void blockAssassinateCard(Card card) {
		card.flipOver();
	}
	
	public void coupCard(Card card) {
		setCoins(getCoins() - 7);
		card.flipOver();
	}
	
	public Card[] getCardsForExchange(Deck deck) {
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
	
	// OVERRIDES
	@Override
	public String toString() {
		return this.getName();
	}

}
