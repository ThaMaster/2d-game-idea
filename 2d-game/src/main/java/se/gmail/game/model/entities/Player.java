package se.gmail.game.model.entities;
import se.gmail.game.util.ImageLoader;
import se.gmail.game.util.animation.Animation;

public class Player extends Entity {

    private double currentHealth;
    private double maxHealth;

    public Player() {
        setPlayerDefaultValues();
        loadPlayerAnimations();
    }

    public void setPlayerDefaultValues() {
        this.maxHealth = 100.0;
        this.currentHealth = maxHealth;
        this.setSpeed(4);
    }

    public double getCurrentHealth() {
        return this.currentHealth;
    }

    public void setCurrentHealth(double health) {
        this.currentHealth = health;
    }

    public double getMaxHealth() {
        return this.maxHealth;
    }

    public void setMaxHealth(double health) {
        this.maxHealth = health;
    }

    public void loadPlayerAnimations() {
        getAnimator().addAnimation(new Animation("idle", ImageLoader.loadWholeDirectory("/player/sprites/executioner/male/idle"), 7));
        getAnimator().addAnimation(new Animation("run", ImageLoader.loadWholeDirectory("/player/sprites/executioner/male/run"), 7));
    }
}
