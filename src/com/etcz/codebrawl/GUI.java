package com.etcz.codebrawl;
import com.etcz.codebrawl.*;

import java.awt.Color;

import javax.swing.*;
import java.awt.Graphics;

import com.etcz.codebrawl.EnvironmentInfo;
import com.etcz.codebrawl.Main;

public class GUI extends JPanel
{
        public final int diam = 20;
        
    Main.Troop[][] troops;
    public Color[] troopsColor;
    private EnvironmentInfo env;
    public GUI(Main.Troop[][] troops,EnvironmentInfo env)
    {
        this.troops = troops;
        troopsColor = new Color[troops.length];
        this.env = env;
        setSize((int)env.getWidth(), (int)env.getHeight());
        setVisible(true);
        troopsColor[0] = new Color(((int)(Math.random()*255)),((int)(Math.random()*255)),((int)(Math.random()*255)));
        for (int i = 1; i < troops.length; i++)
        {
            troopsColor[i] = new Color((troopsColor[i-1].getRed()+255/troops.length)%255,
                                    (troopsColor[i-1].getGreen()+255/troops.length)%255,
                                    (troopsColor[i-1].getBlue()+255/troops.length)%255);
        }
    }
    
    public void paint(Graphics g, Main.actions a)
    {    
            EnvironmentInfo.PowerUp[] powerups = env.getPowerUpInfo();
        super.paintComponent(g);
        for (int i = 0; i < powerups.length; i++)
        {
            g.setColor(new Color(227,201,5));
            if (env.getPowerUpInfo()[i].isPicked())
            {
                g.drawRect(powerups[i].getX()-5, powerups[i].getY()-5, 10, 10);
            }
            else
            {
                g.fillRect(powerups[i].getX()-5, powerups[i].getY()-5, 10, 10);
            }
            
        }
        for (int i = 0; i < troops.length; i++)
        {
            g.setColor(troopsColor[i]);
            for (Main.Troop t : troops[i])
            {
                    if(t.getHealth() == 0){
                            g.drawArc((int)t.getX()-diam/2,(int)t.getY()-diam/2,diam,diam,0,360);
                    } else{
                            g.fillArc((int)t.getX()-diam/2,(int)t.getY()-diam/2,diam,diam,0,360);
                    }
                switch(t.lastAction){
                case walk:                
                    break;
                case shoot:
                        if(t.lastTarget==null || t.getHealth() <= 0){
                                break;
                        }
                        g.drawLine((int)t.getX()+5,(int)t.getY()+5,(int)t.lastTarget.getX()+5,(int)t.lastTarget.getY()+5);
                    break;
                }
            }
        }
    }
    
    public void repaint(Graphics g)
    {
        EnvironmentInfo.PowerUp[] powerups = env.getPowerUpInfo();
        super.paintComponent(g);
        for (int i = 0; i < powerups.length; i++)
        {
            g.setColor(new Color(227,201,5));
            if (env.getPowerUpInfo()[i].isPicked())
            {
                g.drawRect(powerups[i].getX()-5, powerups[i].getY()-5, 10, 10);
            }
            else
            {
                g.fillRect(powerups[i].getX()-5, powerups[i].getY()-5, 10, 10);
            }
            
        }
        for (int i = 0; i < troops.length; i++)
        {
            g.setColor(troopsColor[i]);
            for (Main.Troop t : troops[i])
            {
                    if(t.getHealth() == 0){
                            g.drawArc((int)t.getX()-diam/2,(int)t.getY()-diam/2,diam,diam,0,360);
                    } else{
                            g.fillArc((int)t.getX()-diam/2,(int)t.getY()-diam/2,diam,diam,0,360);
                    }
                switch(t.lastAction){
                case walk:                
                    break;
                case shoot:
                        if(t.lastTarget==null || t.getHealth() <= 0){
                                break;
                        }
                        g.drawLine((int)t.getX()+5,(int)t.getY()+5,(int)t.lastTarget.getX()+5,(int)t.lastTarget.getY()+5);
                    break;
                }
            }
        }
    }
    public void paintComponent(Graphics g)
    {
            EnvironmentInfo.PowerUp[] powerups = env.getPowerUpInfo();
        super.paintComponent(g);
        for (int i = 0; i < powerups.length; i++)
        {
            g.setColor(new Color(227,201,5));
            if (env.getPowerUpInfo()[i].isPicked())
            {
                g.drawRect(powerups[i].getX()-5, powerups[i].getY()-5, 10, 10);
            }
            else
            {
                g.fillRect(powerups[i].getX()-5, powerups[i].getY()-5, 10, 10);
            }
            
        }
        for (int i = 0; i < troops.length; i++)
        {
            g.setColor(troopsColor[i]);
            for (Main.Troop t : troops[i])
            {
                    if(t.getHealth() == 0){
                            g.drawArc((int)t.getX()-diam/2,(int)t.getY()-diam/2,diam,diam,0,360);
                    } else{
                            g.fillArc((int)t.getX()-diam/2,(int)t.getY()-diam/2,diam,diam,0,360);
                    }
                switch(t.lastAction){
                case walk:                
                    break;
                case shoot:
                        if(t.lastTarget==null || t.getHealth() <= 0){
                                break;
                        }
                    g.drawLine((int)t.getX()+5,(int)t.getY()+5,(int)t.lastTarget.getX()+5,(int)t.lastTarget.getY()+5);
                    break;
                }
            }
        }
    }
}