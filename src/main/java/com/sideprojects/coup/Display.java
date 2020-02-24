package com.sideprojects.coup;

public class Display {
	
	public Display() {}

	public static void welcomeToCoupHowManyPlayers() {
		System.out.println("Welcome to Coup!  How many are playing? (2-6 players): ");
	}
	
}

//public static List<Player> createPlayers(Scanner input) {
//	System.out.println("Welcome to Coup!  How many are playing? (2-6 players): ");
//	List<Player> players = new ArrayList<Player>();
//	int numberOfPlayers = Integer.parseInt(input.nextLine());		
//	
//	for (int i = 0; i < numberOfPlayers; i++) {
//		players.add(new Player("Player " + Integer.toString(i + 1)));
//	}
//	
//	return players;
//}