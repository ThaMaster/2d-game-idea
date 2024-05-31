package se.gmail.game.model.entities;

import se.gmail.game.model.state.*;
import se.gmail.game.util.CollisionBox;
import se.gmail.game.util.ImageLoader;
import se.gmail.game.util.animation.Animation;

public class Enemy extends Entity {

    StateMachine enemySM;

    public Enemy() {
        setSpeed(1);
        loadEnemyAnimations();
        enemySM = new StateMachine(new EnemyIdle("idle", this));
        enemySM.addState(new EnemyRun("run", this));
    }

    public void update() {
        enemySM.update();
        getAnimator().setAnimation(enemySM.getCurrentStateName());
    }

    public void loadEnemyAnimations() {
        getAnimator().addAnimation(new Animation("idle", ImageLoader.loadWholeDirectory("/enemy/sprites/idle"), 7));
        getAnimator().addAnimation(new Animation("run", ImageLoader.loadWholeDirectory("/enemy/sprites/run"), 7));
    }
}
