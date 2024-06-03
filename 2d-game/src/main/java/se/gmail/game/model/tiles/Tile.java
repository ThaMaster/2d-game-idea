package se.gmail.game.model.tiles;

import java.awt.image.BufferedImage;

import se.gmail.game.util.CollisionBox;

public class Tile {
    private String tileName = "";
    private BufferedImage image;
    private CollisionBox cBox;

    public Tile(String tileName) {
        this.tileName = tileName;
        this.cBox = new CollisionBox(0, 0, 80, 80);
        this.cBox.enableCollision(false);
    }
    public String getTileName() {
        return this.tileName;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public void setCollisionBox(CollisionBox collisionBox) {
        this.cBox = collisionBox;
    }

    public CollisionBox getCollisionBox() {
        return this.cBox;
    }
}
