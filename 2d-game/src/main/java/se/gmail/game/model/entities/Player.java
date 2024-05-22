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
}
