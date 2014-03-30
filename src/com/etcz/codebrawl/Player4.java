package src.com.etcz.codebrawl;

import java.util.ArrayList;


public class Player4 extends Player {
    
    Main.Troop me;
    ArrayList<Main.Troop> enemies;
    public void tick(Main.Troop troop, EnvironmentInfo env) {
        this.me = troop;
        enemies = new ArrayList<>();
        ArrayList<Main.Troop> temp = troop.look();

        // grab all of the enemies
        for (int i = 0; i < temp.size(); i++) {
            if (troop.getPlayerID() != temp.get(i).getPlayerID()) {
            	if (temp.get(i) != null) {
            		enemies.add(temp.get(i));
            	}	
            }
        }

        // detect if an enemy is in range
        Main.Troop enemy = getNearestEnemy();
        // shoot the enemy
        if (enemy != null) {
            this.me.shoot(enemy);
        }
        else {
            // try and run away and don't make the same moves over and over
            double rng = Math.random() * 4 + 1;
            if (rng <= 1.0) {
                // move right 1 and down 3
                this.me.walk(1,3);
            }
            else if (rng > 1.0 && rng <= 2.0) {
                // move right 3 and down 1
                this.me.walk(3, 1);
            }
            else if (rng > 2.0 && rng <= 3.0) {
                // move left 1 and up 3
                this.me.walk(-1, -3);
            }
            else {
                // move left 3 and up 1
                this.me.walk(-3, 2);
            }
        }

    }

    // get the first enemy that is within your range
    public Main.Troop getNearestEnemy() {
    	if (enemies != null) {
	        for (int i = 0; i < enemies.size(); i++) {
	            if (Math.abs(this.me.getX() - enemies.get(i).getX()) <= 30 || 
	                Math.abs(this.me.getY() - enemies.get(i).getY()) <= 30) {
	                return enemies.get(i);
	            }
	        }
    	}
        return null;
    }
}
