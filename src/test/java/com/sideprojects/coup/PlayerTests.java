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
	public void take_income_increments_coins() {
		player1.takeIncome();
		Assert.assertEquals(3, player1.getCoins());
	}
	
	@Test
	public void take_foreign_aid_increases_coins_by_2() {
		player1.takeForeignAid();
		Assert.assertEquals(4, player1.getCoins());
	}
	
	@Test
	public void taxing_increases_coins_by_3() {
		player1.tax();
		Assert.assertEquals(5,  player1.getCoins());
	}
	
	@Test
	public void stealing_transfers_2_coins_between_players() {
		player1.steal(player2);
		Assert.assertEquals(4, player1.getCoins());
		Assert.assertEquals(0,  player2.getCoins());
	}
	
	@Test
	public void killing_flips_a_targets_card() {
		player1.setCard1(new Card("Duke"));
		player1.setCard2(new Card("Ambassador"));
		player2.setCard1(new Card("Assassin"));
		player2.setCard2(new Card("Contessa"));
		
		player1.killCard1(player2);
		player2.killCard2(player1);
		Assert.assertTrue("Card should be facedown", player1.getCard1().isFaceDown());
		Assert.assertTrue("Card should be faceup", !player1.getCard2().isFaceDown());
		Assert.assertTrue("Card should be faceup", !player2.getCard1().isFaceDown());
		Assert.assertTrue("Card should be facedown", player2.getCard2().isFaceDown());
	}
}
