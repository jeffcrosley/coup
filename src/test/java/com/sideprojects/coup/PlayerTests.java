package com.sideprojects.coup;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.sideprojects.coup.Deck;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlayerTests {

	Player player1 = new Player("Player 1");
	Player player2 = new Player("Player 2");
	Deck deck = new Deck();
	
	@Test
	public void takeIncome() {
		player1.takeIncome();
		Assert.assertEquals("Income should increase coins by 1", 3, player1.getCoins());
	}
	
	@Test
	public void takeForeignAid() {
		player1.takeForeignAid();
		Assert.assertEquals("Foreign Aid should increase coins by 2", 4, player1.getCoins());
	}
	
	@Test
	public void taxAsDuke() {
		player1.taxAsDuke();
		Assert.assertEquals("Tax should increase coins by 3", 5, player1.getCoins());
	}

	@Test
	public void stealAsCaptain() {
		player1.stealAsCaptain(player2);
		Assert.assertEquals("P1 should have 2 more coins", 4, player1.getCoins());
		Assert.assertEquals("P2 should have 2 fewer coins", 0, player2.getCoins());
	}

	@Test
	public void determineAvailableCardsForExchange() {
		
	}
}
