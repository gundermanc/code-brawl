package com.etcz.codebrawl;

import java.util.ArrayList;

import com.etcz.codebrawl.Main.Troop;

public class Player2 extends Player{
	
	public void tick(Troop troop){
		ArrayList<Troop> visible = troop.look();
		//if someone in range
		if(!visible.isEmpty()){
			//if enemy
			Troop sighted = visible.get(0);
			if(sighted.getPlayerID() != troop.getPlayerID()){
				//if more than half health
				if(troop.getHealth() > Troop.MAX_HEALTH/4){
					troop.shoot(sighted);
				}
				//if less than half health
				else{
					troop.walk(sighted.getX()-troop.getX(),sighted.getY()-troop.getY());
				}
			}
			//if ally
			else{
				troop.walk(sighted.getX()-troop.getX(),sighted.getY()-troop.getY());
			}
		}
		//nothing in range
		else{
			troop.walk(Math.random(),Math.random());
		}
	
	}
}
