package com.etcz.codebrawl;

public class Troop {
	private float x;
	private float y;
	private int health;
	private static int radius;
	
	public Troop(float x, float y){
		
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
		Main.QueueAction(new GameTurn(){
			
		});
	}
	
	public final void shoot(float x, float y){
		Main.QueueAction(new GameTurn(){
			
		});
	}
}
