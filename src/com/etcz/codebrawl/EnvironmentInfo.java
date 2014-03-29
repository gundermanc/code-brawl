package com.etcz.codebrawl;

public class EnvironmentInfo
{
    public EnvironmentInfo(double width, double height)
    {
        this.width = width;
        this.height = height;
    }
    
    private static double width;
    private static double height;
    private Wall[] wall;
    
    public void setWalls()
    {   
    }
    
    public void update(GameTurn turn)
    {   
    }
    
    public double getWidth()
    {
        return width;
    }
    
    public double getHeight()
    {
        return height;
    }
    
}
