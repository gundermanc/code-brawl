package com.etcz.codebrawl;

import java.util.ArrayList;

public class Player2 extends Player{
	
	public void tick(Main.Troop troop){
		ArrayList<Main.Troop> visible = troop.look();
		if(!visible.isEmpty()){
			Main.Troop t = visible.get(0);
			troop.shoot(t);
			System.out.println("Shoot command");
		} else{
			troop.walk(1, 0);
		}
	}
	
}
