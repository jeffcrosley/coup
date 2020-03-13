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

