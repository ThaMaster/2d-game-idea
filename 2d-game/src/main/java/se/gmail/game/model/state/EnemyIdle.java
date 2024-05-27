package se.gmail.game.model.state;

import se.gmail.game.model.entities.Enemy;
import se.gmail.game.util.Util;

public class EnemyIdle extends State {

    private int moveSpeed = 0;

    // in milliseconds
    private double idleTime = 0;
    private double currentTime = 0, lastTime = 0, elapsedTime = 0;
    private Enemy enemy;

    public EnemyIdle(String stateName, Enemy enemy) {
        super(stateName);
        this.enemy = enemy;
    }

    @Override
    public void enter() {
        this.enemy.setSpeed(moveSpeed);
        lastTime = System.currentTimeMillis();
        randomizedWander();
    }

    @Override
    public void exit() {
        this.setTransition(false);
    }

    @Override
    public void update() {
        currentTime = System.currentTimeMillis();
        elapsedTime = currentTime - lastTime;

        if(idleTime > 0) {
            idleTime -= elapsedTime;
        } else {
            this.setNextStateName("run");
            this.setTransition(true);
        }
        lastTime = currentTime;
    }

    private void randomizedWander() {
        idleTime = Util.randomInt(0, 3) * 1000;
    }

        
}
