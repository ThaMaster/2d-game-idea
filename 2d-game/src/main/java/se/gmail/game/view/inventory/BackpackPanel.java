package se.gmail.game.view.inventory;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import se.gmail.game.model.systems.inventory.Inventory;

public class BackpackPanel extends JPanel {

    private JLayeredPane layeredPane;

    private BackpackSlots bpSlots;
    private UiWallPanel wallPanel;

    private int bpWidth = 0;
    private int bpHeight = 0;
    
    public BackpackPanel(Inventory inv) {
        this.setLayout(new BorderLayout()); // Use BorderLayout by default

        this.layeredPane = new JLayeredPane();
        this.add(layeredPane, BorderLayout.CENTER); // Add the layered pane to the center of the panel
        
        this.bpSlots = new BackpackSlots(inv);
        this.wallPanel = new UiWallPanel(bpSlots.getSlotsWidth()+32, bpSlots.getSlotsHeight()+32);

        bpWidth = wallPanel.getPanelWidth();
        bpHeight = wallPanel.getPanelHeight();

        wallPanel.setBounds(0, 0, wallPanel.getPanelWidth(), wallPanel.getPanelHeight());
        layeredPane.add(wallPanel, JLayeredPane.DEFAULT_LAYER);

        // Add inventory panel to the layered pane
        bpSlots.setBounds(16, 16, bpSlots.getSlotsWidth(), bpSlots.getSlotsHeight());
        bpSlots.setOpaque(false);
        layeredPane.add(bpSlots, JLayeredPane.PALETTE_LAYER);
        this.setPreferredSize(new Dimension(bpSlots.getSlotsWidth(), bpSlots.getSlotsHeight()));
        this.setVisible(true);
        this.setOpaque(false);
    }

    public int getBackpackWidth() {
        return bpWidth;
    }

    public int getBackpackHeight() {
        return bpHeight;
    } 

    public int getSlotWidth() {
        return this.bpSlots.getSlotWidth();
    }
    public int getSlotHeight() {
        return this.bpSlots.getSlotHeight();
    }
    
}
