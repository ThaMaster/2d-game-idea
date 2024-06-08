package se.gmail.game.view.inventory;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.JPanel;

import se.gmail.game.model.object.GameObject;
import se.gmail.game.model.systems.inventory.Inventory;
import se.gmail.game.util.ImageLoader;

public class BackpackSlots extends JPanel {

    private HashMap<String, BufferedImage> images = new HashMap<>();

    private int slotWidth = 0;
    private int slotHeight = 0;

    private Inventory inventory;
    private boolean inventoryGrid[][]; 
    private boolean gridDone[][];

    private int bpSlotWidth = 0;
    private int bpSlotHeight = 0;

    public BackpackSlots(Inventory inv) {
        this.inventory = inv;
        this.inventoryGrid = this.inventory.getInventoryGrid();
        this.gridDone = new boolean[inventory.getGridWidth()][inventory.getGridHeight()];
        loadInventoryImages();
        
        this.bpSlotWidth = 2 * slotWidth * inventory.getGridWidth();
        this.bpSlotHeight = 2 * slotHeight * inventory.getGridHeight();
        this.setPreferredSize(new Dimension(bpSlotWidth, bpSlotHeight));
        this.setVisible(true);
    }

    public int getSlotsWidth() {
        return bpSlotWidth;
    }

    public int getSlotsHeight() {
        return bpSlotHeight;
    }

    private void loadInventoryImages() {
        images.put("t_l_c", ImageLoader.loadImage("/ui/inventory_slots/brown/inventory_slot_1.png"));
        images.put("t_r_c", ImageLoader.loadImage("/ui/inventory_slots/brown/inventory_slot_2.png"));
        images.put("b_l_c", ImageLoader.loadImage("/ui/inventory_slots/brown/inventory_slot_3.png"));
        images.put("b_r_c", ImageLoader.loadImage("/ui/inventory_slots/brown/inventory_slot_4.png"));
        images.put("r_w", ImageLoader.loadImage("/ui/inventory_slots/brown/inventory_slot_5.png"));
        images.put("l_w", ImageLoader.loadImage("/ui/inventory_slots/brown/inventory_slot_6.png"));
        images.put("b_w", ImageLoader.loadImage("/ui/inventory_slots/brown/inventory_slot_7.png"));
        images.put("t_w", ImageLoader.loadImage("/ui/inventory_slots/brown/inventory_slot_8.png"));
        images.put("fill", ImageLoader.loadImage("/ui/inventory_slots/brown/inventory_slot_9.png"));
        images.put("empty_t_l", ImageLoader.loadImage("/ui/inventory_slots/empty/inventory_slot_1.png"));
        images.put("empty_t_r", ImageLoader.loadImage("/ui/inventory_slots/empty/inventory_slot_2.png"));
        images.put("empty_b_l", ImageLoader.loadImage("/ui/inventory_slots/empty/inventory_slot_3.png"));
        images.put("empty_b_r", ImageLoader.loadImage("/ui/inventory_slots/empty/inventory_slot_4.png"));

        slotWidth = (int)(images.get("t_l_c").getWidth() / 1.5);
        slotHeight = (int)(images.get("t_l_c").getHeight() / 1.5);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        g2.setComposite(AlphaComposite.SrcOver);

        int gridWidth = inventory.getGridWidth();
        int gridHeight= inventory.getGridHeight();

        this.gridDone = new boolean[gridWidth][gridHeight];
        
        for(int y = 0; y < gridHeight; y++) {
            for(int x = 0; x < gridWidth; x++) {
                if(!gridDone[x][y]) {
                    if(inventoryGrid[x][y]) {
                        renderEmptySlot(g2, x, y);
                    } else {
                        renderOccupiedSlot(g2, x, y);
                    }
                }
            }
        }
    }

