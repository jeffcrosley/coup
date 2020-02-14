package com.sideprojects.coup;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DeckTests {

	Deck deck = new Deck();
	
	@Test
	public void deal() {
		Card firstCard = deck.deal();
		firstCard.flipOver();
		Assert.assertEquals("Deck should decrease", 14, deck.getCardsInDeck().size());
		Assert.assertEquals("First card should be Duke", "Duke", firstCard.getSuit());
	}
}
