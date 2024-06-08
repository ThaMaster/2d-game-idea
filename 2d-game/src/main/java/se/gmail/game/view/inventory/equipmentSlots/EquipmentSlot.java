package se.gmail.game.view.inventory.equipmentSlots;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class EquipmentSlot {
    
    private BufferedImage itemImage;
    private BufferedImage slotFrame;
    private BufferedImage slotFrameEmpty;

    private boolean hasItem = false;

    private int xPos = 0;
    private int yPos = 0;
    private int slotWidth = 0;
    private int slotHeight = 0;
    
    public EquipmentSlot() {
        loadSlotImages();
    }

    public abstract void loadSlotImages();

    public void draw(Graphics2D g2, int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        if(hasItem) {
            g2.drawImage(slotFrame, xPos, yPos, slotWidth, slotHeight, null);
            g2.drawImage(itemImage, xPos, yPos, slotWidth, slotHeight, null);
        } else {
            g2.drawImage(slotFrameEmpty, xPos, yPos, slotWidth, slotHeight, null);
        }
    }

    public void setItemImage(BufferedImage eqImage) {
        this.itemImage = eqImage;
        this.hasItem = true;
    }

    public void removeItemImage() {
        this.itemImage = null;
        this.hasItem = false;
    }

    public int getSlotWidth() {
        return this.slotWidth;
    }

    public void setSlotWidth(int width) {
        this.slotWidth = width;
    }

    public int getSlotHeight() {
        return this.slotHeight;
    }

    public void setSlotHeight(int height) {
        this.slotHeight = height;
    }

    protected void setSlotFrame(BufferedImage slotImage) {
        this.slotFrame = slotImage;
        this.slotWidth = slotImage.getWidth();
        this.slotHeight = slotImage.getHeight();
    }

    protected void setSlotFrameEmpty(BufferedImage slotImage) {
        this.slotFrameEmpty = slotImage;
    }

    public Rectangle getSlotRect() {
        return new Rectangle(xPos, yPos, slotWidth, slotHeight);
    }
}
