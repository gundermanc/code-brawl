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
        setSize((int)env.getWidth(), (int)env.getHeight());
        setVisible(true);
    }
    
    public void paint(Graphics g)
    {
    	System.out.print("printing");//TODO remove
        for (int i = 0; i < troops.length; i++)
        {
            g.setColor(new Color(((int)(Math.random()*255)),((int)Math.random()*255),((int)Math.random()*255)));
            //System.out.print("first loop");//TODO remove
            //System.out.print(troops[i].length);//TODO remove
            for (Main.Troop t : troops[i])
            {
            	//System.out.print(t.getX()+" x "+t.getY());//TODO remove
                g.drawArc((int)t.getX(),(int)t.getY(),10,10,0,360);
            }
        }
    }
    
    public void repaint(Graphics g)
    {
    	System.out.print("printing");//TODO remove
        for (int i = 0; i < troops.length; i++)
        {
            g.setColor(new Color(((int)(Math.random()*255)),((int)Math.random()*255),((int)Math.random()*255)));
            //System.out.print("first loop");//TODO remove
            //System.out.print(troops[i].length);//TODO remove
            for (Main.Troop t : troops[i])
            {
            	//System.out.print(t.getX()+" x "+t.getY());//TODO remove
                g.drawArc((int)t.getX(),(int)t.getY(),10,10,0,360);
            }
        }
    }
}
