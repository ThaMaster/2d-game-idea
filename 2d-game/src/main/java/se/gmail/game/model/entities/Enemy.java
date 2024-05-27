package se.gmail.game.model.entities;

import se.gmail.game.model.state.EnemyIdle;
import se.gmail.game.model.state.StateMachine;

public class Enemy extends Entity {

    StateMachine enemySM;

    public Enemy() {
        setSpeed(1);
        setPosition(0, 0);
        enemySM = new StateMachine(new EnemyIdle("idle", this));
    }

    public void update() {
        enemySM.update();
        System.out.println("x: " + this.getXPosition() + " y: " + this.getYPosition());
    }
}
