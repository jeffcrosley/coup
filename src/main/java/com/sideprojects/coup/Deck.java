package com.sideprojects.coup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
	
	// PRIVATE MEMBERS
	private List<Card> cardsInDeck;
	
	// GETS AND SETS
	public List<Card> getCardsInDeck() {
		return cardsInDeck;
	}

	// CTOR
	public Deck() {
		this.cardsInDeck = new ArrayList<Card>();
	}
	
	// PUBLIC METHODS
		// TESTED
	public void shuffle() {
		Collections.shuffle(getCardsInDeck());
	}
		// TESTED
	public Card deal() {
		return cardsInDeck.remove(0);
	}
		// TESTED
	public void buildDeck() {
		List<String> suits = new ArrayList<String>(Arrays.asList("Duke", "Captain", "Assassin", "Contessa", "Ambassador"));
		for (String suit : suits) {
			for (int i = 0; i < 3; i++) {
				getCardsInDeck().add(new Card(suit));
			}
		}
	}
	
	// STRING OVERRIDE
	@Override
	public String toString() {
		String output = "";
		for (Card card : cardsInDeck) {
			output += card.getSuit() + "\n";
		}
		return output;
		
	}
}
