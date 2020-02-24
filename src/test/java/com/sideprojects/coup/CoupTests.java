package com.sideprojects.coup;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CoupTests {
	
	Coup coup = new Coup();
	
	@Test
	public void createPlayers_makes_correct_number_of_players() {
		List<Player> players = Coup.createPlayers(3);
		
		Assert.assertEquals(3, players.size());
	}
}
