package se.gmail.game.model.entities;

public class Player extends Entity {

    private double health;
    private double mana;

    public Player() {
        setPlayerDefaultValues();
    }

    public void setPlayerDefaultValues() {
        this.health = 100.0;
        this.mana = 50.0;
        this.setSpeed(4);
        this.setPosition(1, 1);
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
}
