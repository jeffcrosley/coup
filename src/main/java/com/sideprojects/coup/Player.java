package com.sideprojects.coup;

public class Player {
	
	private String name = this.name;
	private Hand hand = this.hand;
	private int numberOfCoins = this.numberOfCoins;
	private boolean isAlive() {
		return this.numberOfActiveCards > 0;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getNumberOfCoins() {
		return numberOfCoins;
	}
	
	public void setNumberOfCoins(int numberOfCoins) {
		this.numberOfCoins = numberOfCoins;
	}
	
	public Player(String name, Deck deck) {
		this.name = name;
		this.numberOfCoins = 2;
		this.hand = new Hand(Card deck.listOfCards, Card deck.listOfCards.remove(0));
	}
	
	public void TakeIncome() {
		this.setNumberOfCoins(this.numberOfCoins + 1);
		System.out.println("Took 1 coin as Income. Current coins = " + this.getNumberOfCoins());
	}
	
	public void TakeForeignAid() {
		this.setNumberOfCoins(this.numberOfCoins + 2);
		System.out.println("Took 2 coins as Foreign Aid. Current coins = " + this.getNumberOfCoins());
	}
	
	public void DukeTax() {
		this.setNumberOfCoins(this.numberOfCoins + 3);
		System.out.println("Taxed 3 coins as Duke. Current coins = " + this.getNumberOfCoins());
	}
	
	public void CaptainSteal(Player targetPlayer) {
		this.setNumberOfCoins(this.numberOfCoins + 2);
		targetPlayer.setNumberOfCoins(targetPlayer.getNumberOfCoins() - 2);		
		System.out.println("Stole as Captain. Current coins = " + this.getNumberOfCoins());
		System.out.println(targetPlayer.getName() + " now has " + targetPlayer.getNumberOfCoins() + " coins.");
	}
	
	public void CaptainBlock() {
		
	}
	
	public void AssassinKill(Player targetPlayer) {
		
	}
	
	public void ContessaBlock() {
		
	}
	
	public void AmbassadorExchange() {
		
	}
	
	public void AmbassadorBlock() {
		
	}
	
	public void StageCoup(Player targetPlayer) {
		
	}
	
	private String TakeAction() {
		return null;
	}
	
	public void Challenge() {
		
	}
}
