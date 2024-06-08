package se.gmail.game.view.inventory;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import se.gmail.game.model.object.GameObject;
import se.gmail.game.model.systems.inventory.Inventory;

public class InventoryPanel extends JPanel implements MouseListener, MouseMotionListener {

    private GameObject holdingObject;
    private BufferedImage holdingImage;
    private int selectedXPos = -1;
    private int selectedYPos = -1;
    private int hItemX = -1;
    private int hItemY = -1;
    private boolean dragging = false;

    private int invWidth = 0;
    private int invHeight = 0;

    private BackpackPanel bpPanel;
    private EquipmentPanel eqPanel;

    private Inventory inv;

    public InventoryPanel(Inventory inv) {
        this.inv = inv;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.setLayout(new BorderLayout());

        bpPanel = new BackpackPanel(inv);
        eqPanel = new EquipmentPanel();

        this.invWidth = bpPanel.getBackpackWidth() + eqPanel.getWindowWidth();
        this.invHeight= bpPanel.getBackpackHeight();

        this.add(bpPanel, BorderLayout.CENTER);
        this.add(eqPanel, BorderLayout.EAST);
        this.setVisible(true);
        this.setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        if(hItemX != -1 && hItemY != -1) {
            g2.drawImage(holdingImage, hItemX, hItemY, holdingImage.getWidth(), holdingImage.getHeight(), null);
        }
    }

    public int getInventoryWidth() {
        return invWidth;
    }

    public int getInventoryHeight() {
        return invHeight;
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
            this.selectedXPos = (e.getX() - 16)/(bpPanel.getSlotWidth()*2);
            this.selectedYPos = (e.getY() - 16)/(bpPanel.getSlotHeight()*2);
            
            this.holdingObject = inv.getItemAt(selectedXPos, selectedYPos);

            if(this.holdingObject != null && e.getX() < bpPanel.getBackpackWidth()) {
                Integer pos[] = inv.getItemPos(holdingObject);
                this.selectedXPos = pos[0];
                this.selectedYPos = pos[1];
                this.dragging = true;
                this.holdingImage = holdingObject.getObjImage();
                inv.removeItem(selectedXPos, selectedYPos);
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
                String hoveringSlot = checkEquipemntHover(e.getX(), e.getY());
                if(!hoveringSlot.equals("none")) {
                    eqPanel.setEquipment(hoveringSlot, holdingImage);
                }
                if(!inv.insertItem(holdingObject, (e.getX() - 16)/(bpPanel.getSlotWidth()*2), (e.getY() - 16)/(bpPanel.getSlotHeight()*2))) {
                    inv.insertItem(holdingObject, this.selectedXPos, this.selectedYPos);
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
    public void mouseMoved(MouseEvent e) {
        checkEquipemntHover(e.getX(), e.getY());
    }

    private void updateImagePosition(int x, int y) {
        hItemX = x - holdingImage.getWidth() / 2;
        hItemY = y - holdingImage.getHeight() / 2;
        repaint();
    }

    private String checkEquipemntHover(int xPos, int yPos) {
        int eqStartW = bpPanel.getBackpackWidth();
        if(xPos >= eqStartW) {
            return eqPanel.getHoveringSlot(xPos - eqStartW, yPos);
        }
        return "none";
    }

    public boolean isDraggingItem() {
        return this.dragging;
    }

    public BufferedImage getHoldingImage() {
        return this.holdingImage;
    }

    public int getHImageX() {
        return this.hItemX;
    }

    public int getHImageY() {
        return this.hItemY;
    }
}
