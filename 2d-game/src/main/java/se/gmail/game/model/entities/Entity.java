package se.gmail.game.model.entities;

import java.util.Vector;

/**
 * Base class for all entities in the game. Enemies, npcs and even
 * the player should extend and utilize this base class.
 */
public class Entity {
    private int xPos, yPos;
    private int speed;

    public void setPosition(int x, int y) {
        this.xPos = x;
        this.yPos = y;
    }

    public int getXPosition() {
        return xPos;
    }

    public int getYPosition() {
        return yPos;
    }

    public void setYPosition(int y) {
        this.yPos = y;
    }

    public void setXPosition(int x) {
        this.xPos = x;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
