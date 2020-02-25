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
	public void flipOver() {
		isFaceDown = !isFaceDown;
	}
	
	public void discard(Deck deck) {
		deck.getCardsInDeck().add(this);
	}
	
	// OVERRIDES
	@Override
	public String toString() {
			return suit;
	}
	
	@Override
	public boolean equals(Object o) {
		// COMMENTS FROM ANDREW; THIS CODE ALLOWS COMPARISON OF ARRAYS OF CUSTOM OBJECTS
		
		// check if the input object is null
		if(o == null)
			return false;
		
		// check if the input object exactly matches this object
		// note that if you had subtypes or interfaces you'd need to do reflection
		if(!o.getClass().equals(this.getClass())) {
			return false;
		}
		
		// could just do a try cast for invalid cast exception, this is faster and I'm lazy
		Card c = (Card)o;
		
		return this.getSuit().equals(c.getSuit());
	}
}
