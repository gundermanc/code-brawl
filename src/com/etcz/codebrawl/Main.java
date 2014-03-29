package com.etcz.codebrawl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.*;
import javax.swing.*;
import com.etcz.codebrawl.*;

public class Main extends JPanel{
    private LinkedList<GameTurn> actionQueue;
    private EnvironmentInfo environment;
    private int max_troop;
    private Troop[][] troops;
	public enum actions{
		walk, shoot
	}
    
    public Main(int numPlayers) {
        this.actionQueue = new LinkedList<GameTurn>();
        this.environment = new EnvironmentInfo(numPlayers);
        //create troops
        troops = new Troop[numPlayers][max_troop];
        for (int i = 0, len=numPlayers; i < len; i++)
        {
            for (int j = 0; i<max_troop; i++)
            {
                troops[i][j] = new Troop(Math.random()*environment.getWidth(),Math.random()*environment.getHeight());
            }
        }
        GUI window = new GUI(troops);
        while(true){
        	int numEliminated = 0;
        	//loop though troops
        	for(int i=0; i<numPlayers; i++){
        		boolean eliminated = true;
        		for(Troop t : troops[i]){
        			if(t.health>0){
        				eliminated = false;
        				//TODO call player i's tick function
            			//tick(t);
                                //window.pack();
        			}
        		}
        		if(eliminated){
        			numEliminated++;
        		}
        	}
        	if(numEliminated >= numPlayers--){
        		break;//TODO game over
        	}
        	//process queue
        	processQueue();
        }
        
    }

    
    public void QueueAction(GameTurn action) {
        this.actionQueue.offerLast(action);
    }
    
    public void processQueue(){
    	GameTurn gt;
    	while((gt = actionQueue.poll())!=null){
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
    	ArrayList<Troop> closeTroops = new ArrayList<Troop>();
    	for(int i=0; i< troops.length; i++)
        {
            for(Troop t : troops[i]){
    		if(inRange(t.x,t.y,x,y)){
    			closeTroops.add(t);
    		}
            }  
        }
    	return closeTroops;
    }
    
    public boolean inRange(double x1, double y1, double x2, double y2){
    	return dist(x1,y1,x2,y2) < Troop.RADIUS;
    }
    
    public double dist(double x1, double y1, double x2, double y2){
    	return Math.pow(x1-x2, 2) + Math.pow(71-y2, 2);
    }

    public static void main(String[] args) {
       //Main main = new Main(Integer.parseInt(args[0]));
    	Main main = new Main(2);
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
