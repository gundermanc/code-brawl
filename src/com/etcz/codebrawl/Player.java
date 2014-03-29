package src.com.etcz.codebrawl;
import src.com.etcz.codebrawl.*;

public class Player
{
    private Main.Troop[] troop;
    public void player(Main.Troop[] troop)
    {
        this.troop = troop;
    }
    
    public Main.Troop[] getTroop()
    {
        return troop;
    }
    
}
