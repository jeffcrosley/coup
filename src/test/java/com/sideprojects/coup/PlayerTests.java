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
	public void blocking_foreing_aid_takes_2_coins_from_target() {
		player2.takeForeignAid();
		player1.blockForeignAid(player2);
		
		Assert.assertEquals(2, player2.getCoins());
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
	public void blocking_stealing_reverts_theft() {
		player1.steal(player2);
		player2.blockSteal(player1);
		
		Assert.assertEquals(2, player1.getCoins());
		Assert.assertEquals(2, player2.getCoins());
	}
	
	@Test
	public void assassinating_subtracts_3_coins() {
		player1.setCard1(new Card("Duke"));
		player1.setCard2(new Card("Ambassador"));
		player2.setCard1(new Card("Assassin"));
		player2.setCard2(new Card("Contessa"));
		player1.setCoins(8);
		player2.setCoins(5);
		
		player1.assassinateCard(player2.getCard1());
		player2.assassinateCard(player1.getCard2());
		
		Assert.assertEquals("Assassinating should cost 3 coins", 5, player1.getCoins());
		Assert.assertEquals("Assassinating should cost 3 coins", 2, player2.getCoins());
	}
	
	@Test
	public void assassinating_flips_a_targets_card() {
		player1.setCard1(new Card("Duke"));
		player1.setCard2(new Card("Ambassador"));
		player2.setCard1(new Card("Assassin"));
		player2.setCard2(new Card("Contessa"));
		
		player1.assassinateCard(player2.getCard1());
		player2.assassinateCard(player1.getCard2());
		Assert.assertTrue("Card should be facedown", player1.getCard1().isFaceDown());
		Assert.assertTrue("Card should be faceup", !player1.getCard2().isFaceDown());
		Assert.assertTrue("Card should be faceup", !player2.getCard1().isFaceDown());
		Assert.assertTrue("Card should be facedown", player2.getCard2().isFaceDown());
	}
	
	@Test
	public void blocking_assassination_reverts_a_flipped_card() {
		player1.setCard1(new Card("Duke"));
		player1.setCard2(new Card("Ambassador"));
		player2.setCard1(new Card("Assassin"));
		player2.setCard2(new Card("Contessa"));
		
		player1.assassinateCard(player2.getCard1());
		player2.assassinateCard(player1.getCard2());
		
		player1.blockAssassinateCard(player1.getCard2());
		player2.blockAssassinateCard(player2.getCard1());
		
		Assert.assertTrue("Card should be facedown", player1.getCard2().isFaceDown());
		Assert.assertTrue("Card should be facedown", player2.getCard1().isFaceDown());
	}
	
	@Test
	public void coup_subtracts_7_coins() {
		player1.setCard1(new Card("Duke"));
		player1.setCard2(new Card("Ambassador"));
		player2.setCard1(new Card("Assassin"));
		player2.setCard2(new Card("Contessa"));
		player1.setCoins(7);
		player2.setCoins(9);
		
		player1.coupCard(player2.getCard1());
		player2.coupCard(player1.getCard2());
		
		Assert.assertEquals("Coup should cost 7 coins", 0, player1.getCoins());
		Assert.assertEquals("Coup should cost 7 coins", 2, player2.getCoins());
	}
	
	@Test
	public void coup_flips_a_targets_card() {
		player1.setCard1(new Card("Duke"));
		player1.setCard2(new Card("Ambassador"));
		player2.setCard1(new Card("Assassin"));
		player2.setCard2(new Card("Contessa"));
		
		player1.coupCard(player2.getCard1());
		player2.coupCard(player1.getCard2());
		Assert.assertTrue("Card should be facedown", player1.getCard1().isFaceDown());
		Assert.assertTrue("Card should be faceup", !player1.getCard2().isFaceDown());
		Assert.assertTrue("Card should be faceup", !player2.getCard1().isFaceDown());
		Assert.assertTrue("Card should be facedown", player2.getCard2().isFaceDown());
	}
	
	@Test
	public void identify_available_cards_for_swap() {
		deck.buildDeck();
		player1.setCard1(deck.deal());
		player1.setCard2(deck.deal());
		player2.setCard1(deck.deal());
		player2.setCard2(deck.deal());
		Card[] expectedOutput = null;
		Card[] output = null;
		
		expectedOutput = new Card[] {new Card("Duke"), new Card("Duke"), new Card("Captain"), new Card("Captain")};
		output = player1.getCardsForExchange(deck);
		Assert.assertArrayEquals("Should return array of both faceup cards plus top two from deck", expectedOutput, output);
			
		player1.getCard1().flipOver();
		expectedOutput = new Card[] {new Card("Duke"), new Card("Assassin"), new Card("Assassin")};
		output = player1.getCardsForExchange(deck);
		Assert.assertArrayEquals("Should return array of one faceup card plus top two from deck", expectedOutput, output);		
	
		player1.getCard1().flipOver();
		player1.getCard2().flipOver();
		expectedOutput = new Card[] {new Card("Duke"), new Card("Assassin"), new Card("Contessa")};
		output = player1.getCardsForExchange(deck);
		Assert.assertArrayEquals("Should return array of one faceup card plus top two from deck", expectedOutput, output);		
		}
}

