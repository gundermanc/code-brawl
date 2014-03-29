package com.etcz.codebrawl;

public class Player1 extends Player{

	public void tick(Main.Troop troop){
		System.out.println("p1t");//TODO remove
		troop.walk(-1, 0);
	}
	
}
