package com.etcz.codebrawl;
import com.etcz.codebrawl.*;

import java.awt.Color;

import javax.swing.*;
import java.awt.Graphics;

public class GUI extends JFrame
{
    Main.Troop[][] troops;
    public GUI(Main.Troop[][] troops,EnvironmentInfo env)
    {
        this.troops = troops;
        setSize((int)env.getHeight(), (int)env.getHeight());
        setVisible(true);
    }
    
    public void paint(Graphics g)
    {
        for (int i = 0; i < troops.length; i++)
        {
            g.setColor(new Color(((int)Math.random()*255),((int)Math.random()*255),((int)Math.random()*255)));
            for (Main.Troop t : troops[i])
            {
                g.drawArc((int)t.getX(),(int)t.getY(),10,10,0,360);
            }
        }
    }
}
