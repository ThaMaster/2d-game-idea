package se.gmail.game.model.state;

import se.gmail.game.model.entities.Enemy;
import se.gmail.game.util.Util;

public class EnemyIdle extends State {

    private int moveSpeed = 1;

    private int xDirection = 0;
    private int yDirection = 0;
    // in milliseconds
    private double wanderTime = 0;
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

    }

    @Override
    public void update() {
        currentTime = System.currentTimeMillis();
        elapsedTime = currentTime - lastTime;

        if(wanderTime > 0) {
            enemy.setXPosition(enemy.getXPosition() + xDirection);
            enemy.setYPosition(enemy.getYPosition() + yDirection);
            wanderTime -= elapsedTime;
        } else {
            randomizedWander();
        }
        lastTime = currentTime;
    }

    private void randomizedWander() {
        xDirection = Util.randomInt(-1, 1);
        yDirection = Util.randomInt(-1, 1);
        wanderTime = Util.randomInt(0, 3) * 1000;
    }

        
}
