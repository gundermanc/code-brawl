package com.etcz.codebrawl;

public class Player
{
    private Main.Troop[] troop;
    public Player(Main.Troop[] troop)
    {
        this.troop = troop;
    }
    
    public Main.Troop[] getTroop()
    {
        return troop;
    }
    
}
