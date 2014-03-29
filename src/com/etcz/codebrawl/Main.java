package src.com.etcz.codebrawl;

import java.util.ArrayList;
import java.util.LinkedList;

import src.com.etcz.codebrawl.*;

public class Main {
    private LinkedList<GameTurn> actionQueue;
    private EnvironmentInfo environment;
    private Player[] players;
	public enum actions{
		walk, shoot
	}
    
    public Main(int numberOfPlayers) {
	this.actionQueue = new LinkedList<GameTurn>();
        players = new Player[numberOfPlayers];
    }

    public void QueueAction(GameTurn action) {
	this.actionQueue.offerLast(action);
    }
    
    public ArrayList<Troop> findTroopsInRange(double x, double y){
    	ArrayList<Troop> troops = new ArrayList<Troop>();
    	for(int i = 0; i < players.length; i++)
        {
            for(Troop t : players[i]){
    		if(inRange(t.x,t.y,x,y)){
    			troops.add(t);
    		}
            }  
        }
    	return troops;
    }
    
    public boolean inRange(double x1, double y1, double x2, double y2){
    	return Math.pow(x1-x2, 2) + Math.pow(71-y2, 2) < Troop.RADIUS;
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
    	
    	public Troop(double x, double y){
    		this.x = x;
    		this.y = y;
    	}
    	
    	public double getX(){
    		return x;
    	}
    	public double getY(){
    		return y;
    	}
    	public int getHealth(){
    		return health;
    	}
    	
    	/**
    	 * @return A list of objects in the radius of the troop.
    	 */
    	public ArrayList<Troop> look(){
    		return findTroopsInRange(x,y);
    	}

    	public final void walk(double x, double y){
    		QueueAction(new GameTurn(actions.walk,this));
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