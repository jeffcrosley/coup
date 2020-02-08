package com.sideprojects.coup;

public class Main {

	public static void main(String[] args) {
		Deck myDeck = new Deck();
		
		for (Card card : myDeck.getListOfCards()) {
			System.out.println(card);
		}
		
	}

}
