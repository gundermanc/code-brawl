package src.com.etcz.codebrawl;
import src.com.etcz.codebrawl.*;

public class Player
{
    private Main.Troop[] troops;
    public void player(Main.Troop[] troops)
    {
        this.troops = troops;
    }
    
    public Main.Troop[] getTroop()
    {
        return this.troops;
    }
    
}
