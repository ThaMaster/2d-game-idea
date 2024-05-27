package se.gmail.game.model.entities;

import se.gmail.game.util.enums.Direction;

import java.awt.Graphics2D;

import se.gmail.game.util.animation.Animator;

/**
 * Base class for all entities in the game. Enemies, npcs and even
 * the player should extend and utilize this base class.
 */
public class Entity {
    private int xPos, yPos;
    private int speed;
    private Direction direction;
    private Animator animator;

    public Entity() {
        xPos = 0;
        yPos = 0;
        this.direction = Direction.WEST;
        this.animator = new Animator();
    }

    public void setPosition(int x, int y) {
        this.xPos = x;
        this.yPos = y;
    }

    public Animator getAnimator() {
        return this.animator;
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

    public Direction getDirection() {
        return this.direction;
    }

    public void setDirection(Direction dir) {
        if(dir == Direction.EAST || dir == Direction.WEST) {
            this.animator.setFlipped(dir == Direction.EAST);
        }
        this.direction = dir;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public void draw(Graphics2D g2) {
        this.animator.draw(g2, getXPosition(), getYPosition());
    }
}
