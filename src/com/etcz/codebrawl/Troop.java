package com.etcz.codebrawl;

public class Troop {
	private float x;
	private float y;
	private int playerNum;
	private int health;
	private static int radius;
	
	private Main mainPointer;
	
	public enum actions{
		walk, shoot
	}
	
	public Troop(float x, float y, int playerNum, Main mainPointer){
		this.mainPointer = mainPointer;
	}
	
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	
	/**
	 * @return A list of objects in the radius of the troop.
	 */
	public List<Objects> look(){
		
	}

	public final void walk(float x, float y){
		mainPointer.QueueAction(new GameTurn(actions.walk,this));
	}
	
	public final void shoot(float x, float y){
		mainPointer.QueueAction(new GameTurn(actions.shoot,this));
	}
}
