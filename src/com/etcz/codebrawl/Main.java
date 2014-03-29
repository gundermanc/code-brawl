/**
 * To add: timer for the match
 * 	timer for each method?
 * importing functions
 */
package com.etcz.codebrawl;
import com.etcz.codebrawl.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.*;
import javax.swing.*;


public class Main extends JPanel{
    private LinkedList<GameTurn> actionQueue;
    private EnvironmentInfo environment;
    private int max_troop = 5;
    private Troop[][] troops;
    private Player[] players;
        public enum actions{
                walk, shoot
        }
    
    public Main(String[] args) throws Exception{
        int numPlayers = Integer.parseInt(args[0]);
        players = new Player[numPlayers];
        for (int i = 1; i < args.length; i++)
        {
            Class temp = Class.forName("com.etcz.codebrawl."+args[i]);
            players[i-1] = (Player)temp.newInstance();
        }
        
        this.actionQueue = new LinkedList<GameTurn>();
        this.environment = new EnvironmentInfo(numPlayers);
        //create troops
        troops = new Troop[numPlayers][max_troop];
        for (int i = 0, len=numPlayers; i < len; i++)
        {
            for (int j = 0; j<max_troop; j++)
            {
                troops[i][j] = new Troop(Math.random()*environment.getWidth(),Math.random()*environment.getHeight(),i);
            }
        }
        GUI window = new GUI(troops,environment);
        JFrame frame = new JFrame("Code BRAWL!!");
        frame.setSize((int)environment.getWidth()+25,(int)environment.getHeight()+40);//TODO added 50
        frame.setVisible(true);
        frame.getContentPane().add(window);
        while(true){
                int numEliminated = 0;
                //loop though troops
                for(int i=0; i<numPlayers; i++){
                        boolean eliminated = true;
                        for(Troop t : troops[i]){
                                if(t.health>0){
                                        eliminated = false;
                                        players[i].tick(t);
                                }
                        }
                        if(eliminated){
                                numEliminated++;
                        }
                }
                if(numEliminated >= numPlayers-1){
                        break;//TODO game over
                }
                window.validate();
                window.repaint();
                try {
                    Thread.sleep(150);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                //process queue
                processQueue();
            window.repaint();
        }
        
    }

    
    public void QueueAction(GameTurn action) {
    	Troop lastTroop = actionQueue.getLast().troop;
    	if(action.troop.equals(lastTroop)){
    		return;//TODO throw exception
    	}
        this.actionQueue.offerLast(action);
    }
    
    public void processQueue(){
            GameTurn gt;
            while((gt = actionQueue.poll())!=null){
            	gt.troop.lastAction = gt.act;
            	gt.troop.lastTarget = null;
                    switch(gt.act){
                    case walk:
                    	Troop troop = gt.troop;
                            double finalX = troop.x + 3*gt.x;
                            double finalY = troop.y + 3*gt.y;
                            //prevent bumping into edge of screen
                            if(finalX < 0 || finalX > environment.getWidth())
                                    finalX = troop.x;
                            if(finalY < 0 || finalY > environment.getHeight())
                                    finalY = troop.y;
                            //prevent bumping into other troops
                            /*if(!findTroopsInRange(gt.troop,Troop.WIDTH).isEmpty()){
                                    return;
                            }*/
                            //change pos
                            gt.troop.setPos(finalX, finalY);
                            break;
                    case shoot:
                            ArrayList<Troop> troopsInRange = findTroopsInRange(gt.troop,Troop.RADIUS);
                            for(Troop t : troopsInRange){
                                    if(t.equals(gt.target)){
                                    	gt.troop.lastTarget = t;
                                    	t.health--;
                                    }
                            }
                            break;
                    }
            }
    }
    
    public ArrayList<Troop> findTroopsInRange(Troop troop, double range){
            ArrayList<Troop> closeTroops = new ArrayList<Troop>();
            for(int i=0; i< troops.length; i++)
        {
            for(Troop t : troops[i]){
                    if(inRange(t.x,t.y,troop.x,troop.y) && !t.equals(troop) && t.health>0){
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
            return Math.sqrt( Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2) );
    }

    public static void main(String[] args) throws Exception{
       //Main main = new Main(Integer.parseInt(args[0]));
            Main main = new Main(args);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    public class Troop {
            private double x;
            private double y;
            public static final int MAX_HEALTH = 10;
            private int health = MAX_HEALTH;
            private final static int RADIUS = 30;
            private final static double WIDTH = 5;
            protected actions lastAction = actions.walk;
            protected Troop lastTarget = null;
            private int playerID;
            
            private Troop(double x, double y, int playerID){
                    this.x = x;
                    this.y = y;
                    this.playerID = playerID;
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
            public int getPlayerID(){
            	return this.playerID;
            }
            
            /**
             * @return A list of objects in the radius of the troop.
             */
            public ArrayList<Troop> look(){
                    return findTroopsInRange(this,RADIUS);
            }

            public final void walk(double x, double y){
                    QueueAction(new GameTurn(actions.walk,this,x,y));
            }
            
            public final void shoot(Troop t){
                    QueueAction(new GameTurn(actions.shoot,this,t));
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