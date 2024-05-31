package se.gmail.game.model.entities;
import se.gmail.game.util.ImageLoader;
import se.gmail.game.util.animation.Animation;

public class Player extends Entity {

    private double health;
    private double mana;

    public Player() {
        setPlayerDefaultValues();
        loadPlayerAnimations();
    }

    public void setPlayerDefaultValues() {
        this.health = 100.0;
        this.mana = 50.0;
        this.setSpeed(4);
    }

    public double getHealth() {
        return this.health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getMana() {
        return this.mana;
    }

    public void setMana(double mana) {
        this.mana = mana;
    }

    public void loadPlayerAnimations() {
        getAnimator().addAnimation(new Animation("idle", ImageLoader.loadWholeDirectory("/player/sprites/executioner/male/idle"), 7));
        getAnimator().addAnimation(new Animation("run", ImageLoader.loadWholeDirectory("/player/sprites/executioner/male/run"), 7));
    }
}
