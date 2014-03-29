package com.etcz.codebrawl;

import java.util.ArrayList;

import com.etcz.codebrawl.Main.Troop;

public class Player2 extends Player{
	
	public void tick(Troop troop, EnvironmentInfo env){
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
				//if less than quarter health
				else{
					troop.walk(troop.getX()-sighted.getX(),troop.getY()-sighted.getY());
				}
			}
			//if ally
			else{
				troop.walk(troop.getX()-sighted.getX(),troop.getY()-sighted.getY());
			}
		}
		//nothing in range
		else{
			troop.walk((2*Math.random())-1,2*(Math.random())-1);
		}
	
	}
}
