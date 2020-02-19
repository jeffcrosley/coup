package com.sideprojects.coup;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
	 
	// PRIVATE MEMBERS
	private String name;
	private int coins;
	private Card card1;
	private Card card2;
	private boolean isTurn;
	private boolean isAlive;

	// GETS AND SETS
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public Card getCard1() {
		return card1;
	}

	public void setCard1(Card card1) {
		this.card1 = card1;
	}

	public Card getCard2() {
		return card2;
	}

	public void setCard2(Card card2) {
		this.card2 = card2;
	}

	public boolean isTurn() {
		return isTurn;
	}

	public void setTurn(boolean isTurn) {
		this.isTurn = isTurn;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	// CTOR
	public Player(String name) {
			this.name = name;
			this.isAlive = true;
			this.coins = 2;
	}	

	// PUBLIC METHODS
	public void takeIncome() {
		setCoins(getCoins() + 1);
	}
	
	public void takeForeignAid() {
		setCoins(getCoins() + 2);
	}
	
	public void tax() {
		setCoins(getCoins() + 3);
	}
	
	public void steal(Player target) {
		this.setCoins(this.getCoins() + 2);
		target.setCoins(target.getCoins() - 2);
	}
	
	public void killCard1(Player target) {
		target.getCard1().flipOver();
	}
	
	public void killCard2(Player target) {
		target.getCard2().flipOver();
	}	
	
	// STRING OVERRIDE
	@Override
	public String toString() {
		return this.getName();
	}

}
