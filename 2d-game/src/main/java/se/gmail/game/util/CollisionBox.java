package se.gmail.game.util;

import java.awt.Rectangle;

import se.gmail.game.model.entities.Entity;
import se.gmail.game.model.tiles.TileManager;

public class CollisionBox {
    private Rectangle collisionBox;
    private boolean collisionOn = false;
    private int xOffset = 0, yOffset = 0;
    
    public CollisionBox(int x, int y, int width, int height) {
        this.xOffset = x;
        this.yOffset = y;
        this.collisionBox = new Rectangle(x, y, width, height);
    }

    public boolean checkTile(Entity e, TileManager tm) {
        Rectangle cShape = e.getCollisionBox().getCollisionShape();
        int entityLeft = cShape.x;
        int entityRight = cShape.x + cShape.width;
        int entityTop = cShape.y;
        int entityBottom = cShape.y + cShape.height;

        int entityLeftCol = entityLeft / tm.getTileSize();
        int entityRightCol = entityRight / tm.getTileSize();
        int entityTopRow = entityTop / tm.getTileSize();
        int entityBottomRow = entityBottom / tm.getTileSize();

        int tileNum1, tileNum2;

        switch(e.getDirection()) {
            case NORTH:
                entityTopRow = (entityTop - e.getSpeed())/ tm.getTileSize();
                tileNum1 = tm.getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = tm.getMapTileNum()[entityRightCol][entityTopRow];
                break;
            case SOUTH:
                entityBottomRow = (entityBottom + e.getSpeed())/ tm.getTileSize();
                tileNum1 = tm.getMapTileNum()[entityLeftCol][entityBottomRow];
                tileNum2 = tm.getMapTileNum()[entityRightCol][entityBottomRow];
                break;
            case WEST:
                entityLeftCol = (entityLeft - e.getSpeed())/ tm.getTileSize();
                tileNum1 = tm.getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = tm.getMapTileNum()[entityLeftCol][entityBottomRow];
                break;
            case EAST:
                entityRightCol = (entityRight + e.getSpeed())/tm.getTileSize();
                tileNum1 = tm.getMapTileNum()[entityRightCol][entityTopRow];
                tileNum2 = tm.getMapTileNum()[entityRightCol][entityBottomRow];
                break;
            default:
                tileNum1 = 0;
                tileNum2 = 0;
                System.out.println(e.getDirection());
                break;
        }

        if(tm.getTile(tileNum1).getCollisionBox().hasCollision() || tm.getTile(tileNum2).getCollisionBox().hasCollision()) {
            return true;
        }

        return false;
    }

    public void enableCollision(boolean b) {
        this.collisionOn = b;
    }

    public boolean hasCollision() {
        return this.collisionOn;
    }

    public Rectangle getCollisionShape() {
        return this.collisionBox;
    }

    public void setCoords(int x, int y) {

        this.collisionBox.x = xOffset + x;
        this.collisionBox.y = yOffset + y;
    }

    public void setXCoord(int x) {
        this.collisionBox.x = xOffset + x;
    }

    public void setYCoord(int y) {
        this.collisionBox.y = yOffset + y;
    }

    public int getWidth() {
        return this.collisionBox.width;
    }

    public int getHeight() {
        return this.collisionBox.height;
    }

    public int getXCoord() {
        return this.collisionBox.x;
    }

    public int getYCoord() {
        return this.collisionBox.y;
    }

    public int getXOffset() {
        return this.xOffset;
    }

    public int getYOffset() {
        return this.yOffset;
    }
}
