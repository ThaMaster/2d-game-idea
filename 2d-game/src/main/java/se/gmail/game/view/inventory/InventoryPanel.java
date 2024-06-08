package se.gmail.game.view.inventory;

import java.awt.BorderLayout;
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

    public InventoryPanel(Inventory inv) {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.setLayout(new BorderLayout());

        bpPanel = new BackpackPanel(inv);
        eqPanel = new EquipmentPanel();

        this.invWidth = bpPanel.getBackpackWidth() + eqPanel.getWindowWidth();
        this.invHeight= bpPanel.getBackpackHeight();

        this.add(bpPanel, BorderLayout.CENTER);
        this.add(eqPanel, BorderLayout.EAST);
        this.setVisible(false);
        this.setOpaque(false);
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
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
}
