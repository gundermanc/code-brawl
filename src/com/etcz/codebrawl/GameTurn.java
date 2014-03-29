package src.com.etcz.codebrawl;
import src.com.etcz.codebrawl.*;
public class GameTurn {
	
	public final Main.actions act;
	public final Main.Troop troop;
	public double x;
	public double y;
	
	public GameTurn(Main.actions act, Main.Troop troop){
		this.act = act;
		this.troop = troop;
	}
	
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