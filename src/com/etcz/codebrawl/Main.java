package com.etcz.codebrawl;

import java.util.LinkedList;

import src.com.etcz.codebrawl.PlayerInterface;


public class Main {
    private LinkedList<GameTurn> actionQueue;
    private EnvironmentInfo environment;
    private Troops[] player1;
    private Troops[] player2;
    PlayerInterface player1Interface;
    PlayerInterface player2Interface;
    
    public Main() {
	this.actionQueue = new LinkedList<GameTurn>();
        for (int i = 0; i < player1.length; i++)
        {
            player1[i] = new Troops(startingPosition1X,startingPosition1Y);
            player2[i] = new Troops(startingPosition1X,startingPosition1Y);
        }
    }

    public void QueueAction(GameTurn action) {
	this.actionQueue.offerLast(action);
    }

    public static void main(String[] args) {
	Main main = new Main();
    }
}