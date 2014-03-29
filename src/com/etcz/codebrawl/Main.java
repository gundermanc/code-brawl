package com.etcz.codebrawl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.*;
import javax.swing.*;

public class Main extends JPanel, JFrame{
    private LinkedList<GameTurn> actionQueue;
    private EnvironmentInfo environment;
    private int max_troop;
    private Player[] players;
	public enum actions{
		walk, shoot
	}
    
    public Main(int numberOfPlayers) {
        this.actionQueue = new LinkedList<GameTurn>();
        this.players = new Player[numberOfPlayers];
        this.environment = new EnvironmentInfo(numberOfPlayers);
        for (int i = 0, len=players.length; i < len; i++)
        {
            Troop[] t = new Troop[max_troop];
            for (int j = 0; i<max_troop; i++)
            {
                t[j] = new Troop(Math.random()*environment.getWidth(),Math.random()*environment.getHeight());
            }
            players[i] = new Player(t);
        }
    }

    
    public void QueueAction(GameTurn action) {
        this.actionQueue.offerLast(action);
    }
    
    public void processQueue(){
    	for(GameTurn gt : actionQueue){
    		switch(gt.act){
    		case walk:
    			double finalX = gt.troop.x + gt.x;
    			double finalY = gt.troop.y + gt.y;
    			//prevent bumping into edge of screen
    			if(finalX < 0)
    				finalX = 0;
    			else if(finalX > environment.getWidth())
    				finalX = environment.getWidth();
    			if(finalX < 0)
    				finalX = 0;
    			else if(finalY > environment.getHeight())
    				finalY = environment.getHeight();
    			//prevent bumping into other troops
    			if(!findTroopsInRange(finalX,finalY,Troop.WIDTH).isEmpty()){
    				return;
    			}
    			//change pos
    			gt.troop.setPos(finalX, finalY);
    			break;
    		case shoot:
    			double fireAngle = Math.atan(gt.y/gt.x);
    			ArrayList<Troop> troopsInRange = findTroopsInRange(gt.troop.x,gt.troop.y,Troop.RADIUS);
    			for(Troop t : troopsInRange){
    				double angle = Math.atan((t.y-gt.troop.y)/(t.x-gt.troop.x));
    				if(Math.abs(angle-fireAngle)< Math.PI/4){
    					t.setHealth(t.health--);
    				}
    			}
    			break;
    		}
    	}
    }
    
    public ArrayList<Troop> findTroopsInRange(double x, double y, double range){
    	ArrayList<Troop> troops = new ArrayList<Troop>();
    	for(int i = 0; i < players.length; i++)
        {
            for(Troop t : players[i].getTroops()){
    		if(inRange(t.x,t.y,x,y)){
    			troops.add(t);
    		}
            }  
        }
    	return troops;
    }
    
    public boolean inRange(double x1, double y1, double x2, double y2){
    	return dist(x1,y1,x2,y2) < Troop.RADIUS;
    }
    
    public double dist(double x1, double y1, double x2, double y2){
    	return Math.pow(x1-x2, 2) + Math.pow(71-y2, 2);
    }

    public static void main(String[] args) {
       Main main = new Main(Integer.parseInt(args[0]));
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    public class Troop {
    	private double x;
    	private double y;
    	private int health = 3;
    	private final static int RADIUS = 10;
    	private final static double WIDTH = 5;
    	
    	public Troop(double x, double y){
    		this.x = x;
    		this.y = y;
    	}
    	
    	public double getX(){
    		return this.x;
    	}
    	public double getY(){
    		return this.y;
    	}
    	public int getHealth(){
    		return this.health;
    	}
    	
    	/**
    	 * @return A list of objects in the radius of the troop.
    	 */
    	public ArrayList<Troop> look(){
    		return findTroopsInRange(x,y,RADIUS);
    	}

    	public final void walk(double x, double y){
    		QueueAction(new GameTurn(actions.walk,this,x,y));
    	}
    	
    	public final void shoot(double x, double y){
    		QueueAction(new GameTurn(actions.shoot,this));
    	}
    	
    	private void setPos(double x, double y){
    		this.x = x;
    		this.y = y;
    	}
    	private void setHealth(int health){
    		this.health = health;
    	}
    }
}
