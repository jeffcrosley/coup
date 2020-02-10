package com.sideprojects.coup;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	
	public List<Card> listOfCards = new ArrayList<Card>();
	
	public Deck() {
		listOfCards = this.BuildDeck();
	}
	
	public List<Card> BuildDeck() {
		String[] suits = {"Duke", "Captain", "Assassin", "Ambassador", "Contessa" };
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < suits.length; j++) {
				listOfCards.add(new Card(suits[j]));
			}	
		}
		
		Collections.shuffle(listOfCards);
				
		return listOfCards;
	}
	
	@Override
	public String toString() {
		String myselfAsString = "Curret Deck = " + listOfCards;
		return myselfAsString;
	}
}
