package com.etcz.codebrawl;
import com.etcz.codebrawl.Troop.actions;

public class GameTurn {
	
	public final actions act;
	public final Troop troop;
	
	public GameTurn(actions act, Troop troop){
		this.act = act;
		this.troop = troop;
	}
	
}