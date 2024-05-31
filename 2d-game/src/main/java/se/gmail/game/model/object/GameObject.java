package se.gmail.game.model.object;

import java.awt.image.BufferedImage;

import javax.swing.text.html.parser.Entity;

import se.gmail.game.util.CollisionBox;
import se.gmail.game.util.ImageLoader;

public abstract class GameObject {
    private BufferedImage image;
    private String name;
    private String description;
    private CollisionBox cBox;
    private int worldXPos, worldYPos;

    public void loadObjectImage(String imagePath) {
        this.image = ImageLoader.loadImage(imagePath);
    }

    public BufferedImage getObjImage() {
        return this.image;
    }

    public void setWorldPosition(int x, int y) {
        this.worldXPos = x;
        this.worldYPos = y;
    }

    public void setWorldXPosition(int x) {
        this.worldXPos = x;
    }

    public int getWorldXPosition() {
        return this.worldXPos;
    }

    public void setWorldYPosition(int y) {
        this.worldYPos = y;
    }

    public int getWorldYPosition() {
        return this.worldYPos;
    }

    public void setObjName(String name) {
        this.name = name;
    }

    public String getObjName() {
        return this.name;
    }

    public void setObjDesc(String description) {
        this.description = description;
    }

    public String getObjDesc() {
        return this.description;
    }

    public CollisionBox getCollisionBox() {
        return this.cBox;
    }
    
    public abstract void onPickup(Entity e);
}
