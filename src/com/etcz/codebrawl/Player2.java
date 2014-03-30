package src.com.etcz.codebrawl;
import src.com.etcz.codebrawl.Main.Troop;
import java.util.ArrayList;


public class Player2 extends Player{
	
	Troop currentEnemy = null;
	
	public void tick(Troop troop, EnvironmentInfo env){
		if(currentEnemy != null && currentEnemy.getHealth() <= 0){
			currentEnemy = null;
		}
		ArrayList<Troop> visible = troop.look();
		//if someone in range
		if(!visible.isEmpty()){
			//if enemy
			Troop sighted = visible.get(0);
			if(sighted.getPlayerID() != troop.getPlayerID()){
				if(currentEnemy == null){
					currentEnemy = sighted;
				}
				troop.shoot(sighted);
			}
		}
		//nothing in range
		else{
			if(currentEnemy != null){
				troop.walk(currentEnemy.getX()-troop.getX(), currentEnemy.getY()-troop.getY());
			}
			troop.walk(0,0);
		}
	
	}
}
