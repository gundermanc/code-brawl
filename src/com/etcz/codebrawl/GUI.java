package com.etcz.codebrawl;
import com.etcz.codebrawl.*;

import java.awt.Color;

import javax.swing.*;
import java.awt.Graphics;

public class GUI extends JFrame
{
    Main.Troop[] troop;
    public GUI(Main.Troop[][] troop,EnvironmentInfo env)
    {
        this.troop = troop;
        setSize((int)env.getHeight(), (int)env.getHeight());
        setVisible(true);
    }
    
    public void paint(Graphics g)
    {
        for (int i = 0; i < troop.length; i++)
        {
            g.setColor(new Color(((int)Math.random()*255),((int)Math.random()*255),((int)Math.random()*255)));
            for (Main.Troop t : troop[i])
            {
                g.drawArc((int)t.getX(),(int)t.getY(),10,10,0,360);
            }
        }
    }
}
