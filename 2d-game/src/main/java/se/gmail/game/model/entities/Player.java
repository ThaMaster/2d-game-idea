package se.gmail.game.model.entities;
import se.gmail.game.util.ImageLoader;
import se.gmail.game.util.animation.Animation;

import se.gmail.game.model.object.GameObject;
import se.gmail.game.model.systems.inventory.Inventory;

public class Player extends Entity {

    private double currentHealth;
    private double maxHealth;

    private Inventory inventory;

    public Player() {
        this.inventory = new Inventory(8, 8);
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

    public void addToInventory(GameObject gObj) {
        if(!this.inventory.insertAtFirstAvailableSlot(gObj)) {
            System.out.println("NO SPACE IN INVENTORY!");
        }
    }

    public Inventory getInventory() {
        return this.inventory;
    }
}
