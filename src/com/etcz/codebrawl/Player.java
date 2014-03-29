package src.com.etcz.codebrawl;
import src.com.etcz.codebrawl.*;
public class Player
{
<<<<<<< HEAD
	
	public void tick(Main.Troop troop){
		troop.walk(-1, 0);
	}
=======
    private Main.Troop[] troops;
    public Player(Main.Troop[] troops)
    {
        this.troops = troops;
    }
    
    public Main.Troop[] getTroops()
    {
        return this.troops;
    }
>>>>>>> 94e1301618fb383ddc8433e431aba628f3b22596
    
}
