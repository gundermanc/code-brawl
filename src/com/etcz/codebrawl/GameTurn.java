package src.com.etcz.codebrawl;
import src.com.etcz.codebrawl.Main.actions;

public class GameTurn {
	
	public final Main.actions act;
	public final Main.Troop troop;
	public final double xChange;
	public final double yChange;
	
	public GameTurn(Main.actions act, Main.Troop troop){
		this.act = act;
		this.troop = troop;
	}
	
	public GameTurn(Main.actions act, Main.Troop troop, double xChange, double yChange){
		this.act = act;
		this.troop = troop;
		this.x = x;
		this.y = y; 
	}
	
}