package se.gmail.game.model.state;

import se.gmail.game.model.entities.Enemy;
import se.gmail.game.util.Util;
import se.gmail.game.util.enums.Direction;

public class EnemyRun extends State {

    private int moveSpeed = 1;

    private int xDirection = 0;
    private int yDirection = 0;
    // in milliseconds
    private double wanderTime = 0;
    private double currentTime = 0, lastTime = 0, elapsedTime = 0;
    private Enemy enemy;

    public EnemyRun(String stateName, Enemy enemy) {
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

        if(wanderTime > 0) {
            if(xDirection > 0) {
                enemy.setDirection(Direction.EAST);
            } else {
                enemy.setDirection(Direction.WEST);
            }
            enemy.setWorldXPosition(enemy.getWorldXPosition() + xDirection);
            enemy.setWorldYPosition(enemy.getWorldYPosition() + yDirection);
            wanderTime -= elapsedTime;
        } else {
            this.setNextStateName("idle");
            this.setTransition(true);
        }
        lastTime = currentTime;
    }

    private void randomizedWander() {
        xDirection = Util.randomInt(-1, 1);
        yDirection = Util.randomInt(-1, 1);
        wanderTime = Util.randomInt(0, 3) * 1000;
    }

        
}
