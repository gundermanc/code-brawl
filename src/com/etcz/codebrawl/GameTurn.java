package src.com.etcz.codebrawl;
import src.com.etcz.codebrawl.Main.actions;

public class GameTurn {
	
	public final actions act;
	public final Main.Troop troop;
	
	public GameTurn(actions act, Main.Troop troop){
		this.act = act;
		this.troop = troop;
	}
	
}