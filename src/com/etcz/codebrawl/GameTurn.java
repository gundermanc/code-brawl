package com.etcz.codebrawl;
import com.etcz.codebrawl.*;
public class GameTurn {
	
	public final Main.actions act;
	public final Main.Troop troop;
	public double x;
	public double y;
	public Main.Troop target;
	
	/**
	 * Used for Shooting
	 * @param act
	 * @param troop
	 * @param t
	 */
	public GameTurn(Main.actions act, Main.Troop troop, Main.Troop t){
		this.act = act;
		this.troop = troop;
		this.target = t;
	}
	
	/**
	 * Used for walking
	 * @param act
	 * @param troop
	 * @param x
	 * @param y
	 */
	public GameTurn(Main.actions act, Main.Troop troop, double x, double y){
		this.act = act;
		this.troop = troop;
		this.x = x;
		this.y = y; 
		//normalize
		if(x>y){
			x = 1;
			y = x/y;
		} else{
			y = 1;
			x = y/x;
		}
	}
	
}