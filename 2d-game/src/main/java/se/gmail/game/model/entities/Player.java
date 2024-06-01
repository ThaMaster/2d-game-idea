package se.gmail.game.model.entities;
import se.gmail.game.util.ImageLoader;
import se.gmail.game.util.animation.Animation;

import se.gmail.game.model.object.GameObject;

public class Player extends Entity {

    private double currentHealth;
    private double maxHealth;

    private GameObject inventory[] = new GameObject[9];

    public Player() {
        setPlayerDefaultValues();
        loadPlayerAnimations();
    }

    public void setPlayerDefaultValues() {
        this.maxHealth = 100.0;
        this.currentHealth = 40;
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

    public void addToInventory(GameObject gObj, int i) {
        this.inventory[i] = gObj;
    }

    public GameObject[] getInventory() {
        return this.inventory;
    }
}
