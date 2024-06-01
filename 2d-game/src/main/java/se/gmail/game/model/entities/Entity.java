package se.gmail.game.model.entities;

import se.gmail.game.util.enums.Direction;
import se.gmail.game.model.tiles.TileManager;
import se.gmail.game.util.CollisionBox;

import java.awt.Color;
import java.awt.Graphics2D;

import se.gmail.game.util.animation.Animator;

/**
 * Base class for all entities in the game. Enemies, npcs and even
 * the player should extend and utilize this base class.
 */
public class Entity {
    private int worldXPos, worldYPos;
    private int screenXPos, screenYPos;
    private int speed;
    private Direction direction;
    private Animator animator;
    private CollisionBox cBox;

    public Entity() {
        worldXPos = 0;
        worldYPos = 0;
        screenXPos = 0;
        screenYPos = 0;
        this.direction = Direction.WEST;
        this.animator = new Animator();
        this.cBox = new CollisionBox(25, 50, 30, 30);
        this.cBox.enableCollision(true);
    }

    public Animator getAnimator() {
        return this.animator;
    }

    public void setWorldPosition(int x, int y) {
        this.worldXPos = x;
        this.worldYPos = y;
        this.cBox.setCoords(x, y);
    }

    public void setScreenPosition(int x, int y) {
        this.screenXPos = x;
        this.screenYPos = y;
    }

    public int getWorldXPosition() {
        return worldXPos;
    }

    public int getWorldYPosition() {
        return worldYPos;
    }

    public int getScreenXPosition() {
        return screenXPos;
    }

    public int getScreenYPosition() {
        return screenYPos;
    }

    public void setWorldYPosition(int y) {
        this.worldYPos = y;
        this.cBox.setYCoord(y);
    }

    public void setWorldXPosition(int x) {
        this.worldXPos = x;
        this.cBox.setXCoord(x);
    }

    public void setScreenYPosition(int y) {
        this.screenYPos = y;
    }

    public void setScreenXPosition(int x) {
        this.screenXPos = x;
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
        this.animator.draw(g2, screenXPos, screenYPos);
    }

    public void setCollisionBox(CollisionBox collisionBox) {
        this.cBox = collisionBox;
    }

    public CollisionBox getCollisionBox() {
        return this.cBox;
    }

    public void drawCollisionBox(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.drawRect(screenXPos + cBox.getXOffset(), screenYPos + cBox.getYOffset(), cBox.getWidth(), cBox.getHeight());
    }

    public boolean isTileColliding(TileManager tm) {
        if(this.cBox.hasCollision()) {
            return cBox.checkTile(this, tm);
        }
        return false;
    }

    public boolean isPlayer() {
        return this.getClass().equals(Player.class);
    }
}
