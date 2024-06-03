package se.gmail.game.view.inventory;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.JPanel;

import se.gmail.game.model.object.GameObject;
import se.gmail.game.model.systems.inventory.Inventory;
import se.gmail.game.util.ImageLoader;

public class InventoryPanel extends JPanel implements MouseListener, MouseMotionListener {

    private HashMap<String, BufferedImage> images = new HashMap<>();
    private int slotWidth = 0;
    private int slotHeight = 0;
    private Inventory inventory;
    private boolean inventoryGrid[][]; 
    private boolean gridDone[][];

    private GameObject holdingObject;
    private BufferedImage holdingImage;
    private int selectedXPos = -1;
    private int selectedYPos = -1;
    private int hItemX = -1;
    private int hItemY = -1;
    private boolean dragging = false;


    public InventoryPanel(Inventory inv) {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.inventory = inv;
        this.inventoryGrid = this.inventory.getInventoryGrid();
        this.gridDone = new boolean[inventory.getGridWidth()][inventory.getGridHeight()];
        loadImages();
        this.setPreferredSize(new Dimension(2 * slotWidth * inventory.getGridWidth(), 2 * slotHeight * inventory.getGridHeight()));
    }

    private void loadImages() {
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

        slotWidth = images.get("t_l_c").getWidth();
        slotHeight = images.get("t_l_c").getHeight();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

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

        if(hItemX != -1 && hItemY != -1) {
            g.drawImage(holdingImage, hItemX, hItemY, holdingImage.getWidth(), holdingImage.getHeight(), null);
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

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            this.selectedXPos = e.getX()/(slotWidth*2);
            this.selectedYPos = e.getY()/(slotHeight*2);
            
            this.holdingObject = inventory.getItemAt(selectedXPos, selectedYPos);

            if(this.holdingObject != null) {
                this.dragging = true;
                inventory.removeItem(selectedXPos, selectedYPos);
                this.holdingImage = holdingObject.getObjImage();
                updateImagePosition(e.getX(), e.getY());
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            this.dragging = false;
            this.hItemX = -1;
            this.hItemY = -1;
            if(this.holdingObject != null) {
                if(!inventory.insertItem(holdingObject, e.getX()/(slotWidth*2), e.getY()/(slotHeight*2))) {
                    inventory.insertItem(holdingObject,this.selectedXPos, this.selectedYPos);
                }
            }
            repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (dragging) {
            updateImagePosition(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
    
    private void updateImagePosition(int x, int y) {
        hItemX = x - holdingImage.getWidth() / 2;
        hItemY = y - holdingImage.getHeight() / 2;
        repaint();
    }
}
