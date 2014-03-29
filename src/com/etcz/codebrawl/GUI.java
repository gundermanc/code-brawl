package com.etcz.codebrawl;
import com.etcz.codebrawl.*;

import java.awt.Color;

import javax.swing.*;
import java.awt.Graphics;

import src.com.etcz.codebrawl.EnvironmentInfo;
import src.com.etcz.codebrawl.Main;

public class GUI extends JPanel
{
    Main.Troop[][] troops;
    Color[] troopsColor;
    public GUI(Main.Troop[][] troops,EnvironmentInfo env)
    {
        this.troops = troops;
        troopsColor = new Color[troops.length];
        setSize((int)env.getWidth(), (int)env.getHeight());
        setVisible(true);
        for (int i = 0; i < troops.length; i++)
        {
            troopsColor[i] = new Color(((int)(Math.random()*255)),((int)Math.random()*255),((int)Math.random()*255));
        }
    }
    
    public void paint(Graphics g)
    {    
        super.paintComponent(g);
        for (int i = 0; i < troops.length; i++)
        {
            g.setColor(troopsColor[i]);
            for (Main.Troop t : troops[i])
            {
                g.drawArc((int)t.getX(),(int)t.getY(),10,10,0,360);
            }
        }
    }
    
    public void repaint(Graphics g)
    {
        super.paintComponent(g);
    	System.out.print("reprinting");//TODO remove
        for (int i = 0; i < troops.length; i++)
        {
            g.setColor(troopsColor[i]);
            for (Main.Troop t : troops[i])
            {
                g.drawArc((int)t.getX(),(int)t.getY(),10,10,0,360);
            }
        }
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for (int i = 0; i < troops.length; i++)
        {
            g.setColor(troopsColor[i]);
            for (Main.Troop t : troops[i])
            {
                g.drawArc((int)t.getX(),(int)t.getY(),10,10,0,360);
            }
        }
    }
}
