package com.etcz.codebrawl;
import com.etcz.codebrawl.*;
import javax.swing.*;
import java.awt.Graphics;

public class GUI extends JFrame
{
    Main.Troop[] troop;
    public GUI(Main.Troop[] troop,EnvironmentInfo env)
    {
        this.troop = troop;
        setSize((int)env.getHeight(), (int)env.getHeight());
        setVisible(true);
    }
    
    public void paint(Graphics g)
    {
        for (Main.Troop t : troop)
        {
            g.drawArc((int)t.getX(),(int)t.getY(),20,20,0,360);
        }
    }
}
