package com.sideprojects.coup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DeckTests {

	Deck deck = new Deck();
	Deck deck2 = new Deck();
	
	@Test
	public void buildDeck() {
		deck.buildDeck();
		
		deck2.getCardsInDeck().add(new Card("Duke"));
		deck2.getCardsInDeck().add(new Card("Duke"));
		deck2.getCardsInDeck().add(new Card("Duke"));
		deck2.getCardsInDeck().add(new Card("Captain"));
		deck2.getCardsInDeck().add(new Card("Captain"));
		deck2.getCardsInDeck().add(new Card("Captain"));
		deck2.getCardsInDeck().add(new Card("Assassin"));
		deck2.getCardsInDeck().add(new Card("Assassin"));
		deck2.getCardsInDeck().add(new Card("Assassin"));
		deck2.getCardsInDeck().add(new Card("Contessa"));
		deck2.getCardsInDeck().add(new Card("Contessa"));
		deck2.getCardsInDeck().add(new Card("Contessa"));
		deck2.getCardsInDeck().add(new Card("Ambassador"));
		deck2.getCardsInDeck().add(new Card("Ambassador"));
		deck2.getCardsInDeck().add(new Card("Ambassador"));

		for (int i = 0; i < deck.getCardsInDeck().size(); i++) {
			Assert.assertEquals("Deck does not build correctly", deck.getCardsInDeck().get(i).getSuit(), deck2.getCardsInDeck().get(i).getSuit()); 
		}
}
	
	@Test
	public void shuffle() {
		deck.buildDeck();
		deck2.buildDeck();
		
		deck2.shuffle();
		Assert.assertFalse("Shuffled deck should not be the same as unshuffled deck", deck.getCardsInDeck().equals(deck2.getCardsInDeck()));
	}
	
	@Test
	public void deal() {
		List<String> suits = new ArrayList<String>(Arrays.asList("Duke", "Captain", "Assassin", "Contessa", "Ambassador"));
		
		for (String suit : suits) {
			for (int i = 0; i < 3; i++) {
				deck.getCardsInDeck().add(new Card(suit));
			}
		}
		
		Card firstCard = deck.deal();
		firstCard.flipOver();
		Assert.assertEquals("Deck should decrease", 14, deck.getCardsInDeck().size());
		Assert.assertEquals("First card should be Duke", "Duke", firstCard.getSuit());
	}
}
