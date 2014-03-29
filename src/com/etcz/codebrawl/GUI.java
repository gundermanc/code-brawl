package com.etcz.codebrawl;
import com.etcz.codebrawl.*;

import java.awt.Color;

import javax.swing.*;
import java.awt.Graphics;

import com.etcz.codebrawl.EnvironmentInfo;
import com.etcz.codebrawl.Main;

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
            troopsColor[i] = new Color(((int)(Math.random()*255)),((int)(Math.random()*255)),((int)(Math.random()*255)));
        }
    }
    
    public void paint(Graphics g, Main.actions a)
    {    
        super.paintComponent(g);
        for (int i = 0; i < troops.length; i++)
        {
            g.setColor(troopsColor[i]);
            for (Main.Troop t : troops[i])
            {
            	g.fillArc((int)t.getX(),(int)t.getY(),10,10,0,360);
                switch(t.lastAction){
                case walk:                
                    break;
                case shoot:
                    g.drawLine((int)t.getX(),(int)t.getY(),(int)t.lastTarget.getX(),(int)t.lastTarget.getY());
                    break;
                }
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
            	g.fillArc((int)t.getX(),(int)t.getY(),10,10,0,360);
                switch(t.lastAction){
                case walk:                
                    break;
                case shoot:
                    g.drawLine((int)t.getX(),(int)t.getY(),(int)t.lastTarget.getX(),(int)t.lastTarget.getY());
                    break;
                }
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
            	g.fillArc((int)t.getX(),(int)t.getY(),10,10,0,360);
                switch(t.lastAction){
                case walk:                
                    break;
                case shoot:
                    g.drawLine((int)t.getX(),(int)t.getY(),(int)t.lastTarget.getX(),(int)t.lastTarget.getY());
                    break;
                }
            }
        }
    }
}