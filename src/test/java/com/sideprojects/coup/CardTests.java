package com.sideprojects.coup;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CardTests {

	Card card1 = new Card("Duke");
	Card card2 = new Card("Contessa");
	Deck deck = new Deck();
	
	@Test
	public void flip_card_over() {
		Assert.assertTrue("Card should be face down by default", card1.isFaceDown());
		card1.flipOver();
		Assert.assertFalse("Card should be face up after flipping", card1.isFaceDown());
	}
	
	@Test
	public void add_card_to_discard_pile() {
		card1.discard(deck);
		card2.discard(deck);
		Assert.assertEquals("Card should be added to discard deck", new Card("Duke"), deck.getCardsInDeck().get(0));
		Assert.assertEquals("Card should be added to discard deck", new Card("Contessa"), deck.getCardsInDeck().get(1));
	}
}
