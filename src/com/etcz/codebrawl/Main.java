package com.etcz.codebrawl;

import java.util.LinkedList;


public class Main {
    private LinkedList<GameTurn> actionQueue;

    public Main() {
	this.actionQueue = new LinkedList<GameTurn>();
    }

    public void QueueAction(GameTurn action) {
	this.actionQueue.offerLast(action);
    }

    public static void main(String[] args) {
	Main main = new Main();
    }
}