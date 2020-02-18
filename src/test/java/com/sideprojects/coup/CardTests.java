package com.sideprojects.coup;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CardTests {

	Card card = new Card("Duke");
	
	@Test
	public void flipOver() {
		Assert.assertTrue("Card should be face down by default", card.isFaceDown());
		card.flipOver();
		Assert.assertFalse("Card should be face up after flipping", card.isFaceDown());
	}
}
