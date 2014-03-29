package src.com.etcz.codebrawl;
import src.com.etcz.codebrawl.*;

public class Player
{
    private Main.Troop[] troops;
    public Player(Main.Troop[] troops)
    {
        this.troops = troops;
    }
    
    public Main.Troop[] getTroops()
    {
        return this.troops;
    }
    
}