    private void renderEmptySlot(Graphics2D g2, int x, int y) {
        g2.drawImage(images.get("empty_t_l"), x*slotWidth*2, y*slotHeight*2, slotWidth, slotHeight, null);
        g2.drawImage(images.get("empty_t_r"), x*slotWidth*2 + slotWidth, y*slotHeight*2, slotWidth, slotHeight, null);
        g2.drawImage(images.get("empty_b_l"), x*slotWidth*2, y*slotHeight*2 + slotHeight, slotWidth, slotHeight, null);
        g2.drawImage(images.get("empty_b_r"), x*slotWidth*2 + slotWidth, y*slotHeight*2 + slotHeight, slotWidth, slotHeight, null);
        this.gridDone[x][y] = true;
    }

    private void renderOccupiedSlot(Graphics2D g2, int xPos, int yPos) {
        GameObject item = inventory.getItemAt(xPos, yPos);
        int sizeW = item.getItemSizeW() + xPos;
        int sizeH = item.getItemSizeH() + yPos;

        for(int y = yPos; y < sizeH; y++) {
            for(int x = xPos; x < sizeW; x++) {
                if(y == yPos) {
                    if(x == xPos) {
                        g2.drawImage(images.get("t_l_c"), x*slotWidth*2, y*slotHeight*2, slotWidth, slotHeight, null);
                        if(x+1 == sizeW && y+1 == sizeH) {
                            g2.drawImage(images.get("t_r_c"), x*slotWidth*2 + slotWidth, y*slotHeight*2, slotWidth, slotHeight, null);
                            g2.drawImage(images.get("b_l_c"), x*slotWidth*2, y*slotHeight*2 + slotHeight, slotWidth, slotHeight, null);
                            g2.drawImage(images.get("b_r_c"), x*slotWidth*2 + slotWidth, y*slotHeight*2 + slotHeight, slotWidth, slotHeight, null);
                        } else if(x+1 == sizeW) {
                            g2.drawImage(images.get("t_r_c"), x*slotWidth*2 + slotWidth, y*slotHeight*2, slotWidth, slotHeight, null);
                            g2.drawImage(images.get("l_w"), x*slotWidth*2, y*slotHeight*2 + slotHeight, slotWidth, slotHeight, null);
                            g2.drawImage(images.get("r_w"), x*slotWidth*2 + slotWidth, y*slotHeight*2 + slotHeight, slotWidth, slotHeight, null);
                        } else if(y+1 == sizeH) {
                            g2.drawImage(images.get("t_w"), x*slotWidth*2 + slotWidth, y*slotHeight*2, slotWidth, slotHeight, null);
                            g2.drawImage(images.get("b_l_c"), x*slotWidth*2, y*slotHeight*2 + slotHeight, slotWidth, slotHeight, null);
                            g2.drawImage(images.get("b_w"), x*slotWidth*2 + slotWidth, y*slotHeight*2 + slotHeight, slotWidth, slotHeight, null);
                        } else {
                            g2.drawImage(images.get("t_w"), x*slotWidth*2 + slotWidth, y*slotHeight*2, slotWidth, slotHeight, null);
                            g2.drawImage(images.get("l_w"), x*slotWidth*2, y*slotHeight*2 + slotHeight , slotWidth, slotHeight, null);
                            g2.drawImage(images.get("fill"), x*slotWidth*2 + slotWidth, y*slotHeight*2 + slotHeight, slotWidth, slotHeight, null);
                        }
                    } else if(x+1 == sizeW) {
                        g2.drawImage(images.get("t_r_c"), x*slotWidth*2 + slotWidth, y*slotHeight*2, slotWidth, slotHeight, null);
                        if(y+1 == sizeH) {
                            g2.drawImage(images.get("t_w"), x*slotWidth*2, y*slotHeight*2, slotWidth, slotHeight, null);
                            g2.drawImage(images.get("b_r_c"), x*slotWidth*2+slotWidth, y*slotHeight*2 + slotHeight, slotWidth, slotHeight, null);
                            g2.drawImage(images.get("b_w"), x*slotWidth*2, y*slotHeight*2 + slotHeight, slotWidth, slotHeight, null);
                        } else {
                            g2.drawImage(images.get("t_w"), x*slotWidth*2, y*slotHeight*2, slotWidth, slotHeight, null);
                            g2.drawImage(images.get("r_w"), x*slotWidth*2+slotWidth, y*slotHeight*2 + slotHeight , slotWidth, slotHeight, null);
                            g2.drawImage(images.get("fill"), x*slotWidth*2, y*slotHeight*2 + slotHeight, slotWidth, slotHeight, null);
                        }
                    } else {
                        if(y+1 == sizeH) {
                            g2.drawImage(images.get("t_w"), x*slotWidth*2, y*slotHeight*2, slotWidth, slotHeight, null);
                            g2.drawImage(images.get("t_w"), x*slotWidth*2 + slotWidth, y*slotHeight*2, slotWidth, slotHeight, null);
                            g2.drawImage(images.get("b_w"), x*slotWidth*2, y*slotHeight*2 + slotHeight , slotWidth, slotHeight, null);
                            g2.drawImage(images.get("b_w"), x*slotWidth*2 + slotWidth, y*slotHeight*2 + slotHeight, slotWidth, slotHeight, null);
                        } else {
                            g2.drawImage(images.get("t_w"), x*slotWidth*2, y*slotHeight*2, slotWidth, slotHeight, null);
                            g2.drawImage(images.get("t_w"), x*slotWidth*2 + slotWidth, y*slotHeight*2, slotWidth, slotHeight, null);
                            g2.drawImage(images.get("fill"), x*slotWidth*2, y*slotHeight*2 + slotHeight , slotWidth, slotHeight, null);
                            g2.drawImage(images.get("fill"), x*slotWidth*2 + slotWidth, y*slotHeight*2 + slotHeight, slotWidth, slotHeight, null);
                        }
                    }
                } else if(y+1 == sizeH) {
                    if(x == xPos && x+1 == sizeW) {
                        g2.drawImage(images.get("l_w"), x*slotWidth*2, y*slotHeight*2, slotWidth, slotHeight, null);
                        g2.drawImage(images.get("r_w"), x*slotWidth*2 + slotWidth, y*slotHeight*2, slotWidth, slotHeight, null);
                        g2.drawImage(images.get("b_l_c"), x*slotWidth*2, y*slotHeight*2 + slotHeight , slotWidth, slotHeight, null);
                        g2.drawImage(images.get("b_r_c"), x*slotWidth*2 + slotWidth, y*slotHeight*2 + slotHeight, slotWidth, slotHeight, null);
                    } else if(x == xPos) {
                        g2.drawImage(images.get("l_w"), x*slotWidth*2, y*slotHeight*2, slotWidth, slotHeight, null);
                        g2.drawImage(images.get("fill"), x*slotWidth*2 + slotWidth, y*slotHeight*2, slotWidth, slotHeight, null);
                        g2.drawImage(images.get("b_l_c"), x*slotWidth*2, y*slotHeight*2  + slotHeight, slotWidth, slotHeight, null);
                        g2.drawImage(images.get("b_w"), x*slotWidth*2 + slotWidth, y*slotHeight*2 + slotHeight , slotWidth, slotHeight, null);
                    } else if(x+1 == sizeW) {
                        g2.drawImage(images.get("fill"), x*slotWidth*2, y*slotHeight*2, slotWidth, slotHeight, null);
                        g2.drawImage(images.get("r_w"), x*slotWidth*2 + slotWidth, y*slotHeight*2, slotWidth, slotHeight, null);
                        g2.drawImage(images.get("b_w"), x*slotWidth*2, y*slotHeight*2+slotHeight, slotWidth, slotHeight, null);
                        g2.drawImage(images.get("b_r_c"), x*slotWidth*2+slotWidth, y*slotHeight*2+slotHeight, slotWidth, slotHeight, null);
                    } else {
                        g2.drawImage(images.get("fill"), x*slotWidth*2, y*slotHeight*2, slotWidth, slotHeight, null);
                        g2.drawImage(images.get("fill"), x*slotWidth*2 + slotWidth, y*slotHeight*2, slotWidth, slotHeight, null);
                        g2.drawImage(images.get("b_w"), x*slotWidth*2, y*slotHeight*2 + slotHeight, slotWidth, slotHeight, null);
                        g2.drawImage(images.get("b_w"), x*slotWidth*2 + slotWidth, y*slotHeight*2 + slotHeight, slotWidth, slotHeight, null);
                    }
                } else {
                    if(x == xPos && x+1 == sizeW) {
                        g2.drawImage(images.get("l_w"), x*slotWidth*2, y*slotHeight*2, slotWidth, slotHeight, null);
                        g2.drawImage(images.get("r_w"), x*slotWidth*2 + slotWidth, y*slotHeight*2, slotWidth, slotHeight, null);
                        g2.drawImage(images.get("l_w"), x*slotWidth*2, y*slotHeight*2 + slotHeight , slotWidth, slotHeight, null);
                        g2.drawImage(images.get("r_w"), x*slotWidth*2 + slotWidth, y*slotHeight*2 + slotHeight, slotWidth, slotHeight, null);
                    } else if(x == xPos) {
                        g2.drawImage(images.get("l_w"), x*slotWidth*2, y*slotHeight*2, slotWidth, slotHeight, null);
                        g2.drawImage(images.get("fill"), x*slotWidth*2 + slotWidth, y*slotHeight*2, slotWidth, slotHeight, null);
                        g2.drawImage(images.get("l_w"), x*slotWidth*2, y*slotHeight*2 + slotHeight , slotWidth, slotHeight, null);
                        g2.drawImage(images.get("fill"), x*slotWidth*2 + slotWidth, y*slotHeight*2 + slotHeight, slotWidth, slotHeight, null);
                    } else if(x+1 == sizeW) {
                        g2.drawImage(images.get("fill"), x*slotWidth*2, y*slotHeight*2, slotWidth, slotHeight, null);
                        g2.drawImage(images.get("r_w"), x*slotWidth*2 + slotWidth, y*slotHeight*2, slotWidth, slotHeight, null);
                        g2.drawImage(images.get("fill"), x*slotWidth*2, y*slotHeight*2 + slotHeight , slotWidth, slotHeight, null);
                        g2.drawImage(images.get("r_w"), x*slotWidth*2 + slotWidth, y*slotHeight*2 + slotHeight, slotWidth, slotHeight, null);
                    } else {
                        g2.drawImage(images.get("fill"), x*slotWidth*2, y*slotHeight*2, slotWidth, slotHeight, null);
                        g2.drawImage(images.get("fill"), x*slotWidth*2 + slotWidth, y*slotHeight*2, slotWidth, slotHeight, null);
                        g2.drawImage(images.get("fill"), x*slotWidth*2, y*slotHeight*2 + slotHeight , slotWidth, slotHeight, null);
                        g2.drawImage(images.get("fill"), x*slotWidth*2 + slotWidth, y*slotHeight*2 + slotHeight, slotWidth, slotHeight, null);
                    }
                }
                this.gridDone[x][y] = true;
            }
        } 

        BufferedImage itemImage = inventory.getItemAt(xPos, yPos).getObjImage();
        int itemXPos = xPos * slotWidth * 2 + (item.getItemSizeW() * slotWidth * 2 - slotWidth*2) / 2;
        int itemYPos = yPos * slotHeight * 2 + (item.getItemSizeH() * slotHeight * 2 - slotHeight*2) / 2;

        g2.drawImage(itemImage, itemXPos, itemYPos, slotWidth*2, slotHeight*2, null);
    }

    public int getSlotWidth() {
        return this.slotWidth;
    }

    public int getSlotHeight() {
        return this.slotHeight;
    }

}
