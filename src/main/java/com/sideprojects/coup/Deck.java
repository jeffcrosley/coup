package com.sideprojects.coup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {

	private List<Card> cardsInDeck;
	
	public List<Card> getCardsInDeck() {
		return cardsInDeck;
	}

	public Deck() {
		this.cardsInDeck = new ArrayList<Card>();
		
		List<String> suits = new ArrayList<String>(Arrays.asList("Duke", "Captain", "Assassin", "Contessa", "Ambassador"));
		
		for (String suit : suits) {
			for (int i = 0; i < 3; i++) {
				cardsInDeck.add(new Card(suit));
			}
		}
	}
	
	public void shuffle() {
		Collections.shuffle(cardsInDeck);
	}
	public Card deal() {
		return cardsInDeck.remove(0);
	}
	
	@Override
	public String toString() {
		String output = "";
		for (Card card : cardsInDeck) {
			output += card.getSuit() + "\n";
		}
		return output;
		
	}

}
