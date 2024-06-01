package se.gmail.game.model.object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import se.gmail.game.model.entities.Player;
import se.gmail.game.util.CollisionBox;
import se.gmail.game.util.ImageLoader;

public abstract class GameObject {

    private BufferedImage objImage;
    private String name;
    private String description;
    private CollisionBox cBox = new CollisionBox(25, 25, 30, 30);
    private int worldXPos, worldYPos;
    private int screenXPos, screenYPos;

    public void loadObjImage(String imagePath) {
        this.objImage = ImageLoader.loadImage(imagePath);
    }

    public void setObjImage(BufferedImage image) {
        this.objImage = image;
    }

    public BufferedImage getObjImage() {
        return this.objImage;
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

    public void setCollisionBox(CollisionBox cBox) {
        this.cBox = cBox;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(objImage, screenXPos, screenYPos, objImage.getWidth(), objImage.getHeight(), null);
    }

    public void draw(Graphics2D g2, int xPos, int yPos) {
        g2.drawImage(objImage, xPos, yPos, objImage.getWidth(), objImage.getHeight(), null);
    }

    public void drawCollisionBox(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.drawRect(screenXPos + cBox.getXOffset(), screenYPos + cBox.getYOffset(), cBox.getWidth(), cBox.getHeight());
    }
    
    public abstract void onPickup(Player p);
}
