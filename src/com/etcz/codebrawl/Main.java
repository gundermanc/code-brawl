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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;


public class Main extends JPanel{
    private LinkedList<GameTurn> actionQueue;
    private EnvironmentInfo environment;
    private int max_troop = 10;
    private final int max_turns = 700;
    private Troop[][] troops;
    private Player[] players;
    private GUI window;
    private int numPlayers;
        public enum actions{
                walk, shoot
        }
    
    public Main(String[] args) throws Exception{
        numPlayers = Integer.parseInt(args[0]);
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
        window = new GUI(troops,environment);
        JFrame frame = new JFrame("Code BRAWL!!");
        frame.setSize((int)environment.getWidth()+25,(int)environment.getHeight()+40);//TODO added 50
        frame.setVisible(true);
        frame.getContentPane().add(window);

        
        //start countdown
        JTextArea area = new JTextArea();
        area.setSize(400,200);
        area.setVisible(true);
        frame.repaint();
        //frame.getContentPane().add(area, "Center");
        area.setBackground(new Color(0,0,0,0));
        area.setLocation((int)(environment.getWidth()/2)-100, (int)(environment.getHeight()/2)-50);
        area.setFont(area.getFont().deriveFont(100.0f));
        window.add(area);
        for(int i=0; i<numPlayers; i++){
        	area.setText(args[i+1]);
            area.setForeground(window.troopsColor[i]);
            trySleep(800);
            area.setLocation(area.getX(),area.getY()+1);
        }
        trySleep(800);
        area.setText("Ready");
        trySleep(600);
        area.setText("Set");
        trySleep(600);
        area.setText("Fight!");
        trySleep(600);
        window.remove(area);
        startGame();
    }
    
    public void startGame(){
    	ArrayList<Integer> eliminated = new ArrayList<Integer>();
        int numEliminated = 0;
        
        for(int n=0; n < max_turns && numEliminated+1 < numPlayers; n++){
                //loop though troops
                for(int i=0; i<numPlayers; i++){
                	//if player not eliminated
                    if(!eliminated.contains(i)){
                    	boolean allDead = true;
                    	for(Troop t : troops[i]){
                            if(t.health>0){
                            	allDead = false;
                                    players[i].tick(t, environment);
                            }
                    	}
                    	if(allDead){
                        	eliminated.add(i);
                        	numEliminated++;
                        }
                    }
                }
                window.validate();
                window.repaint();
                trySleep(100);
                //process queue
                processQueue();
            window.repaint();
        }
        if(numEliminated+1 == numPlayers){
        	JOptionPane.showMessageDialog(this, "Congrats! Winner!");
        } else{
        	JOptionPane.showMessageDialog(this, "Out of time.");
        }
    }
    
    public void trySleep(int duration){
    	try {
            Thread.sleep(duration);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    
    public void QueueAction(GameTurn action) {
    	if(!actionQueue.isEmpty() && actionQueue.getLast().troop.equals(action.troop)){
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
                            double finalX = troop.x + 2*gt.x;
                            double finalY = troop.y + 2*gt.y;
                            //prevent bumping into edge of screen
                            if(finalX < 0 || finalX > environment.getWidth())
                                    finalX = troop.x;
                            if(finalY < 0 || finalY > environment.getHeight())
                                    finalY = troop.y;
                            for(EnvironmentInfo.PowerUp pwr : environment.powerUps){
                            	gt.troop.checkAndGetPower(pwr);
                            }
                            //prevent bumping into other troops
                            /*if(!findTroopsInRange(gt.troop,Troop.WIDTH).isEmpty()){
                                    return;
                            }*/
                            //change pos
                            gt.troop.setPos(finalX, finalY);
                            break;
                    case shoot:
                            ArrayList<Troop> troopsInRange = findTroopsInRange(gt.troop,gt.troop.getRadius());
                            for(Troop t : troopsInRange){
                                    if(t.equals(gt.target)){
                                    	gt.troop.lastTarget = t;
                                    	t.health = t.health - gt.troop.damage;
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
                    if(inRange(troop,t) && !t.equals(troop) && t.health>0){
                            closeTroops.add(t);
                    }
            }  
        }
            return closeTroops;
    }
    /**
     * Uses the range of t1
     * @param t1
     * @param t2
     * @return
     */
    public boolean inRange(Troop t1, Troop t2){
            return dist(t1.x,t1.y,t2.x,t2.y) < t1.radius;
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
        private int radius = 30;
        private int damage = 1;
        public static final double WIDTH = 12;
        protected actions lastAction = actions.walk;
        protected Troop lastTarget = null;
        private int playerID;
        private int power=-1;
        
        public Troop(double x, double y, int playerID){
                this.x = x;
                this.y = y;
                this.playerID = playerID;
        }
        
        public void checkAndGetPower(EnvironmentInfo.PowerUp pwr)
        {
            if ((Math.abs(pwr.getX()-getX())<WIDTH) && (Math.abs(pwr.getY()-getY())<WIDTH) && !pwr.isPicked())
            {
                this.power = pwr.getPower();
                pwr.pick();
                
            switch(power)
            {
            case 0:
                this.radius+=10;
                break;
            case 1:
                this.health+=5;
                break;
            case 2:
                this.damage=2;
                break;
            case 3:
                this.health-=1;
                break;
            }
           }
        }
        
        private void levelUp()
        {
            damage+=1;
            health+=2;
        }
        
        public int getRadius()
        {
            return radius;
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
                return findTroopsInRange(this,radius);
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